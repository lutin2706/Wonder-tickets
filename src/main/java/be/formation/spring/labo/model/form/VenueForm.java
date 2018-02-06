package be.formation.spring.labo.model.form;

import be.formation.spring.labo.model.entity.Venue;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class VenueForm {

    private long id;

    @NotNull(message = "Name is required.")
    @Size(min = 3, max = 255, message = "Name should be between 3 and 255 characters long.")
    private String name;

    @NotNull(message = "City is required.")
    @Size(min = 3, max = 255, message = "City should be between 3 and 255 characters long.")
    private String city;

    public VenueForm() {
    }

    public VenueForm(Venue venue) {
        this.id = venue.getId();
        this.name = venue.getName();
        this.city = venue.getCity();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
