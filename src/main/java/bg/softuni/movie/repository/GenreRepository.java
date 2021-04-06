package bg.softuni.movie.repository;

import bg.softuni.movie.model.entity.GenreEntity;
import bg.softuni.movie.model.entity.enums.GenreEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

    GenreEntity findByName(GenreEnum name);
}
