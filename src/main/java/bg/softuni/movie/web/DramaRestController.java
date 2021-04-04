package bg.softuni.movie.web;
import bg.softuni.movie.model.view.DramaViewModel;
import bg.softuni.movie.repository.DramaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/dramas")
@RestController
public class DramaRestController {

    private final DramaRepository dramaRepository;
    private final ModelMapper modelMapper;

    public DramaRestController(DramaRepository dramaRepository, ModelMapper modelMapper) {
        this.dramaRepository = dramaRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/drama-api")
    private ResponseEntity<List<DramaViewModel>> allDramas() {

        List<DramaViewModel> dramaViewModel = dramaRepository
                .findAll()
                .stream()
                .map(movie -> modelMapper
                        .map(movie, DramaViewModel.class)).collect(Collectors.toList());


        return ResponseEntity
                .ok()
                .body(dramaViewModel);
    }
}
