package bg.softuni.movie.service.impl;

import bg.softuni.movie.model.entity.CountryEntity;
import bg.softuni.movie.model.entity.DramaEntity;
import bg.softuni.movie.model.entity.GenreEntity;
import bg.softuni.movie.model.entity.UserEntity;
import bg.softuni.movie.model.entity.enums.CountryEnum;
import bg.softuni.movie.model.entity.enums.GenreEnum;
import bg.softuni.movie.model.view.DramaViewModel;
import bg.softuni.movie.repository.DramaRepository;
import bg.softuni.movie.repository.LogRepository;
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
class DramaServiceImplTest {

    private UserEntity testUser1, testUser2;
    private DramaEntity testDramaEntity1, testDramaEntity2;

    @Autowired
    private CountryService countryService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private UserService userService;

    private DramaServiceImpl serviceToTest;

    @Mock
    DramaRepository mockDramaRepository;

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

        testDramaEntity1 = new DramaEntity()
                .setAddedOn(LocalDate.now())
                .setTitle("dramaEntity1")
                .setEpisodes(16)
                .setCountry(countryEntity)
                .setReleaseDate(LocalDate.of(2017, 8, 8))
                .setDirector("dramaEntity1")
                .setDistributor("dramaEntity1")
                .setDescription("dramaEntity1")
                .setGenre(List.of(genreEntity))
                .setCast("dramaEntity1")
                .setImageUrl("dramaEntity1")
                .setTrailerUrl("dramaEntity1")
                .setUser(testUser1);

        testDramaEntity2 = new DramaEntity()
                .setAddedOn(LocalDate.now())
                .setTitle("dramaEntity2")
                .setEpisodes(16)
                .setCountry(countryEntity)
                .setReleaseDate(LocalDate.of(2017, 8, 8))
                .setDirector("dramaEntity2")
                .setDistributor("dramaEntity2")
                .setDescription("dramaEntity2")
                .setGenre(List.of(genreEntity))
                .setCast("dramaEntity2")
                .setImageUrl("dramaEntity2")
                .setTrailerUrl("dramaEntity2")
                .setUser(testUser1);


        serviceToTest = new DramaServiceImpl(
                mockDramaRepository,
                userService,
                genreService,
                countryService,
                new ModelMapper());
    }

    @Test
    public void testFindAllByUser() {

        when(mockDramaRepository.findAllByUser(testUser1))
                .thenReturn(List.of(testDramaEntity1, testDramaEntity2));

        List<DramaViewModel> allModels = serviceToTest
                .displayUserDramas(testUser1);

        System.out.println();
        Assertions.assertEquals(2, allModels.size());

        DramaViewModel model1 = allModels.get(0);
        DramaViewModel model2 = allModels.get(1);

        Assertions.assertEquals(testDramaEntity1.getTitle(), model1.getTitle());
        Assertions.assertEquals(testDramaEntity1.getEpisodes(), model1.getEpisodes());
        Assertions.assertEquals(testDramaEntity1.getImageUrl(), model1.getImageUrl());
        Assertions.assertEquals(testDramaEntity1.getTrailerUrl(), model1.getTrailerUrl());
        Assertions.assertEquals(testDramaEntity1.getDescription(), model1.getDescription());
        Assertions.assertEquals(testDramaEntity1.getDirector(), model1.getDirector());
        Assertions.assertEquals(testDramaEntity1.getReleaseDate(), model1.getReleaseDate());
        Assertions.assertEquals(testDramaEntity1.getGenre(), model1.getGenre());
        Assertions.assertEquals(testDramaEntity1.getDistributor(), model1.getDistributor());
        Assertions.assertEquals(testDramaEntity1.getCast(), model1.getCast());
        Assertions.assertEquals(testDramaEntity1.getCountry(), model1.getCountry());
        Assertions.assertEquals(testDramaEntity1.getAddedOn(), model1.getAddedOn());

        Assertions.assertEquals(testDramaEntity2.getTitle(), model2.getTitle());
        Assertions.assertEquals(testDramaEntity2.getEpisodes(), model2.getEpisodes());
        Assertions.assertEquals(testDramaEntity2.getImageUrl(), model2.getImageUrl());
        Assertions.assertEquals(testDramaEntity2.getTrailerUrl(), model2.getTrailerUrl());
        Assertions.assertEquals(testDramaEntity2.getDescription(), model2.getDescription());
        Assertions.assertEquals(testDramaEntity2.getDirector(), model2.getDirector());
        Assertions.assertEquals(testDramaEntity2.getReleaseDate(), model2.getReleaseDate());
        Assertions.assertEquals(testDramaEntity2.getGenre(), model2.getGenre());
        Assertions.assertEquals(testDramaEntity2.getDistributor(), model2.getDistributor());
        Assertions.assertEquals(testDramaEntity2.getCast(), model2.getCast());
        Assertions.assertEquals(testDramaEntity2.getCountry(), model2.getCountry());
        Assertions.assertEquals(testDramaEntity2.getAddedOn(), model2.getAddedOn());

    }
}
