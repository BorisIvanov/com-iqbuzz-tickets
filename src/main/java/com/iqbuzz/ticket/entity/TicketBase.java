package com.iqbuzz.ticket.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;


@MappedSuperclass
public class TicketBase {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(name = "seance", nullable = false)
    private String seance;
    @Column(name = "row", nullable = false)
    private int row;
    @Column(name = "seat", nullable = false)
    private int seat;

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

}
