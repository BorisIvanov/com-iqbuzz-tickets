package com.iqbuzz.ticket.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


import java.time.LocalTime;

import static javax.persistence.GenerationType.IDENTITY;


@MappedSuperclass
public class TicketBase {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Column(name = "seance", nullable = false)
    private LocalTime seance;
    @Column(name = "row", nullable = false)
    private int row;
    @Column(name = "seat", nullable = false)
    private int seat;
    @Column(name = "cost", nullable = false)
    private float cost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getSeance() {
        return seance;
    }

    public void setSeance(LocalTime seance) {
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

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
