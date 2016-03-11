package com.iqbuzz.ticket.service;

import com.iqbuzz.ticket.entity.Ticket;
import com.iqbuzz.ticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    private TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> list(String seance){
        return this.ticketRepository.list(seance);
    }

    public void sale(List<com.iqbuzz.ticket.dto.Ticket> ticketList){
        List<Ticket> ticketEntityList = new ArrayList<>();
        for(com.iqbuzz.ticket.dto.Ticket ticketDto : ticketList){
            Ticket ticket = new Ticket();
            ticket.setRow(ticketDto.getRow());
            ticket.setSeat(ticketDto.getSeat());
            ticket.setSeance(ticketDto.getSeance());
            ticketEntityList.add(ticket);
        }
        this.ticketRepository.update(ticketEntityList);
    }

}
