package com.veselov.alex.racecar.controller;

import com.veselov.alex.racecar.service.CarsAvByParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RaceOrDieController {
    @Autowired
    private CarsAvByParser parser;

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/find")
    public ModelAndView findCars(@RequestParam String query) {
        ModelAndView view = new ModelAndView("/welcome");
        view.addObject("cars", parser.handlePagination(query));
        return view;
    }
}
