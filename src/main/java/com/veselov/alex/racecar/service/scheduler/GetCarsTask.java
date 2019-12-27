package com.veselov.alex.racecar.service.scheduler;

import com.veselov.alex.racecar.data.dao.AutoRepository;
import com.veselov.alex.racecar.data.dao.QueryRepository;
import com.veselov.alex.racecar.data.entity.Car;
import com.veselov.alex.racecar.data.entity.Query;
import com.veselov.alex.racecar.service.parser.CarsAvByParser;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class GetCarsTask {
    Map<String, Car> carCache = new HashMap<>();
    @Autowired
    AutoRepository autoRepository;
    @Autowired
    QueryRepository queryRepository;
    @Autowired
    CarsAvByParser carsAvByParser;

    @Scheduled(cron = "0 0/1 * * * *")
    public void getCars() {
        log.info("Scheduler is working, getting cars");
        Set<String> queries = getQueries();
        Map<String, Car> cars = getAllCars(queries);
        this.removeDuplicates(cars);
        log.info("Unique cars({}) -> {}", cars.size(), cars);
        this.autoRepository.saveAll(cars.values());
        carCache.putAll(cars);
    }

    private void removeDuplicates(Map<String, Car> cars) {
        cars.keySet().removeAll(carCache.keySet());
    }

    private Map<String, Car> getAllCars(Set<String> queries) {
        Map<String, Car> result = queries.stream()
                .map(i -> this.carsAvByParser.parseSite(i))
                .flatMap(List::stream)
                .collect(Collectors.toMap(Car::getHref, i -> i));
        log.info("All cars({}) -> {}", result.size(), result);
        return result;
    }

    private Set<String> getQueries() {
        Set<String> result = this.queryRepository
                .findAll()
                .stream()
                .map(Query::getHref)
                .collect(Collectors.toSet());
        log.info("All queries ({}) -> {}", result.size(), result);
        return result;
    }
}
