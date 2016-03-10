package com.iqbuzz.ticket.controllers;

import com.iqbuzz.ticket.service.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@Controller
public class IndexController {

    @Autowired
    private SeanceService seanceService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView result = new ModelAndView("index");
        result.addObject("rows", 5);
        result.addObject("seats", 20);
        result.addObject("seances", seanceService.list());
        return result;
    }

}
