package bg.softuni.movie.web;

import bg.softuni.movie.model.entity.MovieEntity;
import bg.softuni.movie.repository.MovieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/dramas")
@RestController
public class MovieRestController {

    private final MovieRepository movieRepository;

    public MovieRestController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    @GetMapping("/movie-api")
    private ResponseEntity<List<MovieEntity>> allDramas() {
        return ResponseEntity
                .ok()
                .body(movieRepository.findAll());
    }
}
