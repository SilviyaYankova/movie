package bg.softuni.movie.service;

import bg.softuni.movie.model.service.LogServiceModel;

import java.util.List;

public interface LogService {

    void createDramaLog(String action, Long dramaId);

    void createMovieLog(String action, Long movieId);

    List<LogServiceModel> findAllLogs();

    void deleteOlderThen24Hours();
}
