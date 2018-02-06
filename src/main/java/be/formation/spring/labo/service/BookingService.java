package be.formation.spring.labo.service;

import be.formation.spring.labo.model.entity.Booking;
import be.formation.spring.labo.model.entity.Event;
import be.formation.spring.labo.model.entity.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BookingService {

    void saveBooking(Booking booking);

    List<Booking> getListBooking();

    Booking get(long bookingId);

    List<Booking> findAllByEvent(Event event);

    void cancelBooking(Booking booking);
}
