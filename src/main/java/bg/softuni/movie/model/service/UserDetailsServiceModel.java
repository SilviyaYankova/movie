package bg.softuni.movie.model.service;

import bg.softuni.movie.model.entity.DramaEntity;
import bg.softuni.movie.model.entity.MovieEntity;
import bg.softuni.movie.model.entity.UserRoleEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsServiceModel {
    private String username;
    private String fullName;
    private String password;
    private String email;
    private MultipartFile imageUrl;
    private List<UserRoleEntity> roles = new ArrayList<>();
    private List<MovieEntity> favouriteMovies = new ArrayList<>();
    private List<DramaEntity> favouriteDramas = new ArrayList<>();

    public UserDetailsServiceModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserDetailsServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserDetailsServiceModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDetailsServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDetailsServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public MultipartFile getImageUrl() {
        return imageUrl;
    }

    public UserDetailsServiceModel setImageUrl(MultipartFile imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserDetailsServiceModel setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public List<MovieEntity> getFavouriteMovies() {
        return favouriteMovies;
    }

    public UserDetailsServiceModel setFavouriteMovies(List<MovieEntity> favouriteMovies) {
        this.favouriteMovies = favouriteMovies;
        return this;
    }

    public List<DramaEntity> getFavouriteDramas() {
        return favouriteDramas;
    }

    public UserDetailsServiceModel setFavouriteDramas(List<DramaEntity> favouriteDramas) {
        this.favouriteDramas = favouriteDramas;
        return this;
    }
}
