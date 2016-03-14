package com.iqbuzz.ticket.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;

public class TicketReservationRequest {
    private String person;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
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
