package be.formation.spring.labo.service;

import be.formation.spring.labo.model.entity.Venue;

import java.util.List;

public interface VenueService {

    void saveVenue(Venue venue);

    List<Venue> getListVenue();

    Venue get(long venueId);

}
