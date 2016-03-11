package com.iqbuzz.ticket.repository;

import com.iqbuzz.ticket.entity.Ticket;

import java.util.List;

public interface TicketRepository {
    List<Ticket> list(String seance);
    void update(List<Ticket> ticket);
}
