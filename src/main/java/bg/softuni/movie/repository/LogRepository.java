package bg.softuni.movie.repository;

import bg.softuni.movie.model.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LogRepository extends JpaRepository<LogEntity, Long> {

}
