package bg.softuni.movie.web;

import bg.softuni.movie.model.binding.UserRegisterBindingModel;
import bg.softuni.movie.model.entity.UserEntity;
import bg.softuni.movie.model.service.UserDetailsServiceModel;
import bg.softuni.movie.model.service.UserRegisterServiceModel;
import bg.softuni.movie.model.view.DramaViewModel;
import bg.softuni.movie.model.view.MovieViewModel;
import bg.softuni.movie.model.view.UserDetailsViewModel;
import bg.softuni.movie.service.DramaService;
import bg.softuni.movie.service.MovieService;
import bg.softuni.movie.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final DramaService dramaService;
    private final MovieService movieService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, DramaService dramaService, MovieService movieService, ModelMapper modelMapper) {
        this.userService = userService;
        this.dramaService = dramaService;
        this.movieService = movieService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String login(Model model, String username) {
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
            model.addAttribute("userExistError", false);
            model.addAttribute("bad_credentials", false);
            model.addAttribute("username", username);
        }

        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
            model.addAttribute("userExistError", false);
            model.addAttribute("emailExistError", false);
        }

        return "register";
    }

    @PostMapping("/register")
    private String registerAndLoginUser(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                        BindingResult bindingResult,
                                        RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:/users/register";
        }

        if (userService.usernameAlreadyExists(userRegisterBindingModel.getUsername())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("userExistError", true);

            return "redirect:/users/register";
        }

        if (userService.emailAlreadyExists(userRegisterBindingModel.getEmail())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("emailExistError", true);

            return "redirect:/users/register";
        }

        UserRegisterServiceModel userRegisterServiceModel = modelMapper
                .map(userRegisterBindingModel, UserRegisterServiceModel.class);

        userService.registerAndLoginUser(userRegisterServiceModel);

        return "redirect:/";
    }

    //TODO
    @PostMapping("/login-error")
    public String failedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                      String username,
                              UserRegisterBindingModel userRegisterBindingModel,
                              RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
        redirectAttributes.addFlashAttribute("bad_credentials", true);
        redirectAttributes.addFlashAttribute("username", username);

        return "redirect:/users/login";
    }

    @GetMapping("/profile")
    public String profile(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserEntity userEntity = userService.findUser(username);

        UserDetailsViewModel userDetails = modelMapper.map(userEntity, UserDetailsViewModel.class);

        List<String> userRoles = new ArrayList<>();
        userDetails
                .getRoles()
                .forEach(r -> {
                    userRoles.add(r.getRole().name());
                });

        model.addAttribute("userDetailsViewModel", new UserDetailsViewModel());
        model.addAttribute("user", userDetails);
        model.addAttribute("userRoles", userRoles);

        return "profile";
    }

    @PostMapping("/add-picture")
    public String add(UserDetailsServiceModel userDetailsServiceModel) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        userService.addPicture(userDetailsServiceModel, username);

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
