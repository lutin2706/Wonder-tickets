package be.formation.spring.labo.service;

import be.formation.spring.labo.model.entity.Authority;
import be.formation.spring.labo.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String email);

    void giveAuthority(String username, String authority);

    Authority getOrCreateAuthority(String name);

    List<User> getEmployeeList();

    User registerEmployee(String username, String password, String email);

    User getUser(String name);
}
