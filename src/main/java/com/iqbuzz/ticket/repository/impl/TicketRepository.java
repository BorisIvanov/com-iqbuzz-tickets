package com.iqbuzz.ticket.repository.impl;

import com.iqbuzz.ticket.dto.TicketResponse;
import com.iqbuzz.ticket.entity.TicketBase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketRepository implements com.iqbuzz.ticket.repository.TicketRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public TicketRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List ticketList(String seance) {
        try (Session session = sessionFactory.openSession()) {
            return session.createSQLQuery(
                    "SELECT t.row as \"row\", t.seat as \"seat\", 0 as \"type\" " +
                            "FROM ticket t " +
                            "WHERE t.seance = :seance " +
                            "UNION ALL " +
                            "SELECT tr.row as \"row\", tr.seat as \"seat\", 1 as \"type\" " +
                            "FROM ticket_reservation tr " +
                            "WHERE tr.seance = :seance"
            ).setParameter("seance", seance)
                    .setResultTransformer(Transformers.aliasToBean(TicketResponse.class))
                    .list();
        }
    }

    @Override
    public <T extends TicketBase> void update(List<T> ticketList) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            for (T ticket : ticketList) {
                session.merge(ticket);
            }
            session.getTransaction().commit();
        }
    }

    @Override
    public void reservationToTicket(String person, String seance) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session
                    .createSQLQuery(
                            "INSERT INTO ticket (seance, row, seat) " +
                                    "SELECT tr.seance, tr.row, tr.seat FROM ticket_reservation tr " +
                                    "WHERE tr.person = :person AND tr.seance = :seance")
                    .setParameter("person", person)
                    .setParameter("seance", seance)
                    .executeUpdate();
            session
                    .createSQLQuery("DELETE FROM ticket_reservation WHERE person = :person AND seance = :seance")
                    .setParameter("person", person)
                    .setParameter("seance", seance)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public List reservationByPerson(String person) {
        try (Session session = sessionFactory.openSession()) {
            return session.createSQLQuery(
                    "SELECT tr.person as \"person\", tr.seance as \"seance\", tr.row as \"row\", tr.seat as \"seat\" " +
                            "FROM ticket_reservation tr " +
                            "WHERE tr.person = :person ORDER BY tr.seance, tr.row, tr.seat"
            ).setParameter("person", person)
                    .setResultTransformer(Transformers.aliasToBean(com.iqbuzz.ticket.dto.TicketReservationResponse.class))
                    .list();
        }
    }


}
