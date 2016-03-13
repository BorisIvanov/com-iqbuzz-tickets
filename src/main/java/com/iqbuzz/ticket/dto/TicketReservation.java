package com.iqbuzz.ticket.dto;

import java.util.List;


public class TicketReservation {
    private List<Ticket> ticketList;
    private String person;

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
