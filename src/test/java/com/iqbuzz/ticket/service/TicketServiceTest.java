package com.iqbuzz.ticket.service;


import com.iqbuzz.ticket.config.WebConfig;
import com.iqbuzz.ticket.dto.Ticket;
import com.iqbuzz.ticket.exception.LastRowValidateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
public class TicketServiceTest {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private Environment env;

    @Test
    public void validateLastRow() {
        int rowCount = Integer.valueOf(env.getProperty("row.count"));

        List<Ticket> ticketList = new ArrayList<>();

        Ticket ticket = new Ticket();
        ticket.setSeat(1);
        ticket.setRow(rowCount);
        ticketList.add(ticket);

        ticket = new Ticket();
        ticket.setSeat(2);
        ticket.setRow(rowCount);
        ticketList.add(ticket);

        ticket = new Ticket();
        ticket.setSeat(5);
        ticket.setRow(rowCount);
        ticketList.add(ticket);

        ticket = new Ticket();
        ticket.setSeat(6);
        ticket.setRow(rowCount);
        ticketList.add(ticket);

        boolean result = false;
        try {
            ticketService.validateLastRow(ticketList);
            result = true;
        } catch (LastRowValidateException ignored) {
        }
        assertTrue(result);


        ticketList = new ArrayList<>();

        ticket = new Ticket();
        ticket.setSeat(1);
        ticket.setRow(rowCount);
        ticketList.add(ticket);

        ticket = new Ticket();
        ticket.setSeat(2);
        ticket.setRow(rowCount);
        ticketList.add(ticket);

        ticket = new Ticket();
        ticket.setSeat(3);
        ticket.setRow(rowCount);
        ticketList.add(ticket);

        result = false;
        try {
            ticketService.validateLastRow(ticketList);
        } catch (LastRowValidateException e) {
            result = true;
        }
        assertTrue(result);
    }
}
