package be.formation.spring.labo.model.form;

import be.formation.spring.labo.model.entity.Artist;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ArtistForm {

    private long id;

    @NotNull(message = "Name is required.")
    @Size(min = 3, max = 255, message = "Name should be between 3 and 255 characters long.")
    private String name;

    @Size(min = 3, max = Short.MAX_VALUE, message = "Description should be between 3 and 32767 characters long.")
    private String description;

    @Size(min = 3, max = 255, message = "Image URL should be between 3 and 255 characters long.")
    private String imageUrl;

    public ArtistForm() {
    }

    public ArtistForm(Artist artist) {
        this.id = artist.getId();
        this.name = artist.getName();
        this.description = artist.getDescription();
        this.imageUrl = artist.getImageUrl();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
