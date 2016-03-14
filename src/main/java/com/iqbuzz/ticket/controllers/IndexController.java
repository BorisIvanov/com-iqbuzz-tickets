package com.iqbuzz.ticket.controllers;

import com.iqbuzz.ticket.service.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class IndexController {

    private SeanceService seanceService;
    private Environment env;

    @Autowired
    public IndexController(SeanceService seanceService, Environment env) {
        this.seanceService = seanceService;
        this.env = env;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView result = new ModelAndView("index");
        result.addObject("rows", env.getProperty("row.count"));
        result.addObject("seats", env.getProperty("seat.count"));
        result.addObject("seances", seanceService.list());
        result.addObject("environment", env.getProperty("application.environment"));
        return result;
    }

}