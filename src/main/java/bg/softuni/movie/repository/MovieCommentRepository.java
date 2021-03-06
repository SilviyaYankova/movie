package bg.softuni.movie.repository;

import bg.softuni.movie.model.entity.MovieCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieCommentRepository extends JpaRepository<MovieCommentEntity, Long> {

}
