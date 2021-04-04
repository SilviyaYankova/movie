package bg.softuni.movie.web;

import bg.softuni.movie.model.binding.CommentAddBindingModel;
import bg.softuni.movie.model.binding.DramaAddBindingModel;
import bg.softuni.movie.model.service.CommentServiceModel;
import bg.softuni.movie.model.service.DramaServiceModel;
import bg.softuni.movie.model.view.DramaViewModel;
import bg.softuni.movie.service.CommentService;
import bg.softuni.movie.service.DramaService;
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
@RequestMapping("/dramas")
public class DramaController {

    private Long dramaId;
    private final DramaService dramaService;
    private final CommentService commentService;
    private final ModelMapper modelMapper;

    public DramaController(DramaService dramaService, CommentService commentService, ModelMapper modelMapper) {
        this.dramaService = dramaService;
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/add-drama")
    public String addDrama(Model model) {

        if (!model.containsAttribute("dramaAddBindingModel")) {
            model.addAttribute("dramaAddBindingModel", new DramaAddBindingModel());

        }

        return "add-drama";
    }


    @PostMapping("/add-drama")
    public String addDrama(@ModelAttribute("dramaAddBindingModel") @Valid DramaAddBindingModel dramaAddBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal UserDetails principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("dramaAddBindingModel", dramaAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.dramaAddBindingModel", bindingResult);

            return "redirect:add-drama";
        }


        DramaServiceModel dramaServiceModel = modelMapper
                .map(dramaAddBindingModel, DramaServiceModel.class);

        dramaServiceModel.setUser(principal.getUsername());

        dramaService.addDrama(dramaServiceModel);

        return "redirect:/dramas/all-dramas";
    }

    @GetMapping("/all-dramas")
    public String allDramas(Model model) {

        List<DramaViewModel> dramaViewModelList = dramaService.displayAllDramas();

        model.addAttribute("allDramas", dramaViewModelList);

        return "all-dramas";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        dramaService.delete(id);

        return "redirect:/dramas/all-dramas";
    }

    @GetMapping("/drama-details/{id}")
    public String dramaDetails(@PathVariable Long id, Model model) {

        DramaViewModel dramaViewModel = dramaService.findById(id);

        model.addAttribute("dramaDetails", dramaViewModel);

        dramaId =  dramaViewModel.getId();

        return "drama-details";
    }


    @GetMapping("/add-comment")
    public String addComment(Model model) {

        if (!model.containsAttribute("commentAddBindingModel")) {
            model.addAttribute("commentAddBindingModel", new CommentAddBindingModel());
        }

        return "drama-details";
    }

    @PostMapping("/add-comment")
    public String addComment(@ModelAttribute("commentAddBindingModel") @Valid CommentAddBindingModel commentAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal UserDetails principal) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("commentAddBindingModel");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.commentAddBindingModel", bindingResult);

            return "drama-details";
        }

        CommentServiceModel commentServiceModel = modelMapper
                .map(commentAddBindingModel, CommentServiceModel.class);

        commentServiceModel.setUser(principal.getUsername());

        DramaViewModel drama = dramaService.findById(dramaId);

        commentService.addComment(commentServiceModel, drama);

        return "redirect:/dramas/all-dramas";
    }
}
