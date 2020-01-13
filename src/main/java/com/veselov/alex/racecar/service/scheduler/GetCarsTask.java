package com.veselov.alex.racecar.service.scheduler;

import com.veselov.alex.racecar.data.entity.Car;
import com.veselov.alex.racecar.data.entity.Query;
import com.veselov.alex.racecar.service.dbservice.CarService;
import com.veselov.alex.racecar.service.dbservice.QueryService;
import com.veselov.alex.racecar.service.parser.SiteParser;
import com.veselov.alex.racecar.service.telegram.Bot;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class GetCarsTask {
    Map<String, Car> carCache = new HashMap<>();
    @Autowired
    CarService carService;
    @Autowired
    QueryService queryService;
    @Autowired
    SiteParser carsAvBySiteParser;
    @Autowired
    Bot bot;

    /**
     * Every 5 min parses all queries from DB, find cars by these queries.
     * After that it puts it in local cache and it sends found cars to telegram chats.
     */
    @Scheduled(cron = "0 0/5 * * * *")
    public void getCarsByScheduler() {
        Set<String> queries = this.getHrefSetFromQueries();
        Map<String, Car> cars = this.getAllCars(queries);
        this.removeDuplicates(cars);
        log.info("Unique cars({}) -> \n{}", cars.size(), cars);
        this.carService.saveAll(cars.values());
        this.carCache.putAll(cars);
        this.sendCarsToTelegram(cars);
    }

    private Set<String> getHrefSetFromQueries() {
        Set<String> result = this.queryService
                .findAll()
                .stream()
                .map(Query::getHref)
                .collect(Collectors.toSet());
        log.info("All queries ({}) -> {}", result.size(), result);
        return result;
    }

    private Map<String, Car> getAllCars(Set<String> queries) {
        Map<String, Car> result = queries.stream()
                .map(i -> this.carsAvBySiteParser.parseSite(i))
                .flatMap(List::stream)
                .collect(Collectors.toMap(Car::getHref, i -> i));
        log.info("All cars({}) -> {}", result.size(), result.values());
        return result;
    }

    private void removeDuplicates(Map<String, Car> cars) {
        cars.keySet()
                .removeAll(carCache.keySet());
    }

    /**
     * It sends cars to telegram chats.
     *
     * @param cars
     */
    private void sendCarsToTelegram(Map<String, Car> cars) {
        try {
            this.bot.execute(new SendMessage(this.bot.getChatId(), "Send cars to Telegram"));
            for (Map.Entry<String, Car> currentCar : cars.entrySet()) {
                SendMessage msg = new SendMessage(this.bot.getChatId(), currentCar.getValue().toString());
                msg.enableMarkdown(true);
                this.bot.execute(msg);
            }
        } catch (TelegramApiException e) {
            log.error("There is an error -> {}, {}", e, e.getMessage());
        }
    }
}
