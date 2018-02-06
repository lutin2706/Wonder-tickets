package be.formation.spring.labo.controller;

import be.formation.spring.labo.model.entity.Artist;
import be.formation.spring.labo.model.entity.Venue;
import be.formation.spring.labo.model.form.ArtistForm;
import be.formation.spring.labo.model.form.VenueForm;
import be.formation.spring.labo.service.ArtistService;
import be.formation.spring.labo.service.VenueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/admin/artist")
    public String getArtistForm(Model model) {
        model.addAttribute("artistForm", new ArtistForm());
        model.addAttribute("artists", artistService.getAllArtists());
        return "artist";
    }

    @GetMapping("/admin/artist/{id}")
    public String getArtistForm(@PathVariable long id, Model model) {
        model.addAttribute("artistForm", new ArtistForm(artistService.get(id)));
        model.addAttribute("artists", artistService.getAllArtists());
        return "artist";
    }

    @PostMapping("/admin/artist")
    public String createArtist(@Valid ArtistForm artistForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("artists", artistService.getAllArtists());
            return "artist";
        }

        artistService.saveArtist(new Artist(artistForm.getId(), artistForm.getName(), artistForm.getDescription(), artistForm.getImageUrl()));

        return "redirect:/admin/artist";
    }

}
