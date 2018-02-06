package be.formation.spring.labo.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Artist extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    private String imageUrl;

//    @ManyToMany
//    private Set<Event> events = new HashSet<>();


    public Artist() {
    }

    public Artist(long id, String name, String description, String imageUrl) {
        this.setId(id);
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
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
