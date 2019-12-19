package com.veselov.alex.racecar.service.parser;

import com.veselov.alex.racecar.data.entity.Car;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * It parses wwww.carsav.by site
 */
@Service
public class CarsAvByParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarsAvByParser.class);

    /**
     * It parses query and it finds cars.
     *
     * @param startHRef
     * @return
     */
    public List<Car> parseSite(String startHRef) {
        List<Car> cars = new ArrayList<>();
        Connection connection = Jsoup.connect(startHRef);
        Document document;
        try {
            document = connection.get();
            this.handlePagination(startHRef, cars, document);
            LOGGER.info("Parsed {} cars", cars.size());
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return cars;
    }

    /**
     * It returns quantity of cars for the href
     *
     * @param href
     * @param decode
     * @return
     */
    public int getCarQuantity(String href, boolean decode) {
        int result = 0;
        href = getProperUrl(href, decode);
        try {
            LOGGER.info("Will parse this url -> {}", href);
            result = Integer.parseInt(
                    Jsoup.connect(href)
                            .get()
                            .select("header .heading-title")
                            .text()
                            .replaceAll("\\D+", "")
            );
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        LOGGER.info("Found {} cars to parse", result);
        return result;
    }

    /**
     * It decodes href if it need it.
     *
     * @param href
     * @param decode
     * @return
     */
    private String getProperUrl(String href, boolean decode) {
        try {
            href = decode ? URLDecoder.decode(href, "UTF-8") : href;
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return href;
    }

    /**
     * It handles pagination for this query.
     *
     * @param startHRef
     * @param cars
     * @param document
     * @throws IOException
     */
    private void handlePagination(String startHRef, List<Car> cars, Document document) throws IOException {
        int pageQuantity = getPageQuantity(startHRef);
        for (int i = 1; i <= pageQuantity; i++) {
            Elements select = document.select(".listing-item");
            select.forEach(car -> {
                String imgSrc = car.select(".listing-item-image img").attr("src");
                Elements body = car.select(".listing-item-body");
                String name = body.select(".listing-item-title a").text();
                String description = body.select(".listing-item-desc").text().substring(6);
                String link = body.select(".listing-item-title a").attr("href");
                Integer price = Integer.parseInt(body.select(".listing-item-price small").text().replaceAll("\\s", ""));
                Integer year = Integer.parseInt(body.select(".listing-item-desc span").text().substring(0, 4));
                cars.add(new Car(imgSrc, name, description, link, price, year));
            });
            document = Jsoup.connect(startHRef.replace("?", String.format("/page/%d?", i + 1))).get();
        }
    }

    /**
     * It gets quantity of pages.
     *
     * @param href
     * @return
     */
    private int getPageQuantity(String href) {
        final double shownCarsOnOnePage = 25.0;
        return (int) Math.ceil(this.getCarQuantity(href, false) / shownCarsOnOnePage);
    }
}
