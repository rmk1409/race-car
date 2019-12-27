package com.veselov.alex.racecar.controller;

import com.veselov.alex.racecar.data.dao.AutoRepository;
import com.veselov.alex.racecar.service.parser.CarsAvByParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class CarController {
    @Autowired
    private AutoRepository repository;
    @Autowired
    private CarsAvByParser parser;

    @GetMapping("/find")
    public String findCars(@RequestParam String query, Model model) {
        log.info("In controller, parsing the query -> {}", query);
        model.addAttribute("cars", this.parser.parseSite(query));
        return "welcome";
    }

    @GetMapping("/cars")
    public String getCars(Model model, @RequestParam(defaultValue = "1") int pageNumber) {
        log.info("Show all cars");
        final var carsByPage = 10;
        model.addAttribute("carQuantity", this.repository.count());
        model.addAttribute("cars", this.repository.findAll(PageRequest.of(pageNumber - 1, carsByPage)).toList());
        return "cars";
    }
}
