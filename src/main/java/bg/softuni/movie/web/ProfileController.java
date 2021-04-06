package bg.softuni.movie.web;

import bg.softuni.movie.model.entity.UserEntity;
import bg.softuni.movie.model.service.UserServiceModel;
import bg.softuni.movie.model.view.DramaViewModel;
import bg.softuni.movie.model.view.MovieViewModel;
import bg.softuni.movie.model.view.UserViewModel;
import bg.softuni.movie.service.DramaService;
import bg.softuni.movie.service.MovieService;
import bg.softuni.movie.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class ProfileController {

    private final UserService userService;
    private final DramaService dramaService;
    private final ModelMapper modelMapper;
    private final MovieService movieService;

    public ProfileController(UserService userService, DramaService dramaService, ModelMapper modelMapper, MovieService movieService) {
        this.userService = userService;
        this.dramaService = dramaService;
        this.modelMapper = modelMapper;
        this.movieService = movieService;
    }

    @GetMapping("/profile")
    public String profile(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserEntity userEntity = userService.findUser(username);

        UserViewModel userViewModel = modelMapper.map(userEntity, UserViewModel.class);

        List<String> userRoles = new ArrayList<>();
        userViewModel
                .getRoles()
                .forEach(r -> {
                    userRoles.add(r.getRole().name());
                });

        model.addAttribute("userDetailsViewModel", new UserViewModel());
        model.addAttribute("user", userViewModel);
        model.addAttribute("userRoles", userRoles);

        return "profile";
    }

    @PostMapping("/add-picture")
    public String add(UserServiceModel userServiceModel) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        userService.addPicture(userServiceModel, username);

        return "redirect:/users/profile";
    }


    @GetMapping("/my-dramas")
    public String myDramas(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserEntity user = userService.findUser(username);

        List<DramaViewModel> userDramas = dramaService.displayUserDramas(user);

        model.addAttribute("userDramas", userDramas);

        return "my-dramas";
    }

    @GetMapping("/my-movies")
    public String myMovies(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserEntity user = userService.findUser(username);

        List<MovieViewModel> userMovies = movieService.displayUserMovies(user);

        model.addAttribute("userMovies", userMovies);

        return "my-movies";
    }
}
