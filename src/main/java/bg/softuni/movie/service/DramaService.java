package bg.softuni.movie.service;

import bg.softuni.movie.model.entity.DramaEntity;
import bg.softuni.movie.model.entity.UserEntity;
import bg.softuni.movie.model.service.DramaServiceModel;
import bg.softuni.movie.model.view.DramaViewModel;

import java.util.List;
import java.util.Optional;

public interface DramaService {

    void addDrama(DramaServiceModel dramaServiceModel);

    List<DramaViewModel> displayAllDramas();

    DramaViewModel findById(Long id);

    void delete(Long id);

    List<DramaViewModel> displayUserDramas(UserEntity user);

    Optional<DramaEntity> findDramaById(Long id);
}
