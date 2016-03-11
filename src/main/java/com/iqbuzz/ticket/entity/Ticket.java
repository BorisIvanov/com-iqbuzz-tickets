package com.iqbuzz.ticket.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(name = "seance")
    private String seance;
    @Column(name = "row")
    private int row;
    @Column(name = "seat")
    private int seat;/*
    @ManyToOne
    @JoinColumn(name = "reservation", nullable = true)
    private TicketReservation ticketReservation;*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeance() {
        return seance;
    }

    public void setSeance(String seance) {
        this.seance = seance;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }
/*
    public TicketReservation getTicketReservation() {
        return ticketReservation;
    }

    public void setTicketReservation(TicketReservation ticketReservation) {
        this.ticketReservation = ticketReservation;
    }*/
}
