package com.iqbuzz.ticket.dto;

public class TicketReservationRequest {
    private String person;
    private String seance;

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getSeance() {
        return seance;
    }

    public void setSeance(String seance) {
        this.seance = seance;
    }
}
