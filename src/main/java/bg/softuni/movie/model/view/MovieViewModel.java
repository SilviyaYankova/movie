package bg.softuni.movie.model.view;


import bg.softuni.movie.model.entity.GenreEntity;

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
    private String country;
    private String cast;

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

    public String getCountry() {
        return country;
    }

    public MovieViewModel setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCast() {
        return cast;
    }

    public MovieViewModel setCast(String cast) {
        this.cast = cast;
        return this;
    }
}
