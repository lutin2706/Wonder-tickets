package be.formation.spring.labo.service.impl;

import be.formation.spring.labo.model.entity.Artist;
import be.formation.spring.labo.repository.ArtistRepository;
import be.formation.spring.labo.service.ArtistService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    @PreAuthorize("hasRole('EMPLOYEE')")
    public void saveArtist(Artist artist) {
        artistRepository.save(artist);
    }

    @Override
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    @Override
    public Artist get(Long artistId) {
        return artistRepository.findOne(artistId);
    }
}
