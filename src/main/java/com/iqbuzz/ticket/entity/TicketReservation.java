package com.iqbuzz.ticket.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalTime;

@Entity
@Table(name = "ticket_reservation")
public class TicketReservation extends TicketBase {

    @Column(name = "person", nullable = false)
    private String person;
    @JsonIgnore
    @Column(name = "reservation_time", nullable = false)
    private LocalTime reservationTime;

    public TicketReservation(){
        reservationTime = LocalTime.now();
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public LocalTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalTime reservationTime) {
        this.reservationTime = reservationTime;
    }
}
