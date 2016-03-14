package com.iqbuzz.ticket.service;

import com.iqbuzz.ticket.entity.Ticket;
import com.iqbuzz.ticket.entity.TicketReservation;
import com.iqbuzz.ticket.exception.LastRowValidateException;
import com.iqbuzz.ticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    private TicketRepository ticketRepository;
    private Environment env;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);

    @Autowired
    public TicketService(TicketRepository ticketRepository, Environment env) {
        this.ticketRepository = ticketRepository;
        this.env = env;
    }

    public List list(String seance) {
        return this.ticketRepository.ticketList(LocalTime.from(formatter.parse(seance)));
    }

    public void sale(List<com.iqbuzz.ticket.dto.Ticket> ticketList) throws LastRowValidateException {
        validateLastRow(ticketList);
        List<Ticket> ticketEntityList = new ArrayList<>();
        for (com.iqbuzz.ticket.dto.Ticket ticketDto : ticketList) {
            Ticket ticket = new Ticket();
            ticket.setRow(ticketDto.getRow());
            ticket.setSeat(ticketDto.getSeat());
            ticket.setSeance(LocalTime.from(formatter.parse(ticketDto.getSeance())));
            ticketEntityList.add(ticket);
        }
        this.ticketRepository.update(ticketEntityList);
    }

    public void reservation(com.iqbuzz.ticket.dto.TicketReservation ticketReservation) throws LastRowValidateException {
        validateLastRow(ticketReservation.getTicketList());
        List<TicketReservation> ticketEntityList = new ArrayList<>();
        for (com.iqbuzz.ticket.dto.Ticket ticketDto : ticketReservation.getTicketList()) {
            TicketReservation ticket = new TicketReservation();
            ticket.setRow(ticketDto.getRow());
            ticket.setSeat(ticketDto.getSeat());
            ticket.setSeance(LocalTime.from(formatter.parse(ticketDto.getSeance())));
            ticket.setPerson(ticketReservation.getPerson());
            ticketEntityList.add(ticket);
        }
        this.ticketRepository.update(ticketEntityList);
    }

    public void validateLastRow(List<com.iqbuzz.ticket.dto.Ticket> ticketList) throws LastRowValidateException {
        int seatCountInRow = Integer.valueOf(env.getProperty("seat.count"));
        int rowCount = Integer.valueOf(env.getProperty("row.count"));
        boolean[] lastRow = new boolean[seatCountInRow];
        for (com.iqbuzz.ticket.dto.Ticket ticket : ticketList) {
            if (ticket.getRow() == rowCount) {
                lastRow[ticket.getSeat() - 1] = true;
            }
        }

        for (int i = 0; i < seatCountInRow; i += 2) {
            if ((lastRow[i] | lastRow[i + 1]) && !((lastRow[i] & lastRow[i + 1]))) {
                throw new LastRowValidateException();
            }
        }
    }

    public List reservationByPerson(String person) {
        return this.ticketRepository.reservationByPerson(person);
    }


    public void reservationSale(String person, String sale) {
        this.ticketRepository.reservationToTicket(person, sale);
    }
}
