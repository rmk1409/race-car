package com.veselov.alex.racecar.service.parser;

import com.veselov.alex.racecar.data.entity.Car;

import java.util.List;

public interface SiteParser {

    /**
     * It parses query and finds cars.
     *
     * @param startHRef
     * @return
     */
    List<Car> parseSite(String startHRef);

    /**
     * It returns total quantity of cars for the href
     *
     * @param href
     * @return
     */
    int getCarQuantityFromPage(String href);
}
