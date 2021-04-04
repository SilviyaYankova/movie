package bg.softuni.movie.service.impl;

import bg.softuni.movie.model.entity.CommentEntity;
import bg.softuni.movie.model.entity.DramaEntity;
import bg.softuni.movie.model.entity.UserEntity;
import bg.softuni.movie.model.service.CommentServiceModel;
import bg.softuni.movie.model.view.DramaViewModel;
import bg.softuni.movie.repository.CommentRepository;
import bg.softuni.movie.repository.DramaRepository;
import bg.softuni.movie.service.CommentService;
import bg.softuni.movie.service.DramaService;
import bg.softuni.movie.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final DramaRepository dramaRepository;
    private final CommentRepository commentRepository;
    private final DramaService dramaService;
    private final UserService userService;
    private final ModelMapper modelMapper;


    public CommentServiceImpl(DramaRepository dramaRepository, CommentRepository commentRepository, DramaService dramaService, UserService userService, ModelMapper modelMapper) {
        this.dramaRepository = dramaRepository;
        this.commentRepository = commentRepository;
        this.dramaService = dramaService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @Override
    public void addComment(CommentServiceModel commentServiceModel, DramaViewModel drama) {
        CommentEntity commentEntity = modelMapper
                .map(commentServiceModel, CommentEntity.class);

        UserEntity user = userService.findUser(commentServiceModel.getUser());

        commentEntity
                .setAddedOn(LocalDate.now())
                .setUserEntity(user);


        DramaEntity dramaEntity = dramaService.findDramaById(drama.getId()).get();

        List<CommentEntity> comments = dramaEntity.getComments();
        comments.add(commentEntity);

        dramaEntity
                .setComments(comments)
                .setUser(user);

        commentRepository.save(commentEntity);
        dramaRepository.save(dramaEntity);
    }
}
