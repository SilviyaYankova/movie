package bg.softuni.movie.service.impl;

import bg.softuni.movie.exceptions.ObjectNotFoundException;
import bg.softuni.movie.model.entity.CountryEntity;
import bg.softuni.movie.model.entity.DramaEntity;
import bg.softuni.movie.model.entity.GenreEntity;
import bg.softuni.movie.model.entity.UserEntity;
import bg.softuni.movie.model.entity.enums.CountryEnum;
import bg.softuni.movie.model.entity.enums.GenreEnum;
import bg.softuni.movie.model.service.DramaServiceModel;
import bg.softuni.movie.model.view.DramaViewModel;
import bg.softuni.movie.repository.DramaRepository;
import bg.softuni.movie.repository.LogRepository;
import bg.softuni.movie.service.CountryService;
import bg.softuni.movie.service.DramaService;
import bg.softuni.movie.service.GenreService;
import bg.softuni.movie.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public void seedDramas() {
        if (dramaRepository.count() == 0) {
            DramaEntity becauseThisIsMyFirstLife = new DramaEntity()
                    .setAddedOn(LocalDate.now())
                    .setUser(userService.findUserById(1L))
                    .setTitle("Because This Is My First Life")
                    .setEpisodes(16)
                    .setCountry(countryService.findCountry(CountryEnum.KOREA))
                    .setReleaseDate(LocalDate.of(2017, 8, 8))
                    .setDirector("Park Joon-Hwa")
                    .setDistributor("tvN")
                    .setDescription("Nam Se-Hee (Lee Min-Ki) is a single man in his early 30's. He has chosen to not marry. He owns his home, but he owes a lot on his mortgage. Yoon Ji-Ho (Jung So-Min) is a single woman in her early 30's. She does not own a home and envies those that do. She has given up on dating due to her financial struggles. Yoon Ji-Ho begins to live at Nam Se-Hee’s house. They become housemates.")
                    .setGenre((List.of(genreService.findGenre(GenreEnum.DRAMA),
                            genreService.findGenre(GenreEnum.FAMILY),
                            genreService.findGenre(GenreEnum.ROMANCE))))
                    .setCast("Nam Se-Hee, Yoon Ji-Ho, Woo Soo-Ji, Park Byung-Eun, Kim Ga-Eun, Kim Min-Suk")
                    .setImageUrl("https://asianwiki.com/images/5/5d/Because_This_Is_My_First_Life-P1.jpg")
                    .setTrailerUrl("PJ2SMZv-W8s");

            DramaEntity tunnel = new DramaEntity()
                    .setAddedOn(LocalDate.now())
                    .setUser(userService.findUserById(1L))
                    .setTitle("Tunnel")
                    .setEpisodes(16)
                    .setCountry(countryService.findCountry(CountryEnum.KOREA))
                    .setReleaseDate(LocalDate.of(2017, 5, 25))
                    .setDirector("Shin Yong-Hwi, Nam Ki-Hoon")
                    .setDistributor("OCN")
                    .setDescription("In 1986, Detective Park Gwang-Ho (Choi Jin-Hyuk) desperately tries to catch a serial killer. He chases after the serial killer and goes through a tunnel. On the other side of the tunnel, Detective Park Gwang-Ho finds himself in the year 2017. The serial killer has resumed the killings that began 30 years ago. Detective Park Gwang-Ho works with Detective Kim Sun-Jae (Yoon Hyun-Min) and Professor of Criminal Psychology Shin Jae-Yi (Lee Yoo-Young) to catch the killer.")
                    .setGenre((List.of(genreService.findGenre(GenreEnum.DRAMA),
                            genreService.findGenre(GenreEnum.FANTASY),
                            genreService.findGenre(GenreEnum.THRILLER))))
                    .setCast("Choi Jin-Hyuk, Yoon Hyun-Min, Lee Yoo-Young, Jo Hee-Bong, Kim Byung-Chul, Kang Ki-Young, Lee Shi-A, Kim Min-Sang, Yang Joo-Ho, Cha Hak-Yeon")
                    .setImageUrl("https://asianwiki.com/images/d/d4/Tunnel_%28Korean_Drama%29-p1.jpg")
                    .setTrailerUrl("LtYSObN0pOU");

            DramaEntity pasta = new DramaEntity()
                    .setAddedOn(LocalDate.now())
                    .setUser(userService.findUserById(1L))
                    .setTitle("Pasta")
                    .setEpisodes(20)
                    .setCountry(countryService.findCountry(CountryEnum.KOREA))
                    .setReleaseDate(LocalDate.of(2010, 3, 4))
                    .setDirector("Kwon Seok-Jang")
                    .setDistributor("MBC")
                    .setDescription("\"Pasta\" covers the dreams and success of a young woman who aspires to become an elite chef at La Sfera restaurant.\n" +
                            "\n" +
                            "Seo Yoo Kyung started her career as a kitchen assistant at La Sfera restaurant. She eventually works her way up to become a chef.\n" +
                            "\n" +
                            "Choi Hyun Wook is the top chef at La Sfera restaurant. He studied the culinary arts in Italy and started out as a chef assistant at a hotel in Sicily. Hyun Wook eventually worked his way up to become the most widely recognized Italian chef in Korea.")
                    .setGenre((List.of(genreService.findGenre(GenreEnum.COMEDY),
                            genreService.findGenre(GenreEnum.ROMANCE))))
                    .setCast("Kong Hyo-Jin, Lee Sun-Kyun, Lee Ha-Nee, Alex, Choi Min, Pyeon Jung-Su, Jang Yong, Kim Dong-Hee, Lee Sung-Min, Yun Yong-Hyeon")
                    .setImageUrl("https://asianwiki.com/images/2/2c/Pasta-p1.jpg")
                    .setTrailerUrl("Ma0O4VxCLd");

            dramaRepository.save(becauseThisIsMyFirstLife);
            dramaRepository.save(tunnel);
            dramaRepository.save(pasta);
        }
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
                .map(this::mapDramaViewModelToDramaEntity)
                .collect(Collectors.toList());
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
        DramaEntity dramaEntity = dramaRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        Long dramaAddedByUserId = dramaEntity.getUser().getId();

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String username = authentication.getName();
        UserEntity user = userService.findUser(username);

        if (dramaAddedByUserId.equals(user.getId()) || user.getId() == 1) {
            dramaRepository.deleteById(id);
        }
    }

    @Override
    public List<DramaViewModel> displayUserDramas(UserEntity user) {
        return dramaRepository.findAllByUser(user)
                .stream()
                .map(this::mapDramaViewModelToDramaEntity)
                .collect(Collectors.toList());
    }

    @Override
    public DramaEntity findDramaById(Long id) {
        return dramaRepository
                .findById(id)
                .orElseThrow(ObjectNotFoundException::new);
    }

    public DramaViewModel mapDramaViewModelToDramaEntity(DramaEntity dramaEntity) {
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
                .setComments(dramaEntity.getComments())
                .setUser(dramaEntity.getUser());

        return dramaViewModel;
    }
}
