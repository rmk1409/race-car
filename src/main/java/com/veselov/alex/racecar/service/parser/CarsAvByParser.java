package com.veselov.alex.racecar.service.parser;

import com.veselov.alex.racecar.data.dao.AutoRepository;
import com.veselov.alex.racecar.data.entity.Auto;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * It parses wwww.carsav.by site
 */
@Service
public class CarsAvByParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarsAvByParser.class);

    @Autowired
    private AutoRepository repository;

    /**
     * It parses query and it finds cars.
     *
     * @param startHRef
     * @return
     */
    public List<Auto> parseSite(String startHRef) {
        List<Auto> autos = new ArrayList<>();
        Connection connection = Jsoup.connect(startHRef);
        Document document;
        try {
            document = connection.get();
            this.handlePagination(startHRef, autos, document);
            LOGGER.info("Found {} cars", autos.size());
            repository.saveAll(autos);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return autos;
    }

    /**
     * It handles pagination for this query.
     *
     * @param startHRef
     * @param autos
     * @param document
     * @throws IOException
     */
    private void handlePagination(String startHRef, List<Auto> autos, Document document) throws IOException {
        int pageQuantity = getPageQuantity(document);
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
                autos.add(new Auto(imgSrc, name, description, link, price, year));
            });
            document = Jsoup.connect(startHRef.replace("?", String.format("/page/%d?", i + 1))).get();
        }
    }

    /**
     * It gets quantity of pages.
     *
     * @param document
     * @return
     */
    private int getPageQuantity(Document document) {
        final double shownCarsOnOnePage = 25.0;
        String text = document.select("header .heading-title").text();
        return (int) Math.ceil(Integer.parseInt(text.replaceAll("\\D+", "")) / shownCarsOnOnePage);
    }
}
