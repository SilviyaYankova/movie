package bg.softuni.movie.web;

import bg.softuni.movie.exceptions.ObjectNotFoundException;
import bg.softuni.movie.model.entity.DramaEntity;
import bg.softuni.movie.model.entity.UserEntity;
import bg.softuni.movie.model.entity.enums.CountryEnum;
import bg.softuni.movie.model.entity.enums.GenreEnum;
import bg.softuni.movie.repository.DramaRepository;
import bg.softuni.movie.repository.LogRepository;
import bg.softuni.movie.repository.UserRepository;
import bg.softuni.movie.repository.UserRoleRepository;
import bg.softuni.movie.service.CountryService;
import bg.softuni.movie.service.GenreService;
import org.junit.jupiter.api.AfterEach;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class DramaControllerTest {

    private static final String DRAMA_CONTROLLER_PREFIX = "/dramas";

    private long testDramaId;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DramaRepository dramaRepository;
    @Autowired
    private CountryService countryService;
    @Autowired
    private GenreService genreService;

    private DramaTestData testData;

    @BeforeEach
    public void setUp() {
        testData = new DramaTestData(
                userRepository,
                dramaRepository,
                countryService, genreService);
        testData.init();
        testDramaId = testData.getTestDramaId();
    }

    @Test
    @WithMockUser(value = "11111", roles = {"USER", "ADMIN"})
    void addDrama() throws Exception {
        UserEntity userEntity = userRepository.findByUsername("11111").orElseThrow(ObjectNotFoundException::new);

        mockMvc.perform(MockMvcRequestBuilders.post(DRAMA_CONTROLLER_PREFIX + "/add-drama")
                .param("addedOn", "2021-04-09")
                .param("user", userEntity.getUsername())
                .param("title", "test drama")
                .param("episodes", "16")
                .param("country", CountryEnum.KOREA.name())
                .param("releaseDate", "2000-01-01")
                .param("director", "test director")
                .param("distributor", "test distributor")
                .param("description", "test description")
                .param("genre", GenreEnum.DRAMA.name())
                .param("cast", "test cast")
                .param("imageUrl", "test imageUrl")
                .param("trailerUrl", "test imageUrl")


                .with(csrf()))
                .andExpect(status().is3xxRedirection());

        List<DramaEntity> list = dramaRepository.findAll();

        System.out.println();
        Assertions.assertEquals(5, dramaRepository.count());
    }

}
