package com.veselov.alex.racecar.controller;

import com.veselov.alex.racecar.data.dao.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CarController {
    @Autowired
    private AutoRepository repository;

    @GetMapping("/cars")
    public ModelAndView getCars(){
        ModelAndView view = new ModelAndView("cars");
        view.addObject("cars", repository.findAll());
        return view;
    }
}
