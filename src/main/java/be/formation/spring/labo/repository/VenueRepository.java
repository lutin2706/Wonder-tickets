package be.formation.spring.labo.repository;

import be.formation.spring.labo.model.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VenueRepository extends JpaRepository<Venue, Long> {

}
