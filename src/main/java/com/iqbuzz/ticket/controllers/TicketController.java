package com.iqbuzz.ticket.controllers;

import com.iqbuzz.ticket.dto.Ticket;
import com.iqbuzz.ticket.dto.TicketReservation;
import com.iqbuzz.ticket.dto.TicketReservationRequest;
import com.iqbuzz.ticket.exception.LastRowValidateException;
import com.iqbuzz.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TicketController extends BaseController {

    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @RequestMapping(value = "/ticket/list/{seance}", method = RequestMethod.GET)
    @ResponseBody
    public List list(@PathVariable("seance") String seance) {
        return ticketService.list(seance);
    }

    @RequestMapping(
            value = "/ticket/sale",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public Map<String, String> sale(@RequestBody List<Ticket> ticketList) throws LastRowValidateException {
        ticketService.sale(ticketList);
        return new HashMap<>();
    }

    @RequestMapping(
            value = "/ticket/reservation",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public Map<String, String> reservation(@RequestBody TicketReservation ticketReservation)
            throws LastRowValidateException {
        ticketService.reservation(ticketReservation);
        return new HashMap<>();
    }

    @RequestMapping(
            value = "/ticket/reservation/{person}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public List reservationByPerson(@PathVariable("person") String person) {
        return ticketService.reservationByPerson(person);
    }

    @RequestMapping(
            value = "/ticket/reservation/sale",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public Map<String, String> reservationSale(@RequestBody TicketReservationRequest request) {
        ticketService.reservationSale(request.getPerson(), request.getSeance());
        return new HashMap<>();
    }

}
