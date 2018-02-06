package be.formation.spring.labo.repository;

import be.formation.spring.labo.model.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    @Override
    List<Artist> findAll();
}
