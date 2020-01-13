package com.veselov.alex.racecar.service.parser;

import com.veselov.alex.racecar.data.entity.Car;
import com.veselov.alex.racecar.data.entity.SourceSite;
import com.veselov.alex.racecar.service.dbservice.SourceSiteService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
public class CarsAvBySiteParser implements SiteParser {
    @Autowired
    private SourceSiteService sourceSiteService;

    public List<Car> parseSite(String startHRef) {
        List<Car> cars = new ArrayList<>();
        try {
            cars = this.getCarsFromSite(startHRef);
            log.info("Parsed {} cars", cars.size());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return cars;
    }

    private List<Car> getCarsFromSite(String startHRef) throws IOException {
        Document document = Jsoup.connect(startHRef)
                .get();
        return this.handlePagination(startHRef, document);
    }

    /**
     * It handles pagination for this query.
     *
     * @param startHRef 1st page of pagination to parse
     * @param document
     * @throws IOException
     */
    private List<Car> handlePagination(String startHRef, Document document) throws IOException {
        List<Car> cars = new ArrayList<>();
        SourceSite sourceSite = this.sourceSiteService.findById(1001).get();
        for (int i = 1, pageQuantity = this.getPageQuantity(startHRef); i <= pageQuantity; i++) {
            Elements select = document.select(".listing-item");
            select.forEach(carElement -> {
                cars.add(this.parseCar(sourceSite, carElement));
            });
            document = Jsoup.connect(startHRef.replace("?", String.format("/page/%d?", i + 1))).get();
        }
        return cars;
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

    public int getCarQuantityFromPage(String href) {
        int carQuantity = 0;
        try {
            carQuantity = Integer.parseInt(
                    Jsoup.connect(href)
                            .get()
                            .select("header .heading-title")
                            .text()
                            .replaceAll("\\D+", "")
            );
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        log.info("Found {} cars to parse", carQuantity);
        return carQuantity;
    }

    private Car parseCar(SourceSite sourceSite, Element carElement) {
        String imgSrc = carElement.select(".listing-item-image img").attr("src");
        Elements body = carElement.select(".listing-item-body");
        String name = body.select(".listing-item-title a").text();
        String description = body.select(".listing-item-desc").text().substring(6);
        String link = body.select(".listing-item-title a").attr("href");
        Integer price = Integer.parseInt(body.select(".listing-item-price small").text().replaceAll("\\s", ""));
        Integer year = Integer.parseInt(body.select(".listing-item-desc span").text().substring(0, 4));
        return new Car(0, imgSrc, name, description, link, price, year, sourceSite);
    }
}
