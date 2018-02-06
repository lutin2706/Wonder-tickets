package be.formation.spring.labo.controller;

import be.formation.spring.labo.model.entity.Booking;
import be.formation.spring.labo.model.entity.Event;
import be.formation.spring.labo.model.entity.Ticket;
import be.formation.spring.labo.model.form.EventForm;
import be.formation.spring.labo.service.*;
import be.formation.spring.labo.service.impl.EmailServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
public class EventController {

    private final EventService eventService;

    private final VenueService venueService;

    private final ArtistService artistService;

    private final BookingService bookingService;

    private final TicketService ticketService;

    private final EmailServiceImpl emailService;

    public EventController(EventService eventService, VenueService venueService, ArtistService artistService, BookingService bookingService, TicketService ticketService, EmailServiceImpl emailService) {
        this.eventService = eventService;
        this.venueService = venueService;
        this.artistService = artistService;
        this.bookingService = bookingService;
        this.ticketService = ticketService;
        this.emailService = emailService;
    }

    @GetMapping("/admin/event")
    public String getEventForm(Model model) {
        EventForm eventForm = new EventForm();
        eventForm.setVenuesAvailable(venueService.getListVenue());
        eventForm.setArtistsAvailable(artistService.getAllArtists());

        model.addAttribute("eventForm", eventForm);
        model.addAttribute("events", eventService.getEventList());
        return "event";
    }

    @GetMapping("/admin/event/{id}")
    public String getEventForm(@PathVariable long id, Model model) {
        EventForm eventForm = new EventForm(eventService.getEvent(id));
        eventForm.setVenuesAvailable(venueService.getListVenue());
        eventForm.setArtistsAvailable(artistService.getAllArtists());

        model.addAttribute("eventForm", eventForm);
        model.addAttribute("events", eventService.getEventList());
        return "event";
    }

    @PostMapping("/admin/event")
    public String createEvent(@Valid EventForm eventForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            eventForm.setVenuesAvailable(venueService.getListVenue());
            eventForm.setArtistsAvailable(artistService.getAllArtists());
            model.addAttribute("events", eventService.getEventList());
            return "event";
        }

        int price = Math.round(eventForm.getPrice() * 100);
        Event event = new Event(eventForm.getId(), eventForm.getTitle(), eventForm.getDescription(), eventForm.getTicketsMax(), LocalDateTime.of(eventForm.getDate(), eventForm.getTime()), eventForm.getType(),
                price, true, venueService.get(eventForm.getVenueId()));
        for (Long artistId : eventForm.getArtists()) {
            event.getArtists().add(artistService.get(artistId));
        }
         eventService.saveEvent(event);

        return "redirect:/admin/event";
    }

    @GetMapping("/admin/event/cancel/{id}")
    public String cancelEvent(@PathVariable long id, Model model) {

        Event event = eventService.getEvent(id);
        model.addAttribute("event", event);
        model.addAttribute("bookings", bookingService.findAllByEvent(event));
        return "cancelEvent";
    }

    @PostMapping("/admin/event/cancel")
    public  String cancelEventStepTwo(@RequestParam long eventId, Model model) {
        Event event = eventService.getEvent(eventId);
        for (Booking booking : bookingService.findAllByEvent(event)) {
            for (Ticket ticket : booking.getTickets()) {
                ticketService.cancelTicket(ticket);
            }
            emailService.sendCancellation(booking);
            bookingService.cancelBooking(booking);
        }

        eventService.cancelEvent(event);
        model.addAttribute("message", "L'événement a été correctement annulé");
        return "redirect:/admin/event";
    }
}
