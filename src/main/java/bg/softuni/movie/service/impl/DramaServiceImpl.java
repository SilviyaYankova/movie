package bg.softuni.movie.service.impl;

import bg.softuni.movie.exceptions.ObjectNotFoundException;
import bg.softuni.movie.model.entity.CountryEntity;
import bg.softuni.movie.model.entity.DramaEntity;
import bg.softuni.movie.model.entity.GenreEntity;
import bg.softuni.movie.model.entity.UserEntity;
import bg.softuni.movie.model.service.DramaServiceModel;
import bg.softuni.movie.model.view.DramaViewModel;
import bg.softuni.movie.repository.CommentRepository;
import bg.softuni.movie.repository.DramaRepository;
import bg.softuni.movie.service.CountryService;
import bg.softuni.movie.service.DramaService;
import bg.softuni.movie.service.GenreService;
import bg.softuni.movie.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DramaServiceImpl implements DramaService {

    private final DramaRepository dramaRepository;
    private final UserService userService;
    private final GenreService genreService;
    private final CountryService countryService;
    private final ModelMapper modelMapper;

    public DramaServiceImpl(DramaRepository dramaRepository, UserService userService, GenreService genreService, CountryService countryService, ModelMapper modelMapper) {
        this.dramaRepository = dramaRepository;
        this.userService = userService;
        this.genreService = genreService;
        this.countryService = countryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addDrama(DramaServiceModel dramaServiceModel) {

        DramaEntity dramaEntity = modelMapper
                .map(dramaServiceModel, DramaEntity.class);

        List<GenreEntity> genreEntities = new ArrayList<>();
        dramaServiceModel.getGenre().forEach(g -> {
            GenreEntity genreEntity = genreService.findGenre(g);
            genreEntities.add(genreEntity);
        });

        CountryEntity countryEntity = countryService.findCountry(dramaServiceModel.getCountry().getName());

        dramaEntity
                .setCountry(countryEntity)
                .setGenre(genreEntities)
                .setAddedOn(LocalDate.now());

        UserEntity user = userService.findUser(dramaServiceModel.getUser());

        dramaEntity.setUser(user);

        dramaRepository.save(dramaEntity);

    }

    @Override
    public List<DramaViewModel> displayAllDramas() {

        return dramaRepository.findAllDramaDesc()
                .stream()
                .map(dramaEntity -> {
                    DramaViewModel dramaViewModel = new DramaViewModel();
                    modelMapper.map(dramaViewModel, DramaEntity.class);

                    dramaViewModel
                            .setId(dramaEntity.getId())
                            .setTitle(dramaEntity.getTitle())
                            .setEpisodes(dramaEntity.getEpisodes())
                            .setImageUrl(dramaEntity.getImageUrl())
                            .setTrailerUrl(dramaEntity.getTrailerUrl())
                            .setDescription(dramaEntity.getDescription())
                            .setDirector(dramaEntity.getDirector())
                            .setReleaseDate(dramaEntity.getReleaseDate())
                            .setGenre(dramaEntity.getGenre())
                            .setDistributor(dramaEntity.getDistributor())
                            .setCountry(dramaEntity.getCountry())
                            .setCast(dramaEntity.getCast())
                            .setAddedOn(LocalDate.now())
                            .setComments(dramaEntity.getComments());

                    return dramaViewModel;
                }).collect(Collectors.toList());
    }

    @Override
    public DramaViewModel findById(Long id) {
        return dramaRepository
               .findById(id)
               .map(dramaEntity -> modelMapper
                       .map(dramaEntity, DramaViewModel.class))
                .orElseThrow(ObjectNotFoundException::new);
    }

    @Override
    public void delete(Long id) {
        dramaRepository.deleteById(id);
    }

    @Override
    public List<DramaViewModel> displayUserDramas(UserEntity user) {
        return dramaRepository.findAllByUser(user)
                .stream()
                .map(dramaEntity -> {
                    DramaViewModel dramaViewModel = new DramaViewModel();
                    modelMapper.map(dramaViewModel, DramaEntity.class);

                    dramaViewModel
                            .setId(dramaEntity.getId())
                            .setTitle(dramaEntity.getTitle())
                            .setEpisodes(dramaEntity.getEpisodes())
                            .setImageUrl(dramaEntity.getImageUrl())
                            .setTrailerUrl(dramaEntity.getTrailerUrl())
                            .setDescription(dramaEntity.getDescription())
                            .setDirector(dramaEntity.getDirector())
                            .setReleaseDate(dramaEntity.getReleaseDate())
                            .setGenre(dramaEntity.getGenre())
                            .setDistributor(dramaEntity.getDistributor())
                            .setCountry(dramaEntity.getCountry())
                            .setCast(dramaEntity.getCast())
                            .setAddedOn(LocalDate.now())
                            .setComments(dramaEntity.getComments());

                    return dramaViewModel;
                }).collect(Collectors.toList());
    }

    @Override
    public DramaEntity findDramaById(Long id) {
        return dramaRepository
                .findById(id)
                .orElseThrow(ObjectNotFoundException::new);
    }

}
