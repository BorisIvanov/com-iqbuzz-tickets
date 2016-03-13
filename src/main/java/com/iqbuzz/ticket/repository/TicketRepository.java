package com.iqbuzz.ticket.repository;

import com.iqbuzz.ticket.dto.TicketResponse;
import com.iqbuzz.ticket.entity.Ticket;
import com.iqbuzz.ticket.entity.TicketBase;
import com.iqbuzz.ticket.entity.TicketReservation;

import java.time.LocalTime;
import java.util.List;

public interface TicketRepository {
    List ticketList(LocalTime seance);
    <T extends TicketBase> void update(List<T> ticketList);
    List reservationByPerson(String person);
    void reservationToTicket(String person, String seance);
}
