package com.iqbuzz.ticket.service;


import com.iqbuzz.ticket.dto.Ticket;
import org.junit.Test;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.StandardEnvironment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketServiceTest {

    @Test
    public void validateLastRow(){
        Environment env = new StandardEnvironment();
        MutablePropertySources propertySources = env.g
        Map myMap = new HashMap();
        myMap.put("xyz", "myValue");
        propertySources.addFirst(new MapPropertySource("MY_MAP", myMap));

        TicketService ticketService = new TicketService(null, env);
        List<Ticket> ticketList = new ArrayList<>();
        ticketService.validateLastRow(ticketList);
    }
}
