package bg.softuni.movie.service.impl;

import bg.softuni.movie.model.entity.MovieCommentEntity;
import bg.softuni.movie.model.entity.MovieEntity;
import bg.softuni.movie.model.entity.UserEntity;
import bg.softuni.movie.model.service.CommentServiceModel;
import bg.softuni.movie.model.view.MovieViewModel;
import bg.softuni.movie.repository.MovieCommentRepository;
import bg.softuni.movie.repository.MovieRepository;
import bg.softuni.movie.service.MovieCommentService;
import bg.softuni.movie.service.MovieService;
import bg.softuni.movie.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovieCommentServiceImpl implements MovieCommentService {

    private final MovieRepository movieRepository;
    private final MovieCommentRepository movieCommentRepository;
    private final UserService userService;
    private final MovieService movieService;
    private final ModelMapper modelMapper;

    public MovieCommentServiceImpl(MovieRepository movieRepository, MovieCommentRepository movieCommentRepository, UserService userService, MovieService movieService, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.movieCommentRepository = movieCommentRepository;
        this.userService = userService;
        this.movieService = movieService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addMovieComment(CommentServiceModel commentServiceModel, MovieViewModel movie) {
        MovieCommentEntity commentEntity = modelMapper
                .map(commentServiceModel, MovieCommentEntity.class);

        UserEntity user = userService.findUser(commentServiceModel.getUser());

        commentEntity
                .setAddedOn(LocalDateTime.now())
                .setUserEntity(user);

        MovieEntity movieEntity = movieService
                .findMovieById(movie.getId());

        List<MovieCommentEntity> comments = movieEntity.getComments();
        comments.add(commentEntity);

        movieEntity
                .setComments(comments);

        movieCommentRepository.save(commentEntity);
        movieRepository.save(movieEntity);
    }
}
