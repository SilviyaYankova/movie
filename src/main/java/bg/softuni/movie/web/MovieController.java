package bg.softuni.movie.web;

import bg.softuni.movie.model.binding.MovieAddBindingModel;
import bg.softuni.movie.model.service.MovieServiceModel;
import bg.softuni.movie.model.view.DramaViewModel;
import bg.softuni.movie.model.view.MovieViewModel;
import bg.softuni.movie.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    private final MovieService movieService;
    private final ModelMapper modelMapper;

    public MovieController(MovieService movieService, ModelMapper modelMapper) {
        this.movieService = movieService;
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


        if(bindingResult.hasErrors()) {
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

        List<MovieViewModel> movieViewModelList = movieService.displayAllDramas();

        model.addAttribute("allMovies", movieViewModelList);

        return "all-movies";
    }

    @GetMapping("/movie-details/{id}")
    public String dramaDetails(@PathVariable Long id, Model model) {

        MovieViewModel movieViewModel = movieService.findById(id);

        model.addAttribute("movieDetails", movieViewModel);

        return "movie-details";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        movieService.delete(id);

        return "redirect:/movies/all-movies";
    }
}
