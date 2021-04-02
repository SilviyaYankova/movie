package bg.softuni.movie.web;
import bg.softuni.movie.model.entity.DramaEntity;
import bg.softuni.movie.repository.DramaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/dramas")
@RestController
public class DramaRestController {

    private final DramaRepository dramaRepository;

    public DramaRestController(DramaRepository dramaRepository) {
        this.dramaRepository = dramaRepository;
    }

    @GetMapping("/drama-api")
    private ResponseEntity<List<DramaEntity>> allDramas() {
        return ResponseEntity
                .ok()
                .body(dramaRepository.findAll());
    }
}
