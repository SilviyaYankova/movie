package bg.softuni.movie.service;

import bg.softuni.movie.model.entity.CountryEntity;
import bg.softuni.movie.model.entity.enums.CountryEnum;
import bg.softuni.movie.model.entity.enums.GenreEnum;

import java.util.List;

public interface CountryService {

    void seedCountries();

    CountryEntity findCountry(CountryEnum name);
}
