package com.iqbuzz.ticket.controllers;

import com.iqbuzz.ticket.service.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;


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
    public ModelAndView index(@CookieValue("activeSeance") Optional<String> seance, HttpServletRequest request) {
        ModelAndView result = new ModelAndView("index");
        result.addObject("rows", env.getProperty("row.count"));
        result.addObject("seats", env.getProperty("seat.count"));
        List<String> seanceList = seanceService.list();
        result.addObject("seances", seanceList);
        if(seance.isPresent()) {
            result.addObject("seanceActive", seance.get());
        } else {
            result.addObject("seanceActive", seanceList.get(0));
        }
        result.addObject("environment", env.getProperty("application.environment"));
        return result;
    }

}