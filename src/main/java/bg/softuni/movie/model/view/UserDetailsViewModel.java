package bg.softuni.movie.model.view;

import bg.softuni.movie.model.entity.DramaEntity;
import bg.softuni.movie.model.entity.MovieEntity;
import bg.softuni.movie.model.entity.UserRoleEntity;

import java.time.LocalDate;
import java.util.List;

public class UserDetailsViewModel {

    private String id;
    private String username;
    private String fullName;
    private String email;
    private List<UserRoleEntity> roles;
    private List<DramaEntity> dramas;
    private List<MovieEntity> movies;
    private String imageUrl;
    private LocalDate registeredOn;

    public String getId() {
        return id;
    }

    public UserDetailsViewModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDetailsViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserDetailsViewModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }


    public String getEmail() {
        return email;
    }

    public UserDetailsViewModel setEmail(String email) {
        this.email = email;
        return this;
    }


    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserDetailsViewModel setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public List<DramaEntity> getDramas() {
        return dramas;
    }

    public UserDetailsViewModel setDramas(List<DramaEntity> dramas) {
        this.dramas = dramas;
        return this;
    }

    public List<MovieEntity> getMovies() {
        return movies;
    }

    public UserDetailsViewModel setMovies(List<MovieEntity> movies) {
        this.movies = movies;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public UserDetailsViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public UserDetailsViewModel setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
        return this;
    }
}