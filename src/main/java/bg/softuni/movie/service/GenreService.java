package bg.softuni.movie.service;

import bg.softuni.movie.model.entity.GenreEntity;
import bg.softuni.movie.model.entity.enums.GenreEnum;

public interface GenreService {

    void seedGenres();

    GenreEntity findGenre(GenreEnum name);
}
