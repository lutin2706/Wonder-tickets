package be.formation.spring.labo.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Booking extends BaseEntity {

    @Column(nullable = false)
    private LocalDateTime bookingDateTime;

    @Column(nullable = false)
    private String sendingMode;

    @ManyToOne
    private Event event;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy="booking", fetch= FetchType.EAGER)
    private Set<Ticket> tickets = new HashSet<>();

    public Booking() {
    }

    public Booking(LocalDateTime bookingDateTime, String sendingMode, Event event, User user) {
        this.bookingDateTime = bookingDateTime;
        this.sendingMode = sendingMode;
        this.event = event;
        this.user = user;
    }

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public void setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    public String getSendingMode() {
        return sendingMode;
    }

    public void setSendingMode(String sendingMode) {
        this.sendingMode = sendingMode;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
