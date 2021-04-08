package bg.softuni.movie.repository;

import bg.softuni.movie.model.entity.DramaCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DramaCommentRepository extends JpaRepository<DramaCommentEntity, Long> {


}
