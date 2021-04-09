package bg.softuni.movie.web;

import bg.softuni.movie.exceptions.ObjectNotFoundException;
import bg.softuni.movie.model.entity.DramaEntity;
import bg.softuni.movie.model.entity.MovieEntity;
import bg.softuni.movie.model.entity.UserEntity;
import bg.softuni.movie.model.entity.enums.CountryEnum;
import bg.softuni.movie.model.entity.enums.GenreEnum;
import bg.softuni.movie.repository.DramaRepository;
import bg.softuni.movie.repository.MovieRepository;
import bg.softuni.movie.repository.UserRepository;
import bg.softuni.movie.service.CountryService;
import bg.softuni.movie.service.GenreService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class MovieControllerTest {

    private static final String Movie_CONTROLLER_PREFIX = "/movies";

    private long testMovieId;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CountryService countryService;
    @Autowired
    private GenreService genreService;

    private MovieTestData testData;

    @BeforeEach
    public void setUp() {
        testData = new MovieTestData(
                userRepository,
                movieRepository,
                countryService,
                genreService);

        testData.init();
        testMovieId = testData.getTestMovieId();
    }

    @Test
    @WithMockUser(value = "aaaaa", roles = {"USER", "ADMIN"})
    void addMovie() throws Exception {
        UserEntity userEntity = userRepository.findByUsername("aaaaa").orElseThrow(ObjectNotFoundException::new);

        mockMvc.perform(MockMvcRequestBuilders.post(Movie_CONTROLLER_PREFIX + "/add-movie")
                .param("addedOn", "2021-04-09")
                .param("user", userEntity.getUsername())
                .param("title", "test movie")
                .param("country", "5")
                .param("releaseDate", "2000-01-01")
                .param("director", "test director")
                .param("distributor", "test distributor")
                .param("description", "test description")
                .param("genre", GenreEnum.FANTASY.name())
                .param("cast", "test cast")
                .param("imageUrl", "test imageUrl")
                .param("trailerUrl", "test imageUrl")


                .with(csrf()))
                .andExpect(status().is3xxRedirection());

        List<MovieEntity> list = movieRepository.findAll();

        System.out.println();
        Assertions.assertEquals(6, movieRepository.count());
    }

}
