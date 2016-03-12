package com.iqbuzz.ticket.service;

import com.iqbuzz.ticket.entity.Ticket;
import com.iqbuzz.ticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    private TicketRepository ticketRepository;
    private Environment env;

    @Autowired
    public TicketService(TicketRepository ticketRepository, Environment env) {
        this.ticketRepository = ticketRepository;
        this.env = env;
    }

    public List<Ticket> list(String seance) {
        return this.ticketRepository.list(seance);
    }

    public void sale(List<com.iqbuzz.ticket.dto.Ticket> ticketList) {
        List<Ticket> ticketEntityList = new ArrayList<>();
        for (com.iqbuzz.ticket.dto.Ticket ticketDto : ticketList) {
            Ticket ticket = new Ticket();
            ticket.setRow(ticketDto.getRow());
            ticket.setSeat(ticketDto.getSeat());
            ticket.setSeance(ticketDto.getSeance());
            ticketEntityList.add(ticket);
        }
        this.ticketRepository.update(ticketEntityList);
    }


    public boolean validateLastRow(List<com.iqbuzz.ticket.dto.Ticket> ticketList) {
        int seatCountInRow = Integer.valueOf(env.getProperty("seat.count"));
        int rowCount = Integer.valueOf(env.getProperty("row.count"));
        boolean[] lastRow = new boolean[seatCountInRow];
        for (com.iqbuzz.ticket.dto.Ticket ticket : ticketList) {
            if (ticket.getRow() == rowCount) {
                lastRow[ticket.getSeat() - 1] = true;
            }
        }

        for (int i = 0; i < seatCountInRow; i += 2) {
            if ((lastRow[i] | lastRow[i + 1]) && !((lastRow[i] & lastRow[i + 1]))){
                break;
            }
        }
        return false;
    }

}
