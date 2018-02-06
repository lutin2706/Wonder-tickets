package be.formation.spring.labo.service.impl;

import be.formation.spring.labo.model.entity.Authority;
import be.formation.spring.labo.model.entity.User;
import be.formation.spring.labo.repository.AuthorityRepository;
import be.formation.spring.labo.repository.UserRepository;
import be.formation.spring.labo.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("No user found for this username : " + username);
        }

        return user;
    }

    @Override
    public User register(String username, String password, String email) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.getAuthorities().add(getOrCreateAuthority("CUSTOMER"));
        user.setEmail(email);

        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        user = userRepository.save(user);

        return user;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public User registerEmployee(String username, String password, String email) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.getAuthorities().add(getOrCreateAuthority("EMPLOYEE"));
        user.setEmail(email);

        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        user = userRepository.save(user);

        return user;
    }

    @Override
    public User getUser(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void giveAuthority(String username, String authority) {

        User user = userRepository.findByUsername(username);
        user.getAuthorities().add(getOrCreateAuthority(authority));
        userRepository.save(user);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Authority getOrCreateAuthority(String name) {

        Authority authority = authorityRepository.findByAuthority("ROLE_" + name);
        if (authority == null) {
            authority = authorityRepository.save(new Authority("ROLE_" + name));
        }

        return authority;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getEmployeeList() {

        return userRepository.findAllByAuthoritiesContains(getOrCreateAuthority(Authority.EMPLOYEE));
    }
}
