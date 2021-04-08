package bg.softuni.movie.model.service;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class LogServiceModel {

    private Long id;
    private String user;
    private String drama;
    private String movie;
    private String action;
    private LocalDateTime dateTime;

    public LogServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public LogServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUser() {
        return user;
    }

    public LogServiceModel setUser(String user) {
        this.user = user;
        return this;
    }

    public String getDrama() {
        return drama;
    }

    public LogServiceModel setDrama(String drama) {
        this.drama = drama;
        return this;
    }

    public String getMovie() {
        return movie;
    }

    public LogServiceModel setMovie(String movie) {
        this.movie = movie;
        return this;
    }

    public String getAction() {
        return action;
    }

    public LogServiceModel setAction(String action) {
        this.action = action;
        return this;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LogServiceModel setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }
}
