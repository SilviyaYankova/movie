package bg.softuni.movie.model.service;

import bg.softuni.movie.model.entity.DramaCommentEntity;
import bg.softuni.movie.model.entity.CountryEntity;
import bg.softuni.movie.model.entity.enums.GenreEnum;

import java.time.LocalDate;

import java.util.List;

public class DramaServiceModel {

    private String title;
    private Integer episodes;
    private List<GenreEnum> genre;
    private LocalDate releaseDate;
    private String director;
    private String distributor;
    private String cast;
    private String description;
    private String imageUrl;
    private String trailerUrl;
    private String user;
    private CountryEntity country;
    private LocalDate addedOn;
    private List<DramaCommentEntity> comments;

    public DramaServiceModel() {
    }

    public String getTitle() {
        return title;
    }

    public DramaServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public Integer getEpisodes() {
        return episodes;
    }

    public DramaServiceModel setEpisodes(Integer episodes) {
        this.episodes = episodes;
        return this;
    }

    public List<GenreEnum> getGenre() {
        return genre;
    }

    public DramaServiceModel setGenre(List<GenreEnum> genre) {
        this.genre = genre;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public DramaServiceModel setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public String getDirector() {
        return director;
    }

    public DramaServiceModel setDirector(String director) {
        this.director = director;
        return this;
    }

    public String getDistributor() {
        return distributor;
    }

    public DramaServiceModel setDistributor(String distributor) {
        this.distributor = distributor;
        return this;
    }

    public String getCast() {
        return cast;
    }

    public DramaServiceModel setCast(String cast) {
        this.cast = cast;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DramaServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public DramaServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public DramaServiceModel setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
        return this;
    }

    public String getUser() {
        return user;
    }

    public DramaServiceModel setUser(String user) {
        this.user = user;
        return this;
    }

    public CountryEntity getCountry() {
        return country;
    }

    public DramaServiceModel setCountry(CountryEntity country) {
        this.country = country;
        return this;
    }

    public LocalDate getAddedOn() {
        return addedOn;
    }

    public DramaServiceModel setAddedOn(LocalDate addedOn) {
        this.addedOn = addedOn;
        return this;
    }


    public List<DramaCommentEntity> getComments() {
        return comments;
    }

    public DramaServiceModel setComments(List<DramaCommentEntity> comments) {
        this.comments = comments;
        return this;
    }
}
