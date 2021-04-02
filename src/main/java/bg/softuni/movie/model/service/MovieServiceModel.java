package bg.softuni.movie.model.service;

import bg.softuni.movie.model.entity.enums.GenreEnum;

import java.time.LocalDate;
import java.util.List;

public class MovieServiceModel {

    private String title;
    private String country;
    private List<GenreEnum> genre;
    private LocalDate releaseDate;
    private String director;
    private String distributor;
    private String cast;
    private String description;
    private String imageUrl;
    private String trailerUrl;
    private String user;

    public MovieServiceModel() {
    }

    public String getTitle() {
        return title;
    }

    public MovieServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public MovieServiceModel setCountry(String country) {
        this.country = country;
        return this;
    }

    public List<GenreEnum> getGenre() {
        return genre;
    }

    public MovieServiceModel setGenre(List<GenreEnum> genre) {
        this.genre = genre;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public MovieServiceModel setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public String getDirector() {
        return director;
    }

    public MovieServiceModel setDirector(String director) {
        this.director = director;
        return this;
    }

    public String getDistributor() {
        return distributor;
    }

    public MovieServiceModel setDistributor(String distributor) {
        this.distributor = distributor;
        return this;
    }

    public String getCast() {
        return cast;
    }

    public MovieServiceModel setCast(String cast) {
        this.cast = cast;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MovieServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public MovieServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public MovieServiceModel setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
        return this;
    }

    public String getUser() {
        return user;
    }

    public MovieServiceModel setUser(String user) {
        this.user = user;
        return this;
    }
}
