package be.formation.spring.labo.controller;

import be.formation.spring.labo.model.entity.Event;
import be.formation.spring.labo.model.form.EventForm;
import be.formation.spring.labo.service.BookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/admin/booking")
    public String getBookingForm(Model model) {
        model.addAttribute("bookings", bookingService.getListBooking());
//        model.addAttribute("bookingForm", bookingForm);
        return "booking";
    }

//    @GetMapping("/admin/event/{id}")
//    public String getEventForm(@PathVariable long id, Model model) {
//        EventForm eventForm = new EventForm(eventService.getEvent(id));
//        eventForm.setVenuesAvailable(venueService.getListVenue());
//        eventForm.setArtistsAvailable(artistService.getAllArtists());
//
//        model.addAttribute("eventForm", eventForm);
//        model.addAttribute("events", eventService.getEventList());
//        return "event";
//    }
//
//    @PostMapping("/admin/event")
//    public String createEvent(@Valid EventForm eventForm, BindingResult bindingResult, Model model) {
//
//        if (bindingResult.hasErrors()) {
//            eventForm.setVenuesAvailable(venueService.getListVenue());
//            eventForm.setArtistsAvailable(artistService.getAllArtists());
//            model.addAttribute("events", eventService.getEventList());
//            return "event";
//        }
//
//        int price = Math.round(eventForm.getPrice() * 100);
//        Event event = new Event(eventForm.getId(), eventForm.getTitle(), eventForm.getDescription(), eventForm.getTicketsMax(), LocalDateTime.of(eventForm.getDate(), eventForm.getTime()), eventForm.getType(),
//                price, true, venueService.get(eventForm.getVenueId()));
//        for (Long artistId : eventForm.getArtists()) {
//            event.getArtists().add(artistService.get(artistId));
//        }
//         eventService.saveEvent(event);
//
//        return "redirect:/admin/event";
//    }

}
