package be.formation.spring.labo.service;

import be.formation.spring.labo.model.entity.Ticket;
import be.formation.spring.labo.model.entity.Venue;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TicketService {

    void saveTicket(Ticket ticket);

    List<Ticket> getListTicket();

    Ticket get(long ticketId);

    void cancelTicket(Ticket ticket);
}
