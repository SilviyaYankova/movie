package bg.softuni.movie.web;

import bg.softuni.movie.model.view.MovieViewModel;
import bg.softuni.movie.repository.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/movies")
@RestController
public class MovieRestController {

    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    public MovieRestController(MovieRepository movieRepository, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/movie-api")
    private ResponseEntity<List<MovieViewModel>> allDramas() {

        List<MovieViewModel> movieViewModels = movieRepository
                .findAll()
                .stream()
                .map(movie -> modelMapper
                        .map(movie, MovieViewModel.class)).collect(Collectors.toList());


        return ResponseEntity
                .ok()
                .body(movieViewModels);
    }
}
