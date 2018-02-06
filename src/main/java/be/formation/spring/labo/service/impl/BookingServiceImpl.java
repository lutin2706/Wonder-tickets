package be.formation.spring.labo.service.impl;

import be.formation.spring.labo.model.entity.Booking;
import be.formation.spring.labo.model.entity.Event;
import be.formation.spring.labo.repository.BookingRepository;
import be.formation.spring.labo.service.BookingService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }


    @Override
    @PreAuthorize("hasRole('CUSTOMER')")
    public void saveBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getListBooking() {
        return bookingRepository.findAll();
    }

    @Override
    @PreAuthorize("hasRole('CUSTOMER')")
    public Booking get(long bookingId) {
        return bookingRepository.findOne(bookingId);
    }

    @Override
    @PreAuthorize("hasRole('EMPLOYEE')")
    public List<Booking> findAllByEvent(Event event) {
        return bookingRepository.findAllByEvent(event);
    }

    @Override
    @PreAuthorize("hasRole('EMPLOYEE')")
    public void cancelBooking(Booking booking) {
         bookingRepository.delete(booking);
    }
}
