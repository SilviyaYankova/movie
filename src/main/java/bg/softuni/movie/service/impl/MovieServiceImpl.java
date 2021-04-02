package bg.softuni.movie.service.impl;

import bg.softuni.movie.model.entity.DramaEntity;
import bg.softuni.movie.model.entity.GenreEntity;
import bg.softuni.movie.model.entity.MovieEntity;
import bg.softuni.movie.model.entity.UserEntity;
import bg.softuni.movie.model.service.MovieServiceModel;
import bg.softuni.movie.model.view.DramaViewModel;
import bg.softuni.movie.model.view.MovieViewModel;
import bg.softuni.movie.repository.MovieRepository;
import bg.softuni.movie.service.GenreService;
import bg.softuni.movie.service.MovieService;
import bg.softuni.movie.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final UserService userService;
    private final GenreService genreService;
    private final ModelMapper modelMapper;

    public MovieServiceImpl(MovieRepository movieRepository, UserService userService, GenreService genreService, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.userService = userService;
        this.genreService = genreService;
        this.modelMapper = modelMapper;
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

        movieEntity.setGenre(genreEntities);


        UserEntity user = userService.findUser(movieServiceModel.getUser());

        movieEntity.setUser(user);

        movieRepository.save(movieEntity);

    }

    @Override
    public List<MovieViewModel> displayAllDramas() {
        return movieRepository.findAllMoviesDesc()
                .stream()
                .map(movieEntity -> {
                    MovieViewModel movieViewModel = new MovieViewModel();
                    modelMapper.map(movieViewModel, MovieEntity.class);

                    movieViewModel
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
                            .setCast(movieEntity.getCast());

                    return movieViewModel;
                }).collect(Collectors.toList());
    }

    @Override
    public MovieViewModel findById(Long id) {
        return movieRepository
                .findById(id)
                .map(movieEntity -> modelMapper
                        .map(movieEntity, MovieViewModel.class))
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void delete(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public List<MovieViewModel> displayUserMovies(UserEntity user) {

        return movieRepository.findAllByUser(user)
                .stream()
                .map(movieEntity -> {
                    MovieViewModel movieViewModel = new MovieViewModel();
                    modelMapper.map(movieViewModel, MovieEntity.class);

                    movieViewModel
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
                            .setCast(movieEntity.getCast());

                    return movieViewModel;
                }).collect(Collectors.toList());
    }
}
