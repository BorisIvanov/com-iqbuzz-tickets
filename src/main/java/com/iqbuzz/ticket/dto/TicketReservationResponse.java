package com.iqbuzz.ticket.dto;

import java.time.LocalTime;

public class TicketReservationResponse extends TicketBase {
    private String person;
    private LocalTime seance;

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public LocalTime getSeance() {
        return seance;
    }

    public void setSeance(LocalTime seance) {
        this.seance = seance;
    }
}
