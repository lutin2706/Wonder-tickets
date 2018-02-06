package be.formation.spring.labo.model.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegistrationForm {

    @NotNull(message = "Username is required.")
    @Size(min = 3, max = 255, message = "Username should be between 3 and 255 characters long.")
    private String username;

    @NotNull(message = "Password is required.")
    @Size(min = 3, max = 255, message = "Password should be between 3 and 255 characters long.")
    private String password;

    @NotNull(message = "Password check is required.")
    @Size(min = 3, max = 255, message = "Password should be between 3 and 255 characters long.")
    private String passwordCheck;

    @NotNull(message = "Email is required")
    private String email;

    public UserRegistrationForm() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
