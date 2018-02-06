package be.formation.spring.labo.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Venue extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String city;

    public Venue() {
    }

    public Venue(long id, String name, String city) {
        this.setId(id);
        this.name = name;
        this.city = city;
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
