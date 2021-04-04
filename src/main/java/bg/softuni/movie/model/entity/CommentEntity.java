package bg.softuni.movie.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity{

    private UserEntity userEntity;
    private String content;
    private LocalDate addedOn;

    public CommentEntity() {
    }

    @OneToOne
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public CommentEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    public String getContent() {
        return content;
    }

    public CommentEntity setContent(String content) {
        this.content = content;
        return this;
    }

    @Column(name = "added_on")
    public LocalDate getAddedOn() {
        return addedOn;
    }

    public CommentEntity setAddedOn(LocalDate addedOn) {
        this.addedOn = addedOn;
        return this;
    }
}
