package be.formation.spring.labo.service.impl;

import be.formation.spring.labo.model.entity.Ticket;
import be.formation.spring.labo.repository.TicketRepository;
import be.formation.spring.labo.service.TicketService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    @PreAuthorize("hasRole('CUSTOMER')")
    public void saveTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getListTicket() {
        return ticketRepository.findAll();
    }

    @Override
    @PreAuthorize("hasRole('CUSTOMER')")
    public Ticket get(long ticketId) {
        return ticketRepository.findOne(ticketId);
    }

    @Override
    @PreAuthorize("hasRole('EMPLOYEE')")
    public void cancelTicket(Ticket ticket) {
        ticketRepository.delete(ticket);
    }
}
