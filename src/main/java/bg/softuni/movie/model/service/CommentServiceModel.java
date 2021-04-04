package bg.softuni.movie.model.service;

import java.time.LocalDate;

public class CommentServiceModel {

    private String user;
    private String content;
    private LocalDate addedOn;

    public CommentServiceModel() {
    }

    public String getUser() {
        return user;
    }

    public CommentServiceModel setUser(String user) {
        this.user = user;
        return this;
    }

    public String getContent() {
        return content;
    }

    public CommentServiceModel setContent(String content) {
        this.content = content;
        return this;
    }

    public LocalDate getAddedOn() {
        return addedOn;
    }

    public CommentServiceModel setAddedOn(LocalDate addedOn) {
        this.addedOn = addedOn;
        return this;
    }

}
