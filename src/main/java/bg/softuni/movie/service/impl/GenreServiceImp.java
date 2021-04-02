package bg.softuni.movie.service.impl;

import bg.softuni.movie.model.entity.GenreEntity;
import bg.softuni.movie.model.entity.enums.GenreEnum;
import bg.softuni.movie.repository.GenreRepository;
import bg.softuni.movie.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImp implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImp(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }


    @Override
    public void seedGenres() {
        if (genreRepository.count() == 0) {
            GenreEntity actionGenre = new GenreEntity().setName(GenreEnum.ACTION);
            GenreEntity adventureGenre = new GenreEntity().setName(GenreEnum.ADVENTURE);
            GenreEntity animationGenre = new GenreEntity().setName(GenreEnum.ANIMATION);
            GenreEntity comedyGenre = new GenreEntity().setName(GenreEnum.COMEDY);
            GenreEntity dramaGenre = new GenreEntity().setName(GenreEnum.DRAMA);
            GenreEntity familyGenre = new GenreEntity().setName(GenreEnum.FAMILY);
            GenreEntity fantasyGenre = new GenreEntity().setName(GenreEnum.FANTASY);
            GenreEntity historicalGenre = new GenreEntity().setName(GenreEnum.HISTORICAL);
            GenreEntity horrorGenre = new GenreEntity().setName(GenreEnum.HORROR);
            GenreEntity romanceGenre = new GenreEntity().setName(GenreEnum.ROMANCE);
            GenreEntity mysteryGenre = new GenreEntity().setName(GenreEnum.MYSTERY);
            GenreEntity psychologicalGenre = new GenreEntity().setName(GenreEnum.PSYCHOLOGICAL);
            GenreEntity sciFiGenre = new GenreEntity().setName(GenreEnum.SCI_FI);
            GenreEntity supernaturalGenre = new GenreEntity().setName(GenreEnum.SUPERNATURAL);
            GenreEntity thriller = new GenreEntity().setName(GenreEnum.THRILLER);
            genreRepository.saveAll(List.of(actionGenre, adventureGenre, animationGenre, comedyGenre, dramaGenre,
                    familyGenre, fantasyGenre, historicalGenre, horrorGenre, romanceGenre, mysteryGenre,
                    psychologicalGenre, sciFiGenre, supernaturalGenre, thriller));
        }
    }

    @Override
    public GenreEntity findGenre(GenreEnum name) {
        return genreRepository.findByName(name);
    }


}
