package bg.softuni.movie.model.view;


import bg.softuni.movie.model.entity.DramaCommentEntity;
import bg.softuni.movie.model.entity.CountryEntity;
import bg.softuni.movie.model.entity.GenreEntity;
import bg.softuni.movie.model.entity.UserEntity;

import java.time.LocalDate;
import java.util.List;

public class MovieViewModel {

    private Long id;
    private String title;
    private String imageUrl;
    private String trailerUrl;
    private String description;
    private String director;
    private LocalDate releaseDate;
    private List<GenreEntity> genre;
    private String distributor;
    private String cast;
    private CountryEntity country;
    private LocalDate addedOn;
    private List<DramaCommentEntity> comments;
    private UserEntity user;

    public MovieViewModel() {
    }

    public Long getId() {
        return id;
    }

    public MovieViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public MovieViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public MovieViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public MovieViewModel setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MovieViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDirector() {
        return director;
    }

    public MovieViewModel setDirector(String director) {
        this.director = director;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public MovieViewModel setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public List<GenreEntity> getGenre() {
        return genre;
    }

    public MovieViewModel setGenre(List<GenreEntity> genre) {
        this.genre = genre;
        return this;
    }

    public String getDistributor() {
        return distributor;
    }

    public MovieViewModel setDistributor(String distributor) {
        this.distributor = distributor;
        return this;
    }

    public String getCast() {
        return cast;
    }

    public MovieViewModel setCast(String cast) {
        this.cast = cast;
        return this;
    }

    public CountryEntity getCountry() {
        return country;
    }

    public MovieViewModel setCountry(CountryEntity country) {
        this.country = country;
        return this;
    }

    public LocalDate getAddedOn() {
        return addedOn;
    }

    public MovieViewModel setAddedOn(LocalDate addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    public List<DramaCommentEntity> getComments() {
        return comments;
    }

    public MovieViewModel setComments(List<DramaCommentEntity> comments) {
        this.comments = comments;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public MovieViewModel setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
