package bg.softuni.movie.service.impl;

import bg.softuni.movie.model.entity.*;
import bg.softuni.movie.model.entity.enums.CountryEnum;
import bg.softuni.movie.model.entity.enums.GenreEnum;
import bg.softuni.movie.model.view.MovieViewModel;
import bg.softuni.movie.repository.MovieRepository;
import bg.softuni.movie.repository.UserRepository;
import bg.softuni.movie.service.CountryService;
import bg.softuni.movie.service.GenreService;
import bg.softuni.movie.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@ExtendWith(MockitoExtension.class)
class MovieServiceImplTest {

    private UserEntity testUser1, testUser2;
    private MovieEntity testMovieEntity1, testMovieEntity2;

    @Autowired
    private CountryService countryService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private UserService userService;

    private MovieServiceImpl serviceToTest;

    @Mock
    MovieRepository mockMovieRepository;

    @Mock
    UserRepository mockUserRepository;

    @BeforeEach
    public void init() {
        CountryEntity countryEntity = new CountryEntity().setName(CountryEnum.KOREA);
        GenreEntity genreEntity = new GenreEntity().setName(GenreEnum.DRAMA);

        testUser1 = new UserEntity();
        testUser1.setUsername("user 1")
                .setFullName("user 1")
                .setPassword("user 1")
                .setImageUrl("user 1")
                .setFullName("user 1")
                .setEmail("aaa@aaa.aaa")
                .setRegisteredOn(LocalDate.now());

        testUser2 = new UserEntity();
        testUser2.setUsername("user 2")
                .setFullName("user 2")
                .setPassword("user 2")
                .setImageUrl("user 2")
                .setFullName("user 2")
                .setEmail("aaa@aaa.aaa")
                .setRegisteredOn(LocalDate.now());

        testMovieEntity1 = new MovieEntity()
                .setAddedOn(LocalDate.now())
                .setTitle("movieEntity1")
                .setCountry(countryEntity)
                .setReleaseDate(LocalDate.of(2017, 8, 8))
                .setDirector("movieEntity1")
                .setDistributor("movieEntity1")
                .setDescription("movieEntity1")
                .setGenre(List.of(genreEntity))
                .setCast("movieEntity1")
                .setImageUrl("movieEntity1")
                .setTrailerUrl("movieEntity1")
                .setUser(testUser1);

        testMovieEntity2 = new MovieEntity()
                .setAddedOn(LocalDate.now())
                .setTitle("movieEntity2")
                .setCountry(countryEntity)
                .setReleaseDate(LocalDate.of(2017, 8, 8))
                .setDirector("movieEntity2")
                .setDistributor("movieEntity2")
                .setDescription("movieEntity2")
                .setGenre(List.of(genreEntity))
                .setCast("movieEntity2")
                .setImageUrl("movieEntity2")
                .setTrailerUrl("movieEntity2")
                .setUser(testUser1);


        serviceToTest = new MovieServiceImpl(
                mockMovieRepository,
                userService,
                genreService,
                countryService,
                new ModelMapper());
    }


    @Test
    public void testFindAllByUser() {

        when(mockMovieRepository.findAllByUser(testUser1))
                .thenReturn(List.of(testMovieEntity1, testMovieEntity2));

        List<MovieViewModel> allModels = serviceToTest
                .displayUserMovies(testUser1);

        System.out.println();
        Assertions.assertEquals(2, allModels.size());

        MovieViewModel model1 = allModels.get(0);
        MovieViewModel model2 = allModels.get(1);

        Assertions.assertEquals(testMovieEntity1.getTitle(), model1.getTitle());
        Assertions.assertEquals(testMovieEntity1.getImageUrl(), model1.getImageUrl());
        Assertions.assertEquals(testMovieEntity1.getTrailerUrl(), model1.getTrailerUrl());
        Assertions.assertEquals(testMovieEntity1.getDescription(), model1.getDescription());
        Assertions.assertEquals(testMovieEntity1.getDirector(), model1.getDirector());
        Assertions.assertEquals(testMovieEntity1.getReleaseDate(), model1.getReleaseDate());
        Assertions.assertEquals(testMovieEntity1.getGenre(), model1.getGenre());
        Assertions.assertEquals(testMovieEntity1.getDistributor(), model1.getDistributor());
        Assertions.assertEquals(testMovieEntity1.getCast(), model1.getCast());
        Assertions.assertEquals(testMovieEntity1.getCountry(), model1.getCountry());
        Assertions.assertEquals(testMovieEntity1.getAddedOn(), model1.getAddedOn());

        Assertions.assertEquals(testMovieEntity2.getTitle(), model2.getTitle());
        Assertions.assertEquals(testMovieEntity2.getImageUrl(), model2.getImageUrl());
        Assertions.assertEquals(testMovieEntity2.getTrailerUrl(), model2.getTrailerUrl());
        Assertions.assertEquals(testMovieEntity2.getDescription(), model2.getDescription());
        Assertions.assertEquals(testMovieEntity2.getDirector(), model2.getDirector());
        Assertions.assertEquals(testMovieEntity2.getReleaseDate(), model2.getReleaseDate());
        Assertions.assertEquals(testMovieEntity2.getGenre(), model2.getGenre());
        Assertions.assertEquals(testMovieEntity2.getDistributor(), model2.getDistributor());
        Assertions.assertEquals(testMovieEntity2.getCast(), model2.getCast());
        Assertions.assertEquals(testMovieEntity2.getCountry(), model2.getCountry());
        Assertions.assertEquals(testMovieEntity2.getAddedOn(), model2.getAddedOn());

    }


}
