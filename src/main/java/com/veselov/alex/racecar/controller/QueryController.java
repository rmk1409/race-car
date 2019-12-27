package com.veselov.alex.racecar.controller;

import com.veselov.alex.racecar.data.entity.Query;
import com.veselov.alex.racecar.service.QueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class QueryController {
    @Autowired
    private QueryService service;

    @GetMapping("/queries")
    public String showQueries(Model model) {
        log.info("Finding all queries");
        List<Query> queries = this.service.findAll();
        model.addAttribute("queries", queries);
        model.addAttribute("query", new Query());
        return "queries";
    }

    @PostMapping("/queries")
    public String addQueries(@Valid @ModelAttribute("query") Query query, BindingResult result) {
        String view;
        log.info("Adding or updating a query -> {}", query);
        if (result.hasErrors()) {
            view = "queries";
        } else {
            this.service.save(query);
            view = "redirect:/queries";
        }
        return view;
    }

    @GetMapping("/delete_query/{id}")
    public String deleteQuery(@PathVariable int id) {
        log.info("Deleting the query with -> id({})", id);
        this.service.deleteById(id);
        return "redirect:/queries";
    }
}
