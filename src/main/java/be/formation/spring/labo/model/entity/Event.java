package be.formation.spring.labo.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Event extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    private int ticketsAvailable;

    private LocalDateTime dateTime;

    @Column(nullable = false)
    private String type;

    private int price;

    private boolean enabled;

    @ManyToMany
    private Set<Artist> artists = new HashSet<>();

    @ManyToOne
    @JoinColumn(nullable = false)
    private Venue venue;

    public Event() {
    }

    public Event(long id, String title, String description, int ticketsAvailable, LocalDateTime dateTime, String type, int price, boolean enabled, Venue venue) {
        this.setId(id);
        this.title = title;
        this.description = description;
        this.ticketsAvailable = ticketsAvailable;
        this.dateTime = dateTime;
        this.type = type;
        this.price = price;
        this.enabled = enabled;
        this.venue = venue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTicketsAvailable() {
        return ticketsAvailable;
    }

    public void setTicketsAvailable(int ticketsAvailable) {
        this.ticketsAvailable = ticketsAvailable;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }
}
