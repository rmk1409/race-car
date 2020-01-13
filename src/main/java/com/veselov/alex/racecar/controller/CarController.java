package com.veselov.alex.racecar.controller;

import com.veselov.alex.racecar.data.entity.Car;
import com.veselov.alex.racecar.service.dbservice.CarService;
import com.veselov.alex.racecar.service.parser.SiteParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CarController {
    public static final int CARS_BY_PAGE = 10;

    @Autowired
    private CarService service;
    @Autowired
    private SiteParser parser;

    @GetMapping("/find")
    public String findCarsByQuery(@RequestParam String query, Model model) {
        List<Car> cars = this.parser.parseSite(query);
        model.addAttribute("cars", cars);
        model.addAttribute("carQuantity", cars.size());
        return "cars";
    }

    @GetMapping("/cars")
    public String showAllCars(Model model, @RequestParam(defaultValue = "1") int pageNumber) {
        model.addAttribute("carQuantity", this.service.count());
        model.addAttribute("cars", this.service.findAll(PageRequest.of(pageNumber - 1, CARS_BY_PAGE)).toList());
        return "cars";
    }
}
