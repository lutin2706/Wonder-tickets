package be.formation.spring.labo.controller;

import be.formation.spring.labo.model.entity.Venue;
import be.formation.spring.labo.model.form.UserRegistrationForm;
import be.formation.spring.labo.model.form.VenueForm;
import be.formation.spring.labo.service.VenueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class VenueController {

    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping("/admin/venue")
    public String getVenueForm(Model model) {
        model.addAttribute("venueForm", new VenueForm());
        model.addAttribute("venues", venueService.getListVenue());
        return "venue";
    }

    @GetMapping("/admin/venue/{id}")
    public String getVenueForm(@PathVariable long id, Model model) {
        model.addAttribute("venueForm", new VenueForm(venueService.get(id)));
        model.addAttribute("venues", venueService.getListVenue());
        return "venue";
    }

    @PostMapping("/admin/venue")
    public String saveVenue(@Valid VenueForm venueForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("venues", venueService.getListVenue());
            return "venue";
        }

        venueService.saveVenue(new Venue(venueForm.getId(), venueForm.getName(), venueForm.getCity()));

        return "redirect:/admin/venue";
    }

}
