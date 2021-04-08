package bg.softuni.movie.init;

import bg.softuni.movie.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class initDB implements CommandLineRunner {

    private final UserService userService;
    private final GenreService genreService;
    private final CountryService countryService;
    private final DramaService dramaService;
    private final MovieService movieService;

    public initDB(UserService userService, GenreService genreService, CountryService countryService, DramaService dramaService, MovieService movieService) {
        this.userService = userService;
        this.genreService = genreService;
        this.countryService = countryService;
        this.dramaService = dramaService;
        this.movieService = movieService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.seedUsers();
        genreService.seedGenres();
        countryService.seedCountries();
        dramaService.seedDramas();
        movieService.seedMovies();
    }
}
