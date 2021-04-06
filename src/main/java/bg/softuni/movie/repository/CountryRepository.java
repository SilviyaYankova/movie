package bg.softuni.movie.repository;

import bg.softuni.movie.model.entity.CountryEntity;
import bg.softuni.movie.model.entity.enums.CountryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Long> {

    CountryEntity findByName(CountryEnum name);
}
