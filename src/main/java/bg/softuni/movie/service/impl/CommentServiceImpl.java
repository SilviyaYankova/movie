package bg.softuni.movie.service.impl;

import bg.softuni.movie.exceptions.ObjectNotFoundException;
import bg.softuni.movie.model.entity.CommentEntity;
import bg.softuni.movie.model.entity.DramaEntity;
import bg.softuni.movie.model.entity.MovieEntity;
import bg.softuni.movie.model.entity.UserEntity;
import bg.softuni.movie.model.service.CommentServiceModel;
import bg.softuni.movie.model.view.DramaViewModel;
import bg.softuni.movie.model.view.MovieViewModel;
import bg.softuni.movie.repository.CommentRepository;
import bg.softuni.movie.repository.DramaRepository;
import bg.softuni.movie.repository.MovieRepository;
import bg.softuni.movie.service.CommentService;
import bg.softuni.movie.service.DramaService;
import bg.softuni.movie.service.MovieService;
import bg.softuni.movie.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final DramaRepository dramaRepository;
    private final MovieRepository movieRepository;
    private final CommentRepository commentRepository;
    private final DramaService dramaService;
    private final MovieService movieService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(DramaRepository dramaRepository, MovieRepository movieRepository, CommentRepository commentRepository, DramaService dramaService, MovieService movieService, UserService userService, ModelMapper modelMapper) {
        this.dramaRepository = dramaRepository;
        this.movieRepository = movieRepository;
        this.commentRepository = commentRepository;
        this.dramaService = dramaService;
        this.movieService = movieService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addDramaComment(CommentServiceModel commentServiceModel, DramaViewModel drama) {
        CommentEntity commentEntity = modelMapper
                .map(commentServiceModel, CommentEntity.class);

        UserEntity user = userService.findUser(commentServiceModel.getUser());

        commentEntity
                .setAddedOn(LocalDateTime.now())
                .setUserEntity(user);


        DramaEntity dramaEntity = dramaService
                .findDramaById(drama.getId());

        List<CommentEntity> comments = dramaEntity.getComments();
        comments.add(commentEntity);

        dramaEntity
                .setComments(comments)
                .setUser(user);

        commentRepository.save(commentEntity);
        dramaRepository.save(dramaEntity);
    }

    @Override
    public void addMovieComment(CommentServiceModel commentServiceModel, MovieViewModel movie) {
        CommentEntity commentEntity = modelMapper
                .map(commentServiceModel, CommentEntity.class);

        UserEntity user = userService.findUser(commentServiceModel.getUser());

        commentEntity
                .setAddedOn(LocalDateTime.now())
                .setUserEntity(user);

        MovieEntity movieEntity = movieService
                .findMovieById(movie.getId());

        List<CommentEntity> comments = movieEntity.getComments();
        comments.add(commentEntity);

        movieEntity
                .setComments(comments)
                .setUser(user);

        commentRepository.save(commentEntity);
        movieRepository.save(movieEntity);
    }


}
