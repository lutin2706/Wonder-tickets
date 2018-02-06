package be.formation.spring.labo.controller;

import be.formation.spring.labo.model.form.EventForm;
import be.formation.spring.labo.model.form.OrderForm;
import be.formation.spring.labo.model.form.UserRegistrationForm;
import be.formation.spring.labo.service.EventService;
import be.formation.spring.labo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    private final UserService userService;

    private final EventService eventService;

    public HomeController(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("events", eventService.getActiveEventList());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userRegistrationForm", new UserRegistrationForm());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationForm userRegistrationForm, BindingResult bindingResult) {

        if (!userRegistrationForm.getPassword().equals(userRegistrationForm.getPasswordCheck())) {
            bindingResult.addError(new FieldError("userRegistrationForm", "passwordCheck", "Passwords don't match !"));
        }

        if (bindingResult.hasErrors()) {

            return "register";
        }

        userService.register(userRegistrationForm.getUsername(), userRegistrationForm.getPassword(), userRegistrationForm.getEmail());

        return "redirect:/login";
    }

    @GetMapping("/event/{id}")
    public String showEvent(@PathVariable long id, Model model) {
        model.addAttribute("event", eventService.getEvent(id));
        model.addAttribute("orderForm", new OrderForm(id));
        return "details";
    }
}
