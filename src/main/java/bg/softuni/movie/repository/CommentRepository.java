package bg.softuni.movie.repository;

import bg.softuni.movie.model.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {


    @Query("select c from CommentEntity c")
    List<CommentEntity> deleteCommentsByDramaId(Long id);

}
