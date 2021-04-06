package bg.softuni.movie.model.view;


import bg.softuni.movie.model.entity.CommentEntity;
import bg.softuni.movie.model.entity.CountryEntity;
import bg.softuni.movie.model.entity.GenreEntity;
import bg.softuni.movie.model.entity.UserEntity;

import java.time.LocalDate;
import java.util.List;

public class DramaViewModel {

    private Long id;
    private String title;
    private Integer episodes;
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
    private List<CommentEntity> comments;
    private UserEntity user;

    public DramaViewModel() {
    }

    public Long getId() {
        return id;
    }

    public DramaViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public DramaViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public Integer getEpisodes() {
        return episodes;
    }

    public DramaViewModel setEpisodes(Integer episodes) {
        this.episodes = episodes;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public DramaViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public DramaViewModel setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DramaViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDirector() {
        return director;
    }

    public DramaViewModel setDirector(String director) {
        this.director = director;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public DramaViewModel setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public List<GenreEntity> getGenre() {
        return genre;
    }

    public DramaViewModel setGenre(List<GenreEntity> genre) {
        this.genre = genre;
        return this;
    }

    public String getDistributor() {
        return distributor;
    }

    public DramaViewModel setDistributor(String distributor) {
        this.distributor = distributor;
        return this;
    }

    public String getCast() {
        return cast;
    }

    public DramaViewModel setCast(String cast) {
        this.cast = cast;
        return this;
    }

    public CountryEntity getCountry() {
        return country;
    }

    public DramaViewModel setCountry(CountryEntity country) {
        this.country = country;
        return this;
    }

    public LocalDate getAddedOn() {
        return addedOn;
    }

    public DramaViewModel setAddedOn(LocalDate addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public DramaViewModel setComments(List<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public DramaViewModel setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
