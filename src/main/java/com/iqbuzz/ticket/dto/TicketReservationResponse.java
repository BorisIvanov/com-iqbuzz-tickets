package com.iqbuzz.ticket.dto;

public class TicketReservationResponse extends Ticket {
    private String person;

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
