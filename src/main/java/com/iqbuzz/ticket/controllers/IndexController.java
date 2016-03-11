package com.iqbuzz.ticket.controllers;

import com.iqbuzz.ticket.service.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class IndexController {

    private SeanceService seanceService;

    @Autowired
    public IndexController(SeanceService seanceService) {
        this.seanceService = seanceService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView result = new ModelAndView("index");
        result.addObject("rows", 5);
        result.addObject("seats", 20);
        result.addObject("seances", seanceService.list());
        return result;
    }

}