package bg.softuni.movie.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
public class LogEntity extends BaseEntity {

    private UserEntity user;
    private String drama;
    private String movie;
    private String action;
    private LocalDateTime dateTime;

    public LogEntity() {
    }

    @ManyToOne
    public UserEntity getUser() {
        return user;
    }

    public LogEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }


    @Column(name = "drama")
    public String getDrama() {
        return drama;
    }

    public LogEntity setDrama(String drama) {
        this.drama = drama;
        return this;
    }

    @Column(name = "movie")
    public String getMovie() {
        return movie;
    }

    public LogEntity setMovie(String movie) {
        this.movie = movie;
        return this;
    }

    @Column(name = "action", nullable = false)
    public String getAction() {
        return action;
    }

    public LogEntity setAction(String action) {
        this.action = action;
        return this;
    }

    @Column(name = "date_time", nullable = false)
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LogEntity setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }
}
