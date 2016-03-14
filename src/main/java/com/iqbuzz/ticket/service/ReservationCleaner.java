package com.iqbuzz.ticket.service;

import com.iqbuzz.ticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;


@Component
public class ReservationCleaner {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final int interval = 60 * 1000;
    private TicketRepository ticketRepository;

    @Autowired
    public ReservationCleaner(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Scheduled(fixedRate = interval)
    public void reportCurrentTime() {
        ticketRepository.reservationClean();
    }

}
