package bg.softuni.movie.service;

import bg.softuni.movie.model.entity.UserEntity;
import bg.softuni.movie.model.service.MovieServiceModel;
import bg.softuni.movie.model.view.DramaViewModel;
import bg.softuni.movie.model.view.MovieViewModel;

import java.util.List;

public interface MovieService {
    void addMovie(MovieServiceModel movieServiceModel);

    List<MovieViewModel> displayAllDramas();

    MovieViewModel findById(Long id);

    void delete(Long id);

    List<MovieViewModel> displayUserMovies(UserEntity user);
}
