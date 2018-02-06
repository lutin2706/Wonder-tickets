package be.formation.spring.labo.service;

import be.formation.spring.labo.model.entity.Artist;

import java.util.List;

public interface ArtistService {

    void saveArtist(Artist artist);

    List<Artist> getAllArtists();

    Artist get(Long artistId);
}
