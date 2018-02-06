package be.formation.spring.labo.service.impl;

import be.formation.spring.labo.model.entity.Event;
import be.formation.spring.labo.repository.EventRepository;
import be.formation.spring.labo.service.EventService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    @PreAuthorize("hasRole('EMPLOYEE')")
    public void saveEvent(Event event) {
        eventRepository.save(event);
    }

    @Override
    public List<Event> getActiveEventList() {
        return eventRepository.findAllByEnabledIsTrueAndDateTimeIsAfter(LocalDateTime.now());
    }

    @Override
    @PreAuthorize("hasRole('EMPLOYEE')")
    public List<Event> getEventList() {
        return eventRepository.findAll();
    }

    @Override
    public Event getEvent(Long id) {
        return eventRepository.findOne(id);
    }

    @Override
    @PreAuthorize("hasRole('EMPLOYEE')")
    public void cancelEvent(Event event) {
        event.setEnabled(false);
        eventRepository.save(event);
    }
}
