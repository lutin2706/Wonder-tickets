package be.formation.spring.labo.repository;

import be.formation.spring.labo.model.entity.Authority;
import be.formation.spring.labo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    List<User> findAllByAuthoritiesContains(Authority authority);
    
}
