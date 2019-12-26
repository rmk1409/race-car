package com.veselov.alex.racecar.controller;

import com.veselov.alex.racecar.data.dao.QueryRepository;
import com.veselov.alex.racecar.data.entity.Query;
import com.veselov.alex.racecar.service.parser.CarsAvByParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class QueryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueryController.class);
    @Autowired
    private QueryRepository repository;
    @Autowired
    private CarsAvByParser parser;

    @GetMapping("/queries")
    public ModelAndView showQueries() {
        ModelAndView view = new ModelAndView("/queries");
        List<Query> queries = this.repository.findAll();
        queries.forEach(
                i -> {
                    try {
                        i.setHref(URLEncoder.encode(i.getHref(), "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        LOGGER.error("There is an exception -> {}, {}", e, e.getMessage());
                    }
                }
        );
        view.addObject("queries", queries);
        return view;
    }

    @PostMapping("/queries")
    public String addQueries(Query query) throws UnsupportedEncodingException {
        query.setHref(URLEncoder.encode(query.getHref(), "UTF-8"));
        this.repository.save(query);
        return "redirect:/queries";
    }

    @GetMapping("/delete_query/{id}")
    public String deleteQuery(@PathVariable int id) {
        this.repository.deleteById(id);
        return "redirect:/queries";
    }
}
