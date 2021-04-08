package bg.softuni.movie.model.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "dramas")
public class DramaEntity extends BaseEntity {

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
    private UserEntity user;
    private CountryEntity country;
    private LocalDate addedOn;
    private List<DramaCommentEntity> comments;

    public DramaEntity() {
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public DramaEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    @Column(name = "episodes", nullable = false)
    public Integer getEpisodes() {
        return episodes;
    }

    public DramaEntity setEpisodes(Integer episodes) {
        this.episodes = episodes;
        return this;
    }

    @Column(name = "image_url", nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public DramaEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @Column(name = "trailer_url")
    public String getTrailerUrl() {
        return trailerUrl;
    }

    public DramaEntity setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
        return this;
    }

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public DramaEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @Column(name = "derector", nullable = false, columnDefinition = "TEXT")
    public String getDirector() {
        return director;
    }

    public DramaEntity setDirector(String director) {
        this.director = director;
        return this;
    }

    @Column(name = "release_date")
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public DramaEntity setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    public List<GenreEntity> getGenre() {
        return genre;
    }

    public DramaEntity setGenre(List<GenreEntity> genre) {
        this.genre = genre;
        return this;
    }

    @Column(name = "distributor")
    public String getDistributor() {
        return distributor;
    }

    public DramaEntity setDistributor(String distributor) {
        this.distributor = distributor;
        return this;
    }

    @Column(name = "cast", nullable = false, columnDefinition = "TEXT")
    public String getCast() {
        return cast;
    }

    public DramaEntity setCast(String cast) {
        this.cast = cast;
        return this;
    }

    @ManyToOne
    public UserEntity getUser() {
        return user;
    }

    public DramaEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    @ManyToOne()
    public CountryEntity getCountry() {
        return country;
    }

    public DramaEntity setCountry(CountryEntity country) {
        this.country = country;
        return this;
    }

    @Column(name = "added_on", nullable = false)
    public LocalDate getAddedOn() {
        return addedOn;
    }

    public DramaEntity setAddedOn(LocalDate addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    public List<DramaCommentEntity> getComments() {
        return comments;
    }

    public DramaEntity setComments(List<DramaCommentEntity> comments) {
        this.comments = comments;
        return this;
    }
}
