package bg.softuni.movie.model.binding;

import bg.softuni.movie.model.entity.CountryEntity;
import bg.softuni.movie.model.entity.GenreEntity;
import bg.softuni.movie.model.entity.enums.GenreEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DramaAddBindingModel {

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
    private CountryEntity country;
    private LocalDate addedOn;

    public DramaAddBindingModel() {
    }

    @Size(min = 2, max = 50, message = "Title length must be between 2 and 50 characters")
    @NotBlank(message = "Title length must be between 2 and 50 characters")
    public String getTitle() {
        return title;
    }

    public DramaAddBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }


    @Min(1)
    @NotNull(message = "There must be at least one episode")
    public Integer getEpisodes() {
        return episodes;
    }

    public DramaAddBindingModel setEpisodes(Integer episodes) {
        this.episodes = episodes;
        return this;
    }

    @NotNull()
    @Size(min = 1, message = " - Choose at least one genre")
    public List<GenreEnum> getGenre() {
        return genre;
    }

    public DramaAddBindingModel setGenre(List<GenreEnum> genre) {
        this.genre = genre;
        return this;
    }

    @NotNull(message = "Choose date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public DramaAddBindingModel setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    @Size(min = 2, max = 50, message = "Director length must be between 2 and 50 characters")
    @NotBlank(message = "Director name can not be empty")
    public String getDirector() {
        return director;
    }

    public DramaAddBindingModel setDirector(String director) {
        this.director = director;
        return this;
    }

    @Size(min = 2, max = 50, message = "Distributor length must be between 2 and 50 characters")
    @NotBlank(message = "Distributor name can not be empty")
    public String getDistributor() {
        return distributor;
    }

    public DramaAddBindingModel setDistributor(String distributor) {
        this.distributor = distributor;
        return this;
    }

    @Size(min = 2, message = "Cast length must be at least 2 characters long")
    @NotBlank(message = "Cast can not be empty")
    public String getCast() {
        return cast;
    }

    public DramaAddBindingModel setCast(String cast) {
        this.cast = cast;
        return this;
    }

    @Size(min = 10, message = "Description length must be at least 10 characters long")
    @NotBlank(message = "Description can not be empty")
    public String getDescription() {
        return description;
    }

    public DramaAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @NotBlank(message = "Image URL can not be empty")
    public String getImageUrl() {
        return imageUrl;
    }

    public DramaAddBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @NotBlank(message = "Trailer URL can not be empty")
    public String getTrailerUrl() {
        return trailerUrl;
    }

    public DramaAddBindingModel setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
        return this;
    }

    @NotNull(message = "Country name can not be empty")
    public CountryEntity getCountry() {
        return country;
    }

    public DramaAddBindingModel setCountry(CountryEntity country) {
        this.country = country;
        return this;
    }


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getAddedOn() {
        return addedOn;
    }

    public DramaAddBindingModel setAddedOn(LocalDate addedOn) {
        this.addedOn = addedOn;
        return this;
    }
}
