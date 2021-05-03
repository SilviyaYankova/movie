package bg.softuni.movie.service.impl;

import bg.softuni.movie.exceptions.ObjectNotFoundException;
import bg.softuni.movie.model.entity.*;
import bg.softuni.movie.model.entity.enums.CountryEnum;
import bg.softuni.movie.model.entity.enums.GenreEnum;
import bg.softuni.movie.model.service.MovieServiceModel;
import bg.softuni.movie.model.view.DramaViewModel;
import bg.softuni.movie.model.view.MovieViewModel;
import bg.softuni.movie.repository.MovieRepository;
import bg.softuni.movie.service.CountryService;
import bg.softuni.movie.service.GenreService;
import bg.softuni.movie.service.MovieService;
import bg.softuni.movie.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final UserService userService;
    private final GenreService genreService;
    private final CountryService countryService;
    private final ModelMapper modelMapper;

    public MovieServiceImpl(MovieRepository movieRepository, UserService userService, GenreService genreService, CountryService countryService, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.userService = userService;
        this.genreService = genreService;
        this.countryService = countryService;
        this.modelMapper = modelMapper;
    }


    @Override
    public void seedMovies() {
        if (movieRepository.count() == 0) {
            MovieEntity theDudeInMe = new MovieEntity()
                    .setAddedOn(LocalDate.now())
                    .setUser(userService.findUserById(1L))
                    .setTitle("The Dude In Me")
                    .setCountry(countryService.findCountry(CountryEnum.KOREA))
                    .setReleaseDate(LocalDate.of(2020, 1, 9))
                    .setDirector("Jeon Ki-Sang")
                    .setDistributor("TCO, Merry Christmas Entertainment")
                    .setDescription("Dong-Hyun (Jin Young) is a high school student. One day, he falls from the rooftop and bumps into Pan-Soo (Park Sung-Woong) who is a passerby. Pan-Soo is a member of a criminal organization. When the two men wake up in the hospital, they discover that they have switched bodies.")
                    .setGenre((List.of(genreService.findGenre(GenreEnum.COMEDY),
                            genreService.findGenre(GenreEnum.FANTASY))))
                    .setCast("Park Sung-Woong, Jin Young Ra Mi-Ran, Lee Soo-Min, Lee Joon-Hyuk, Kim Kwang-Kyu, Yoon Kyung-Ho, Kim Hyun-Mok, Park Kyung-Hye, Kim Bu-Seon")
                    .setImageUrl("https://asianwiki.com/images/3/3f/The_Dude_In_Me-tp1.jpg")
                    .setTrailerUrl("c73OjWe3aT0");

            MovieEntity parasite = new MovieEntity()
                    .setAddedOn(LocalDate.now())
                    .setUser(userService.findUserById(1L))
                    .setTitle("Parasite")
                    .setCountry(countryService.findCountry(CountryEnum.KOREA))
                    .setReleaseDate(LocalDate.of(2020, 5, 30))
                    .setDirector("Bong Joon-Ho, Han Jin-Won")
                    .setDistributor("CJ Entertainment")
                    .setDescription("Ki-Taek (Song Kang-Ho) is poor and unemployed. He lives with his wife Choong-Sook (Jang Hye-Jin), son Ki-Woo (Choi Woo-Sik) and daughter Ki-Jung (Park So-Dam) in a damp, insect infested semi-basement apartment in a low income area of Seoul. When Ki-Woo takes his friend (Park Seo-Joon) to a nearby convenient store for drinks, he learns that his friend (who is going to study abroad) is leaving behind a good paying private tutoring job. His friend wants Ki-Woo to takeover the tutoring job while he is abroad. Soon, Ki-Woo enters the life of the wealthy Park family, with Ki-Woo's own family lurking in the periphery.")
                    .setGenre((List.of(genreService.findGenre(GenreEnum.DRAMA))))
                    .setCast("Song Kang-Ho, Jang Hye-Jin, Choi Woo-Sik, Park So-Dam, Lee Sun-Kyun, Cho Yeo-Jeong, Jung Ji-So, Jeong Hyun-Jun")
                    .setImageUrl("https://asianwiki.com/images/b/b2/Parasite-2019-p01.jpg")
                    .setTrailerUrl("isOGD_7hNIY");

            MovieEntity spaceSweepers = new MovieEntity()
                    .setAddedOn(LocalDate.now())
                    .setUser(userService.findUserById(1L))
                    .setTitle("Space Sweepers")
                    .setCountry(countryService.findCountry(CountryEnum.KOREA))
                    .setReleaseDate(LocalDate.of(2020, 2, 5))
                    .setDirector("Jo Sung-Hee")
                    .setDistributor("Netflix")
                    .setDescription("Tae-Ho (Song Joong-Ki) is a pilot of Spaceship Victory, which is led by a captain (Kim Tae-Ri). Tae-Ho will do anything to make money, but he is always broke. Tiger Park (Jin Sun-Kyu) and a robot (Yu Hae-Jin) are crew members of the spaceship.")
                    .setGenre((List.of(genreService.findGenre(GenreEnum.SCI_FI))))
                    .setCast("Song Joong-Ki, Kim Tae-Ri, Jin Sun-Kyu, Yu Hae-Jin, Park Ye-Rin, Richard Armitage\tKim Moo-Yul, Oh Ji-Yul, Kim Hyang-Gi")
                    .setImageUrl("https://asianwiki.com/images/5/52/Space_Sweepers-mp01.jpg")
                    .setTrailerUrl("H1WYnJF1Pwo");


            movieRepository.save(theDudeInMe);
            movieRepository.save(parasite);
            movieRepository.save(spaceSweepers);
        }
    }

    @Override
    public void addMovie(MovieServiceModel movieServiceModel) {
        MovieEntity movieEntity = modelMapper
                .map(movieServiceModel, MovieEntity.class);


        List<GenreEntity> genreEntities = new ArrayList<>();
        movieServiceModel.getGenre().forEach(g -> {
            GenreEntity genreEntity = genreService.findGenre(g);
            genreEntities.add(genreEntity);
        });

        CountryEntity countryEntity = countryService.findCountry(movieServiceModel.getCountry().getName());

        movieEntity
                .setCountry(countryEntity)
                .setGenre(genreEntities)
                .setAddedOn(LocalDate.now());


        UserEntity user = userService.findUser(movieServiceModel.getUser());

        movieEntity.setUser(user);

        movieRepository.save(movieEntity);

    }

    @Override
    public List<MovieViewModel> displayAllMovies() {
        return movieRepository.findAllMoviesDesc()
                .stream()
                .map(this::mapMovieViewModelToMovieEntity)
                .collect(Collectors.toList());
    }

    @Override
    public MovieViewModel findById(Long id) {
        return movieRepository
                .findById(id)
                .map(movieEntity -> modelMapper
                        .map(movieEntity, MovieViewModel.class))
                .orElseThrow(ObjectNotFoundException::new);
    }

    @Override
    public void delete(Long id) {
        MovieEntity movieEntity = movieRepository.findById(id).orElseThrow(ObjectNotFoundException::new);

        Long movieAddedByUserId = movieEntity.getUser().getId();

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String username = authentication.getName();
        UserEntity user = userService.findUser(username);

        if (movieAddedByUserId.equals(user.getId()) || user.getId() == 1) {
            movieRepository.deleteById(id);
        }
    }

    @Override
    public List<MovieViewModel> displayUserMovies(UserEntity user) {

        return movieRepository.findAllByUser(user)
                .stream()
                .map(this::mapMovieViewModelToMovieEntity)
                .collect(Collectors.toList());
    }

    @Override
    public MovieEntity findMovieById(Long id) {
        return movieRepository
                .findById(id)
                .orElseThrow(ObjectNotFoundException::new);
    }

    public MovieViewModel mapMovieViewModelToMovieEntity(MovieEntity movieEntity) {
        MovieViewModel movieViewModel = new MovieViewModel();
        modelMapper.map(movieViewModel, MovieEntity.class);

        movieViewModel
                .setUser(movieEntity.getUser()
                )
                .setId(movieEntity.getId())
                .setTitle(movieEntity.getTitle())
                .setImageUrl(movieEntity.getImageUrl())
                .setTrailerUrl(movieEntity.getTrailerUrl())
                .setDescription(movieEntity.getDescription())
                .setDirector(movieEntity.getDirector())
                .setReleaseDate(movieEntity.getReleaseDate())
                .setGenre(movieEntity.getGenre())
                .setDistributor(movieEntity.getDistributor())
                .setCountry(movieEntity.getCountry())
                .setCast(movieEntity.getCast())
                .setAddedOn(LocalDate.now());

        return movieViewModel;
    }
}
