package bg.softuni.movie.web;

import bg.softuni.movie.model.binding.CommentAddBindingModel;
import bg.softuni.movie.model.binding.MovieAddBindingModel;
import bg.softuni.movie.model.entity.UserEntity;
import bg.softuni.movie.model.entity.UserRoleEntity;
import bg.softuni.movie.model.service.CommentServiceModel;
import bg.softuni.movie.model.service.MovieServiceModel;
import bg.softuni.movie.model.view.MovieViewModel;
import bg.softuni.movie.service.MovieCommentService;
import bg.softuni.movie.service.MovieService;
import bg.softuni.movie.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private Long movieId;
    private final UserService userService;
    private final MovieService movieService;
    private final MovieCommentService movieCommentService;
    private final ModelMapper modelMapper;

    public MovieController(UserService userService, MovieService movieService, MovieCommentService movieCommentService, ModelMapper modelMapper) {
        this.userService = userService;
        this.movieService = movieService;
        this.movieCommentService = movieCommentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add-movie")
    public String add(Model model) {
        if (!model.containsAttribute("movieAddBindingModel")) {
            model.addAttribute("movieAddBindingModel", new MovieAddBindingModel());
        }

        return "add-movie";
    }

    @PostMapping("/add-movie")
    public String addMovie(@ModelAttribute("movieAddBindingModel") @Valid MovieAddBindingModel movieAddBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal UserDetails principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("movieAddBindingModel", movieAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.movieAddBindingModel", bindingResult);

            return "redirect:add-movie";
        }

        MovieServiceModel movieServiceModel = modelMapper
                .map(movieAddBindingModel, MovieServiceModel.class);

        movieServiceModel.setUser(principal.getUsername());

        movieService.addMovie(movieServiceModel);

        return "redirect:/movies/all-movies";
    }


    @GetMapping("/all-movies")
    public String allDramas(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserEntity loggedInUser = userService.findUser(username);

        List<MovieViewModel> movieViewModelList = movieService.displayAllMovies();

        model.addAttribute("allMovies", movieViewModelList);

        model.addAttribute("loggedInUser", loggedInUser);

        return "all-movies";
    }

    @GetMapping("/movie-details/{id}")
    public String movieDetails(@PathVariable Long id, Model model) {

        MovieViewModel movieViewModel = movieService.findById(id);

        model.addAttribute("movieDetails", movieViewModel);

        movieId = movieViewModel.getId();

        return "movie-details";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserEntity loggedInUser = userService.findUser(username);
        List<UserRoleEntity> userRoles = loggedInUser.getRoles();
        String role = userRoles.get(0).getRole().name();

        if (role.equals("ADMIN")) {
            movieService.delete(id);

            return "redirect:/movies/all-movies";
        }


        movieService.delete(id);

        return "redirect:/users/my-movies";
    }

    @GetMapping("/add-comment")
    public String addComment(Model model) {

        if (!model.containsAttribute("commentAddBindingModel")) {
            model.addAttribute("commentAddBindingModel", new CommentAddBindingModel());
        }

        return "movie-details";
    }

    @PostMapping("/add-comment")
    public String addComment(@ModelAttribute("commentAddBindingModel") @Valid CommentAddBindingModel commentAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal UserDetails principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("commentAddBindingModel");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.commentAddBindingModel", bindingResult);

            return "redirect:/movies/movie-details/" + movieId;
        }

        CommentServiceModel commentServiceModel = modelMapper
                .map(commentAddBindingModel, CommentServiceModel.class);

        commentServiceModel.setUser(principal.getUsername());

        MovieViewModel movie = movieService.findById(movieId);

        movieCommentService.addMovieComment(commentServiceModel, movie);

        return "redirect:/movies/movie-details/" + movieId;
    }
}
