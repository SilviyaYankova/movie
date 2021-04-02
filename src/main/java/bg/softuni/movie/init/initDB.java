package bg.softuni.movie.init;

import bg.softuni.movie.service.GenreService;
import bg.softuni.movie.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class initDB implements CommandLineRunner {

    private final UserService userService;
    private final GenreService genreService;

    public initDB(UserService userService, GenreService genreService) {
        this.userService = userService;
        this.genreService = genreService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.seedUsers();
        genreService.seedGenres();
    }
}
