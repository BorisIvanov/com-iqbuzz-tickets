package com.iqbuzz.ticket.controllers;


import com.iqbuzz.ticket.exception.IqbuzzTicketException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

    @ExceptionHandler(IqbuzzTicketException.class)
    @ResponseBody
    public Map<String, String> applicationException(IqbuzzTicketException applicationException) {
        Map<String, String> result = new HashMap();
        result.put("error", applicationException.getMessage());
        return result;
    }


}
