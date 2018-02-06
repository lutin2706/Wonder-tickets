package be.formation.spring.labo.repository;

import be.formation.spring.labo.model.entity.Ticket;
import be.formation.spring.labo.model.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
