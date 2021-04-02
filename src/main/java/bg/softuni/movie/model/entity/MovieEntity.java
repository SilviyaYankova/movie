package bg.softuni.movie.model.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "movies")
public class MovieEntity extends BaseEntity {

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
    private UserEntity user;

    public MovieEntity() {
    }

    @Column(name = "name", nullable = false)
    public String getTitle() {
        return title;
    }

    public MovieEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    @Column(name = "image_url", nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public MovieEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @Column(name = "trailer_url", nullable = false)
    public String getTrailerUrl() {
        return trailerUrl;
    }

    public MovieEntity setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
        return this;
    }

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public MovieEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @Column(name = "director", nullable = false)
    public String getDirector() {
        return director;
    }

    public MovieEntity setDirector(String director) {
        this.director = director;
        return this;
    }

    @Column(name = "release_date")
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public MovieEntity setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    public List<GenreEntity> getGenre() {
        return genre;
    }

    public MovieEntity setGenre(List<GenreEntity> genre) {
        this.genre = genre;
        return this;
    }

    @Column(name = "distributor")
    public String getDistributor() {
        return distributor;
    }

    public MovieEntity setDistributor(String distributor) {
        this.distributor = distributor;
        return this;
    }

    @Column(name = "country", nullable = false)
    public String getCountry() {
        return country;
    }

    public MovieEntity setCountry(String country) {
        this.country = country;
        return this;
    }

    @Column(name = "cast", nullable = false, columnDefinition = "TEXT")
    public String getCast() {
        return cast;
    }

    public MovieEntity setCast(String cast) {
        this.cast = cast;
        return this;
    }


    @ManyToOne
    public UserEntity getUser() {
        return user;
    }

    public MovieEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }


}
