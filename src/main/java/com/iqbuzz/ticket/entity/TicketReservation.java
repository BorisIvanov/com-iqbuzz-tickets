package com.iqbuzz.ticket.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalTime;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "ticket_reservation")
public class TicketReservation extends TicketBase {

    @Column(name = "person", nullable = false)
    private String person;
    @Column(name = "time", nullable = false)
    private LocalTime time;

    public TicketReservation() {
        this.time = LocalTime.now();
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

}
