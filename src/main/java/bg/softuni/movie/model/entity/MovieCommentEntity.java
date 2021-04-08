package bg.softuni.movie.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "movie_comments")
public class MovieCommentEntity extends BaseEntity{

    private UserEntity userEntity;
    private String content;
    private LocalDateTime addedOn;

    public MovieCommentEntity() {
    }

    @OneToOne
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public MovieCommentEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    public String getContent() {
        return content;
    }

    public MovieCommentEntity setContent(String content) {
        this.content = content;
        return this;
    }

    @Column(name = "added_on")
    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public MovieCommentEntity setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }
}
