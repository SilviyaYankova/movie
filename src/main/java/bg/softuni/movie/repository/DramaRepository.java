package bg.softuni.movie.repository;


import bg.softuni.movie.model.entity.DramaEntity;
import bg.softuni.movie.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DramaRepository extends JpaRepository<DramaEntity, Long> {

    @Query("select d from DramaEntity as d order by d.id desc")
    List<DramaEntity> findAllDramaDesc();

    List<DramaEntity> findAllByUser(UserEntity username);

}
