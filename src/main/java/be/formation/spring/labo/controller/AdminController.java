package be.formation.spring.labo.controller;

import be.formation.spring.labo.model.form.UserRegistrationForm;
import be.formation.spring.labo.model.form.VenueForm;
import be.formation.spring.labo.service.UserService;
import be.formation.spring.labo.service.VenueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String admin() {

        return "admin";
    }

    @GetMapping("/admin/employee")
    public String getEmployeeForm(Model model) {
        model.addAttribute("userRegistrationForm", new UserRegistrationForm());
        model.addAttribute("employees", userService.getEmployeeList());
        return "employee";
    }

    @PostMapping("/admin/employee")
    public String register(@Valid UserRegistrationForm userRegistrationForm, BindingResult bindingResult) {

        if (!userRegistrationForm.getPassword().equals(userRegistrationForm.getPasswordCheck())) {
            bindingResult.addError(new FieldError("userRegistrationForm", "passwordCheck", "Passwords don't match !"));
        }

        if (bindingResult.hasErrors()) {
            return "employee";
        }

        userService.registerEmployee(userRegistrationForm.getUsername(), userRegistrationForm.getPassword(), userRegistrationForm.getEmail());

        return "redirect:/admin/employee";
    }
}
