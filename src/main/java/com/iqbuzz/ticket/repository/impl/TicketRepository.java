package com.iqbuzz.ticket.repository.impl;


import com.iqbuzz.ticket.entity.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketRepository implements com.iqbuzz.ticket.repository.TicketRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public TicketRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Ticket> list(String seance) {
        try (Session session = sessionFactory.openSession()) {
            return (List<Ticket>)session.createCriteria(Ticket.class)
                    .add(Restrictions.eq("seance", seance))
                    .list();
        }
    }

    public void update(List<Ticket> ticketList){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            for(Ticket ticket : ticketList) {
                session.merge(ticket);
            }
            session.getTransaction().commit();
        }
    }

}
