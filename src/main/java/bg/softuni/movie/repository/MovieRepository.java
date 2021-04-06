package bg.softuni.movie.repository;
import bg.softuni.movie.model.entity.MovieEntity;
import bg.softuni.movie.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    @Query("select m from MovieEntity as m order by m.id desc")
    List<MovieEntity> findAllMoviesDesc();

    List<MovieEntity> findAllByUser(UserEntity username);
}
