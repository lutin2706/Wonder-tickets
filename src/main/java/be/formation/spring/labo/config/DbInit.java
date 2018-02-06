package be.formation.spring.labo.config;

import be.formation.spring.labo.model.entity.Artist;
import be.formation.spring.labo.model.entity.Authority;
import be.formation.spring.labo.model.entity.Event;
import be.formation.spring.labo.model.entity.Venue;
import be.formation.spring.labo.service.ArtistService;
import be.formation.spring.labo.service.EventService;
import be.formation.spring.labo.service.UserService;
import be.formation.spring.labo.service.VenueService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

@Configuration
public class DbInit implements InitializingBean {

    private final UserService userService;

    private final VenueService venueService;

    private final ArtistService artistService;

    private final EventService eventService;

    public DbInit(UserService userService, VenueService venueService, ArtistService artistService, EventService eventService) {
        this.userService = userService;
        this.venueService = venueService;
        this.artistService = artistService;
        this.eventService = eventService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

//        runAsAdmin();
//
//        /* Authorities */
//        userService.getOrCreateAuthority(Authority.ADMIN);
//        userService.getOrCreateAuthority(Authority.EMPLOYEE);
//        userService.getOrCreateAuthority(Authority.CUSTOMER);
//
//        /* Admin */
//        userService.register("admin", "password", "severine@bertholet-ferret.net");
//        userService.giveAuthority("admin", Authority.ADMIN);
//
//        endRunAsAdmin();
//
//        runAsEmployee();
//
//        /* Venues */
//        Venue foyau = new Venue(1, "Le Foyau", "Lustin");
//        Venue ccProf = new Venue(2,"Centre Culturel", "Profondeville");
//        Venue ccNamur = new Venue(3,"Centre Culturel", "Namur");
//        venueService.saveVenue(foyau);
//        venueService.saveVenue(ccProf);
//        venueService.saveVenue(ccNamur);
//
//        /* Artists */
//        Artist filles = new Artist(1,"Les filles du bord de Meuse", "Troupe de théâtre amateur féminine", "https://i.ytimg.com/vi/l0Llct5b3Qc/hqdefault.jpg");
//        Artist guiHome = new Artist(2,"Gui Home", "Humoriste solo namurois", "http://cdn2.artwhere.net/www.moustique.be/sites/default/files/styles/large/public/field/image/illu_031_guihome.jpg?itok=7KWn3Y2M");
//        Artist comediens = new Artist(3,"Les comédiens du Herdal", "Troupe de théâtre amateur masculine", "http://www.fecota.eu/files/2012/03/2014-04-Visite-Huy-2.jpg");
//        artistService.saveArtist(filles);
//        artistService.saveArtist(guiHome);
//        artistService.saveArtist(comediens);
//
//        /* Event */
//        Event laliland = new Event(1,"LaLi Land", "Jolie pièce de théâtre", 100, LocalDateTime.of(2018, 03,03,20,0),"théâtre", 1500, true, ccProf);
//        laliland.getArtists().add(filles);
//        eventService.saveEvent(laliland);
//
//        Event detend = new Event(2,"Gui Home vous détend", "Un spectacle super-drôle", 250, LocalDateTime.of(2018, 06, 27,20,30), "humour", 2500, true, ccNamur);
//        detend.getArtists().add(guiHome);
//        eventService.saveEvent(detend);
//
//        Event jacadi = new Event(3,"Jacques a dit", "Une pièce d'amour et d'amitié", 100, LocalDateTime.of(2018, 04, 13, 19, 30), "théâtre", 1590, true, ccProf);
//        jacadi.getArtists().add(comediens);
//        eventService.saveEvent(jacadi);
//
//        Event passe = new Event(4,"Spectacle d'automne", "Le spectacle des filles pour l'automne", 100, LocalDateTime.of(2017, 9, 27, 19, 30), "théâtre", 1690, true, ccProf);
//        passe.getArtists().add(filles);
//        eventService.saveEvent(passe);
//
//        endRunAsEmployee();
    }

    private void runAsAdmin() {
        final AnonymousAuthenticationToken token = new AnonymousAuthenticationToken("system", "system", Collections.singletonList(new Authority("ROLE_ADMIN")));
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    private void endRunAsAdmin() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    private void runAsEmployee() {
        final AnonymousAuthenticationToken token = new AnonymousAuthenticationToken("system", "system", Collections.singletonList(new Authority("ROLE_EMPLOYEE")));
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    private void endRunAsEmployee() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
