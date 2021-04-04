package bg.softuni.movie.service;

import bg.softuni.movie.model.service.CommentServiceModel;
import bg.softuni.movie.model.view.DramaViewModel;

public interface CommentService {
    void addComment(CommentServiceModel commentServiceModel, DramaViewModel drama);
}
