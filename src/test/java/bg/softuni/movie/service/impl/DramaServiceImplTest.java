//package bg.softuni.movie.service.impl;
//
//import bg.softuni.movie.model.entity.*;
//import bg.softuni.movie.model.entity.enums.CountryEnum;
//import bg.softuni.movie.model.entity.enums.GenreEnum;
//import bg.softuni.movie.model.entity.enums.UserRoleEnum;
//import bg.softuni.movie.model.view.DramaViewModel;
//import bg.softuni.movie.repository.DramaRepository;
//import bg.softuni.movie.repository.UserRepository;
//import bg.softuni.movie.service.CountryService;
//import bg.softuni.movie.service.GenreService;
//import bg.softuni.movie.service.UserService;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.modelmapper.ModelMapper;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class DramaServiceImplTest {
//
//    private UserEntity testUser1;
//    private DramaEntity testDramaEntity1;
//
//    private DramaServiceImpl serviceToTest;
//
//    @Mock
//    private DramaRepository dramaRepository;
//    @Mock
//    private UserService userService;
//    @Mock
//    private GenreService genreService;
//    @Mock
//    private CountryService countryService;
//    @Mock
//    private ModelMapper modelMapper;
//
//    @BeforeEach
//    public void init() {
//        testUser1 = new UserEntity();
//        testUser1.setUsername("user 1");
//
//        testDramaEntity1 = new DramaEntity();
//        testDramaEntity1
//                .setAddedOn(LocalDate.now())
//                .setUser(userService.findUserById(1L))
//                .setTitle("Because This Is My First Life")
//                .setEpisodes(16)
//                .setCountry(countryService.findCountry(CountryEnum.KOREA))
//                .setReleaseDate(LocalDate.of(2017, 8, 8))
//                .setDirector("Park Joon-Hwa")
//                .setDistributor("tvN")
//                .setDescription("Nam Se-Hee (Lee Min-Ki) is a single man in his early 30's. He has chosen to not marry. He owns his home, but he owes a lot on his mortgage. Yoon Ji-Ho (Jung So-Min) is a single woman in her early 30's. She does not own a home and envies those that do. She has given up on dating due to her financial struggles. Yoon Ji-Ho begins to live at Nam Se-Hee’s house. They become housemates.")
//                .setGenre((List.of(genreService.findGenre(GenreEnum.DRAMA),
//                        genreService.findGenre(GenreEnum.FAMILY),
//                        genreService.findGenre(GenreEnum.ROMANCE))))
//                .setCast("Nam Se-Hee, Yoon Ji-Ho, Woo Soo-Ji, Park Byung-Eun, Kim Ga-Eun, Kim Min-Suk")
//                .setImageUrl("https://asianwiki.com/images/5/5d/Because_This_Is_My_First_Life-P1.jpg")
//                .setTrailerUrl("PJ2SMZv-W8s")
//                .setUser(testUser1);
//
//        dramaRepository.save(testDramaEntity1);
//
//        serviceToTest = new DramaServiceImpl(
//                dramaRepository,
//                userService,
//                genreService,
//                countryService,
//                modelMapper);
//    }
//
//
//    @Test
//    public void findAllDramas() {
//        when(dramaRepository.findAll())
//                .thenReturn(List.of(testDramaEntity1));
//
//        List<DramaViewModel> allDramas = serviceToTest.displayAllDramas();
//
//        DramaViewModel model1 = allDramas.get(0);
//
//        Assertions.assertEquals(testDramaEntity1.getTitle(), model1.getTitle());
//        Assertions.assertEquals(testDramaEntity1.getImageUrl(), model1.getImageUrl());
//        Assertions.assertEquals(testDramaEntity1.getGenre(), model1.getGenre());
//    }
//
//}