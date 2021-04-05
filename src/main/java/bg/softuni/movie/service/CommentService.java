package bg.softuni.movie.service;

import bg.softuni.movie.model.service.CommentServiceModel;
import bg.softuni.movie.model.view.DramaViewModel;
import bg.softuni.movie.model.view.MovieViewModel;

public interface CommentService {

    void addDramaComment(CommentServiceModel commentServiceModel, DramaViewModel drama);

    void addMovieComment(CommentServiceModel commentServiceModel, MovieViewModel movie);

}
