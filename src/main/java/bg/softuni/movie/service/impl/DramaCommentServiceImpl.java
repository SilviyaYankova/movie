package bg.softuni.movie.service.impl;

import bg.softuni.movie.model.entity.*;
import bg.softuni.movie.model.service.CommentServiceModel;
import bg.softuni.movie.model.view.DramaViewModel;
import bg.softuni.movie.repository.DramaCommentRepository;
import bg.softuni.movie.repository.DramaRepository;
import bg.softuni.movie.service.DramaCommentService;
import bg.softuni.movie.service.DramaService;
import bg.softuni.movie.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DramaCommentServiceImpl implements DramaCommentService {

    private final DramaRepository dramaRepository;
    private final DramaCommentRepository dramaCommentRepository;
    private final DramaService dramaService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public DramaCommentServiceImpl(DramaRepository dramaRepository, DramaCommentRepository dramaCommentRepository, DramaService dramaService, UserService userService, ModelMapper modelMapper) {
        this.dramaRepository = dramaRepository;
        this.dramaCommentRepository = dramaCommentRepository;
        this.dramaService = dramaService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addDramaComment(CommentServiceModel commentServiceModel, DramaViewModel drama) {
        DramaCommentEntity commentEntity = modelMapper
                .map(commentServiceModel, DramaCommentEntity.class);

        UserEntity user = userService.findUser(commentServiceModel.getUser());

        DramaEntity dramaEntity = dramaService.findDramaById(drama.getId());

        commentEntity
                .setAddedOn(LocalDateTime.now())
                .setUserEntity(user);

        List<DramaCommentEntity> comments = dramaEntity.getComments();
        comments.add(commentEntity);

        dramaEntity
                .setComments(comments);

        dramaCommentRepository.save(commentEntity);
        dramaRepository.save(dramaEntity);
    }
}
