package com.veselov.alex.racecar.service.parser;

import com.veselov.alex.racecar.data.dao.SourceSiteRepository;
import com.veselov.alex.racecar.data.entity.Car;
import com.veselov.alex.racecar.data.entity.SourceSite;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * It parses wwww.carsav.by site
 */
@Slf4j
@Service
public class CarsAvByParser {

    @Autowired
    private SourceSiteRepository repository;

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
            log.info("Parsed {} of cars", cars.size());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return cars;
    }

    /**
     * It returns quantity of cars for the href
     *
     * @param href
     * @return
     */
    public int getCarQuantityFromPage(String href) {
        int result = 0;
        try {
            result = Integer.parseInt(
                    Jsoup.connect(href)
                            .get()
                            .select("header .heading-title")
                            .text()
                            .replaceAll("\\D+", "")
            );
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        log.info("Found {} cars to parse", result);
        return result;
    }

    /**
     * It handles pagination for this query.
     *
     * @param startHRef 1st page of pagination to parse
     * @param cars
     * @param document
     * @throws IOException
     */
    private void handlePagination(String startHRef, List<Car> cars, Document document) throws IOException {
        int pageQuantity = getPageQuantity(startHRef);
        SourceSite sourceSite = this.repository.findById(1001).get();
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
                cars.add(new Car(0, imgSrc, name, description, link, price, year, sourceSite));
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
        return (int) Math.ceil(this.getCarQuantityFromPage(href) / shownCarsOnOnePage);
    }
}
