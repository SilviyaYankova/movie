package bg.softuni.movie.model.view;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CommentViewModel {

    private Long id;
    private String user;
    private String content;
    private LocalDateTime addedOn;

    public CommentViewModel() {
    }

    public Long getId() {
        return id;
    }

    public CommentViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUser() {
        return user;
    }

    public CommentViewModel setUser(String user) {
        this.user = user;
        return this;
    }

    public String getContent() {
        return content;
    }

    public CommentViewModel setContent(String content) {
        this.content = content;
        return this;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public CommentViewModel setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }
}
