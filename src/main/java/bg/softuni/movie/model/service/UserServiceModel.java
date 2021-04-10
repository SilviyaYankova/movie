package bg.softuni.movie.model.service;

import bg.softuni.movie.model.entity.DramaEntity;
import bg.softuni.movie.model.entity.MovieEntity;
import bg.softuni.movie.model.entity.UserRoleEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class UserServiceModel {

    private String username;
    private String fullName;
    private String password;
    private String email;
    private MultipartFile imageUrl;
    private List<UserRoleEntity> roles = new ArrayList<>();
    private List<MovieEntity> movies = new ArrayList<>();
    private List<DramaEntity> dramas = new ArrayList<>();

    public UserServiceModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserServiceModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public MultipartFile getImageUrl() {
        return imageUrl;
    }

    public UserServiceModel setImageUrl(MultipartFile imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserServiceModel setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public List<MovieEntity> getMovies() {
        return movies;
    }

    public UserServiceModel setMovies(List<MovieEntity> movies) {
        this.movies = movies;
        return this;
    }

    public List<DramaEntity> getDramas() {
        return dramas;
    }

    public UserServiceModel setDramas(List<DramaEntity> dramas) {
        this.dramas = dramas;
        return this;
    }
}
