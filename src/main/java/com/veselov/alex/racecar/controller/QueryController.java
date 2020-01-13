package com.veselov.alex.racecar.controller;

import com.veselov.alex.racecar.data.entity.Query;
import com.veselov.alex.racecar.service.dbservice.QueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class QueryController {
    @Autowired
    private QueryService service;

    /**
     * It will trim all input strings
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/queries")
    public String showAllQueries(Model model) {
        List<Query> queries = this.service.findAllEncoded();
        model.addAttribute("queries", queries);
        model.addAttribute("query", new Query());
        return "queries";
    }

    @PostMapping("/queries")
    public String addOrUpdateQuery(@Valid @ModelAttribute("query") Query query, BindingResult result) {
        String view;
        if (result.hasErrors()) {
            log.error("There are invalid data from form -> {}", result);
            view = "queries";
        } else {
            log.info("Update query ({}) -> {}", query.getId(), query);
            this.service.save(query);
            view = "redirect:/queries";
        }
        return view;
    }

    @GetMapping("/delete_query/{id}")
    public String deleteQueryById(@PathVariable int id) {
        this.service.deleteById(id);
        return "redirect:/queries";
    }
}
