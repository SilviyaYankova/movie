package bg.softuni.movie.web;

import bg.softuni.movie.model.entity.DramaEntity;
import bg.softuni.movie.model.entity.MovieEntity;
import bg.softuni.movie.model.entity.UserEntity;
import bg.softuni.movie.model.entity.enums.CountryEnum;
import bg.softuni.movie.model.entity.enums.GenreEnum;
import bg.softuni.movie.repository.MovieRepository;
import bg.softuni.movie.repository.UserRepository;
import bg.softuni.movie.service.CountryService;
import bg.softuni.movie.service.GenreService;

import java.time.LocalDate;
import java.util.List;

class MovieTestData {

    private long testMovieId;

    private UserRepository userRepository;
    private MovieRepository movieRepository;
    private CountryService countryService;
    private GenreService genreService;

    MovieTestData(UserRepository userRepository, MovieRepository movieRepository, CountryService countryService, GenreService genreService) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.countryService = countryService;
        this.genreService = genreService;
    }

    public void init() {
        UserEntity userEntity1  = new UserEntity()
                .setUsername("aaaaa")
                .setPassword("aaaaa")
                .setImageUrl("aaaaa")
                .setFullName("aaaaa")
                .setRegisteredOn(LocalDate.now());
        userEntity1 = userRepository.save(userEntity1);

        UserEntity userEntity2  = new UserEntity()
                .setUsername("bbbbb")
                .setPassword("bbbbb")
                .setImageUrl("bbbbb")
                .setFullName("bbbbb")
                .setRegisteredOn(LocalDate.now());
        userEntity2 = userRepository.save(userEntity2);


        MovieEntity movieEntity1 = new MovieEntity()
                .setAddedOn(LocalDate.now())
                .setUser(userEntity1)
                .setTitle("movieEntity1")
                .setCountry(countryService.findCountry(CountryEnum.KOREA))
                .setReleaseDate(LocalDate.of(2017, 8, 8))
                .setDirector("movieEntity1")
                .setDistributor("movieEntity1")
                .setDescription("movieEntity1")
                .setGenre((List.of(genreService.findGenre(GenreEnum.DRAMA))))
                .setCast("movieEntity1")
                .setImageUrl("movieEntity1")
                .setTrailerUrl("movieEntity1");

        MovieEntity movieEntity2 = new MovieEntity()
                .setAddedOn(LocalDate.now())
                .setUser(userEntity1)
                .setTitle("movieEntity2")
                .setCountry(countryService.findCountry(CountryEnum.KOREA))
                .setReleaseDate(LocalDate.of(2017, 8, 8))
                .setDirector("movieEntity2")
                .setDistributor("movieEntity2")
                .setDescription("movieEntity2")
                .setGenre((List.of(genreService.findGenre(GenreEnum.DRAMA))))
                .setCast("movieEntity2")
                .setImageUrl("movieEntity2")
                .setTrailerUrl("movieEntity2");

        movieRepository.save(movieEntity1);
        movieRepository.save(movieEntity2);

        testMovieId = movieEntity1.getId();
    }

    public long getTestMovieId() {
        return testMovieId;
    }
}