package be.formation.spring.labo.model.form;

import be.formation.spring.labo.model.entity.Artist;
import be.formation.spring.labo.model.entity.BaseEntity;
import be.formation.spring.labo.model.entity.Event;
import be.formation.spring.labo.model.entity.Venue;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EventForm {

    private long id;

    @NotNull(message = "Title is required.")
    @Size(min = 3, max = 255, message = "Title should be between 3 and 255 characters long.")
    private String title;

    @NotNull(message = "Description is required.")
    @Size(min = 3, max = Short.MAX_VALUE, message = "Description should be between 3 and 32767 characters long.")
    private String description;

    @NotNull(message = "Maximum number of tickets is required")
    @Min(value = 10, message = "Minimum number of tickets is 10")
    private int ticketsMax;

    @NotNull(message = "Date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull(message = "Time is required")
    private LocalTime time;

    @NotNull(message = "Type is required")
    private String type;

    @Min(value = 0, message = "Price must be positive")
    private float price;

    @NotNull(message = "Venue is required")
    private long venueId;

    private List<Long> artists;

    private List<Venue> venuesAvailable;
    private List<Artist> artistsAvailable;
    private List<String> types = Arrays.asList("théâtre", "musique", "humour");

    public EventForm() {
    }

    public EventForm(Event event) {
        this.id = event.getId();
        this.title = event.getTitle();
        this.description = event.getDescription();
        this.ticketsMax = event.getTicketsAvailable();
        this.date = event.getDateTime().toLocalDate();
        this.time = event.getDateTime().toLocalTime();
        this.type = event.getType();
        this.price = event.getPrice()/100;
        this.venueId = event.getVenue().getId();
        this.artists = event.getArtists().stream().map(BaseEntity::getId).collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getTicketsMax() {
        return ticketsMax;
    }

    public void setTicketsMax(int ticketsMax) {
        this.ticketsMax = ticketsMax;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public long getVenueId() {
        return venueId;
    }

    public void setVenueId(long venueId) {
        this.venueId = venueId;
    }

    public List<Long> getArtists() {
        return artists;
    }

    public void setArtists(List<Long> artists) {
        this.artists = artists;
    }

    public List<Venue> getVenuesAvailable() {
        return venuesAvailable;
    }

    public void setVenuesAvailable(List<Venue> venuesAvailable) {
        this.venuesAvailable = venuesAvailable;
    }

    public List<Artist> getArtistsAvailable() {
        return artistsAvailable;
    }

    public void setArtistsAvailable(List<Artist> artistsAvailable) {
        this.artistsAvailable = artistsAvailable;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
