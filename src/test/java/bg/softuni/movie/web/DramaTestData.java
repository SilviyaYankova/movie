package bg.softuni.movie.web;

import bg.softuni.movie.model.entity.DramaEntity;
import bg.softuni.movie.model.entity.UserEntity;
import bg.softuni.movie.model.entity.enums.CountryEnum;
import bg.softuni.movie.model.entity.enums.GenreEnum;
import bg.softuni.movie.repository.DramaRepository;
import bg.softuni.movie.repository.UserRepository;
import bg.softuni.movie.service.CountryService;
import bg.softuni.movie.service.GenreService;

import java.time.LocalDate;
import java.util.List;

class DramaTestData {

    private long testDramaId;

    private UserRepository userRepository;
    private DramaRepository dramaRepository;
    private CountryService countryService;
    private GenreService genreService;

    DramaTestData(UserRepository userRepository, DramaRepository artistRepository, CountryService countryService, GenreService genreService) {

        this.userRepository = userRepository;
        this.dramaRepository = artistRepository;
        this.countryService = countryService;
        this.genreService = genreService;
    }



    public void init() {
        UserEntity userEntity1  = new UserEntity()
                .setUsername("11111")
                .setPassword("11111")
                .setImageUrl("11111")
                .setFullName("11111")
                .setRegisteredOn(LocalDate.now());
        userEntity1 = userRepository.save(userEntity1);

        UserEntity userEntity2  = new UserEntity()
                .setUsername("22222")
                .setPassword("22222")
                .setImageUrl("22222")
                .setFullName("22222")
                .setRegisteredOn(LocalDate.now());
        userEntity2 = userRepository.save(userEntity2);


        DramaEntity dramaEntity1 = new DramaEntity()
                .setAddedOn(LocalDate.now())
                .setUser(userEntity1)
                .setTitle("dramaEntity1")
                .setEpisodes(16)
                .setCountry(countryService.findCountry(CountryEnum.KOREA))
                .setReleaseDate(LocalDate.of(2017, 8, 8))
                .setDirector("dramaEntity1")
                .setDistributor("dramaEntity1")
                .setDescription("dramaEntity1")
                .setGenre((List.of(genreService.findGenre(GenreEnum.DRAMA))))
                .setCast("dramaEntity1")
                .setImageUrl("dramaEntity1")
                .setTrailerUrl("dramaEntity1");

        DramaEntity dramaEntity2 = new DramaEntity()
                .setAddedOn(LocalDate.now())
                .setUser(userEntity1)
                .setTitle("dramaEntity2")
                .setEpisodes(16)
                .setCountry(countryService.findCountry(CountryEnum.KOREA))
                .setReleaseDate(LocalDate.of(2017, 8, 8))
                .setDirector("dramaEntity2")
                .setDistributor("dramaEntity2")
                .setDescription("dramaEntity2")
                .setGenre((List.of(genreService.findGenre(GenreEnum.DRAMA))))
                .setCast("dramaEntity2")
                .setImageUrl("dramaEntity2")
                .setTrailerUrl("dramaEntity2");

        dramaRepository.save(dramaEntity1);
        dramaRepository.save(dramaEntity2);

        testDramaId = dramaEntity1.getId();
    }

    public long getTestDramaId() {
        return testDramaId;
    }
}
