package bg.softuni.movie.service.impl;

import bg.softuni.movie.model.entity.DramaEntity;
import bg.softuni.movie.model.entity.LogEntity;
import bg.softuni.movie.model.entity.MovieEntity;
import bg.softuni.movie.model.entity.UserEntity;
import bg.softuni.movie.model.service.LogServiceModel;
import bg.softuni.movie.repository.LogRepository;
import bg.softuni.movie.service.DramaService;
import bg.softuni.movie.service.LogService;
import bg.softuni.movie.service.MovieService;
import bg.softuni.movie.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;
    private final DramaService dramaService;
    private final MovieService movieService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public LogServiceImpl(LogRepository logRepository, DramaService dramaService, MovieService movieService, UserService userService, ModelMapper modelMapper) {
        this.logRepository = logRepository;
        this.dramaService = dramaService;
        this.movieService = movieService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createDramaLog(String action, Long dramaId) {
        DramaEntity dramaEntity = dramaService.findDramaById(dramaId);

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String username = authentication.getName();

        UserEntity user = userService.findUser(username);

        LogEntity logEntity = new LogEntity()
                .setDrama(dramaEntity.getTitle())
                .setUser(user)
                .setAction(action)
                .setDateTime(LocalDateTime.now());

        logRepository.save(logEntity);
    }

    @Override
    public void createMovieLog(String action, Long movieId) {
        MovieEntity movieEntity = movieService.findMovieById(movieId);

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String username = authentication.getName();

        UserEntity user = userService.findUser(username);

        LogEntity logEntity = new LogEntity()
                .setMovie(movieEntity.getTitle())
                .setUser(user)
                .setAction(action)
                .setDateTime(LocalDateTime.now());

        logRepository.save(logEntity);
    }

    @Override
    public List<LogServiceModel> findAllLogs() {
        return logRepository
                .findAll()
                .stream()
                .map(logEntity -> {
                    LogServiceModel logServiceModel = modelMapper
                            .map(logEntity, LogServiceModel.class);

                    logServiceModel
                            .setUser(logEntity.getUser().getUsername());

                    if (logEntity.getDrama() == null) {
                        logServiceModel.setDrama("——————————");
                        logServiceModel.setMovie(logEntity.getMovie());
                    } else if (logEntity.getMovie() == null) {
                        logServiceModel.setMovie("——————————");
                        logServiceModel.setDrama(logEntity.getDrama());
                    }

                    return logServiceModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteOlderThen24Hours() {
        logRepository.deleteAll();
    }


}
