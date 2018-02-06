package be.formation.spring.labo.repository;

import be.formation.spring.labo.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

   List<Event> findAllByEnabledIsTrueAndDateTimeIsAfter(LocalDateTime dateTime);
}
