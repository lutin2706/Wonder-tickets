package be.formation.spring.labo.service.impl;

import be.formation.spring.labo.model.entity.Venue;
import be.formation.spring.labo.repository.VenueRepository;
import be.formation.spring.labo.service.VenueService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;

    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    @PreAuthorize("hasRole('EMPLOYEE')")
    public void saveVenue(Venue venue) {
        venueRepository.save(venue);
    }

    @Override
    @PreAuthorize("hasRole('EMPLOYEE')")
    public List<Venue> getListVenue() {
        return venueRepository.findAll();
    }

    @Override
    public Venue get(long venueId) {
        return venueRepository.findOne(venueId);
    }
}
