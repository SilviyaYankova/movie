package bg.softuni.movie.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "drama_comments")
public class DramaCommentEntity extends BaseEntity{

    private UserEntity userEntity;
    private String content;
    private LocalDateTime addedOn;

    public DramaCommentEntity() {
    }

    @OneToOne
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public DramaCommentEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    public String getContent() {
        return content;
    }

    public DramaCommentEntity setContent(String content) {
        this.content = content;
        return this;
    }

    @Column(name = "added_on")
    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public DramaCommentEntity setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }
}
