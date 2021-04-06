package bg.softuni.movie.model.binding;

import bg.softuni.movie.model.validators.FieldMatch;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@FieldMatch(
        first = "password",
        second = "confirmPassword"
)
public class UserRegisterBindingModel {

    private String username;
    private String email;
    private String fullName;
    private String password;
    private String confirmPassword;
    private String imageUrl;
    private LocalDate registeredOn;

    public UserRegisterBindingModel() {
    }

    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters")
    @NotBlank(message = "Username can not be empty")
    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    @Size(min = 3, max = 20, message = "Full name length must be between 3 and 20 characters")
    @NotBlank(message = "Full name can not be empty")
    public String getFullName() {
        return fullName;
    }

    public UserRegisterBindingModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email can not be empty")
    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    @Size(min = 5, max = 20, message = "Password length must be between 3 and 20 characters")
    @NotBlank(message = "Password can not be empty")
    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    @Size(min = 5, max = 20, message = "Confirm password length must be between 3 and 20 characters")
    @NotBlank(message = "Confirm password can not be empty")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public UserRegisterBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public UserRegisterBindingModel setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
        return this;
    }
}
