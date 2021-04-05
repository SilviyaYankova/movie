package bg.softuni.movie.service;

import bg.softuni.movie.model.entity.MovieEntity;
import bg.softuni.movie.model.entity.UserEntity;
import bg.softuni.movie.model.service.MovieServiceModel;
import bg.softuni.movie.model.view.MovieViewModel;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    void addMovie(MovieServiceModel movieServiceModel);

    List<MovieViewModel> displayAllMovies();

    MovieViewModel findById(Long id);

    void delete(Long id);

    List<MovieViewModel> displayUserMovies(UserEntity user);

    MovieEntity findMovieById(Long id);


}
