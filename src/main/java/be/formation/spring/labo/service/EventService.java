package be.formation.spring.labo.service;

import be.formation.spring.labo.model.entity.Event;

import java.util.List;

public interface EventService {

    void saveEvent(Event event);

    List<Event> getActiveEventList();

    List<Event> getEventList();

    Event getEvent(Long id);

    void cancelEvent(Event event);
}
