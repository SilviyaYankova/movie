package bg.softuni.movie.repository;

import bg.softuni.movie.model.entity.UserEntity;
import bg.softuni.movie.model.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByUsername(String userName);

  Optional<UserEntity> findByEmail(String email);

  Optional<UserEntity> findById(Long id);



}
