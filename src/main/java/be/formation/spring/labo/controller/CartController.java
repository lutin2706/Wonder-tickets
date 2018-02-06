package be.formation.spring.labo.controller;

import be.formation.spring.labo.model.entity.Booking;
import be.formation.spring.labo.model.entity.Event;
import be.formation.spring.labo.model.entity.Ticket;
import be.formation.spring.labo.model.entity.User;
import be.formation.spring.labo.model.form.OrderForm;
import be.formation.spring.labo.service.BookingService;
import be.formation.spring.labo.service.EventService;
import be.formation.spring.labo.service.TicketService;
import be.formation.spring.labo.service.UserService;
import be.formation.spring.labo.service.impl.EmailServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.UUID;

@Controller
public class CartController {

    private final EmailServiceImpl emailService;

    private final UserService userService;
    private final EventService eventService;
    private final BookingService bookingService;
    private final TicketService ticketService;

    public CartController(EmailServiceImpl emailService, UserService userService, EventService eventService, BookingService bookingService, TicketService ticketService) {
        this.emailService = emailService;
        this.userService = userService;
        this.eventService = eventService;
        this.bookingService = bookingService;
        this.ticketService = ticketService;
    }

    @PostMapping("/user/cart")
    public String updateCart(@Valid OrderForm orderForm, BindingResult bindingResult, Principal principal, Model model) {

        Event event = eventService.getEvent(orderForm.getEventId());

        if (orderForm.getNbr() > event.getTicketsAvailable()) {
            bindingResult.addError(new FieldError("orderForm", "nbr", "The number of requested tickets must be lower than the tickets available"));
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("event", event);
            return "details";
        }

        User user = userService.getUser(principal.getName());

        event.setTicketsAvailable(event.getTicketsAvailable() - orderForm.getNbr());

        Booking booking = new Booking(LocalDateTime.now(), orderForm.getExpedition(), event, user);
        bookingService.saveBooking(booking);

        for (int i = 0; i < orderForm.getNbr(); i++) {
            Ticket ticket = new Ticket(event, UUID.randomUUID(), booking);
            ticketService.saveTicket(ticket);
            booking.getTickets().add(ticket);
        }

//        HttpSession session = request.getSession(true);

        String message = "Votre commande est bien passée. Vous recevrez rapidement vos tickets par e-mail.";
        if (orderForm.getExpedition().equals("email"))
            emailService.sendConfirmation(booking);
        else
            message = "Votre commande est bien passée. Vous recevrez rapidement vos tickets par courrier (si la poste ne les perd pas, à vos risques et périls).";

        model.addAttribute("message", message);
        return "cart";
    }
}
