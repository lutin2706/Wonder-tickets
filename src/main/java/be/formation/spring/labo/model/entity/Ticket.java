package be.formation.spring.labo.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public class Ticket extends BaseEntity {

    @ManyToOne
    private Event event;

    private UUID uuid;

    @ManyToOne
    private Booking booking;

    public Ticket() {
    }

    public Ticket(Event event, UUID uuid, Booking booking) {
        this.event = event;
        this.uuid = uuid;
        this.booking = booking;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
