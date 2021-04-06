package bg.softuni.movie.model.binding;

import bg.softuni.movie.model.entity.UserEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CommentAddBindingModel {

    private UserEntity user;
    private String content;
    private LocalDateTime addedOn;

    public CommentAddBindingModel() {
    }

    public UserEntity getUser() {
        return user;
    }

    public CommentAddBindingModel setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public String getContent() {
        return content;
    }
    public CommentAddBindingModel setContent(String content) {
        this.content = content;
        return this;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public CommentAddBindingModel setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }
}
