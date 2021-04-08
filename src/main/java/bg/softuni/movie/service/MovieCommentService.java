package bg.softuni.movie.service;

import bg.softuni.movie.model.service.CommentServiceModel;
import bg.softuni.movie.model.view.MovieViewModel;

public interface MovieCommentService {

    void addMovieComment(CommentServiceModel commentServiceModel, MovieViewModel movie);
}
