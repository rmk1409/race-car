package com.veselov.alex.racecar.service.parser;

import com.veselov.alex.racecar.data.dao.AutoRepository;
import com.veselov.alex.racecar.data.dao.QueryRepository;
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

@Service
public class CarsAvByParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarsAvByParser.class);

    @Autowired
    private QueryRepository queryRepository;
    @Autowired
    private AutoRepository autoRepository;

    public List<Auto> handlePagination(String startLink) {
        List<Auto> autos = new ArrayList<>();
        Connection connection = Jsoup.connect(startLink);
        Document document;
        try {
            document = connection.get();
            int paginationNumber = (int) Math.ceil(Integer.parseInt(document.select("header .heading-title").text().replaceAll("\\D+", "")) / 25.0);
            for (int i = 1; i <= paginationNumber; i++) {
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
                document = Jsoup.connect(startLink.replace("?", String.format("/page/%d?", i + 1))).get();
            }
            LOGGER.info("Found {} cars", autos.size());
            autoRepository.saveAll(autos);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return autos;
    }
}
