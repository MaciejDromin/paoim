package pl.mlisowski.lab4.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.mlisowski.lab4.application.ClassService;
import pl.mlisowski.lab4.domain.dto.ClassDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/class")
@RequiredArgsConstructor
public class ClassController {

    private final ClassService classService;

    @GetMapping
    public List<ClassDto> getAllClasses() {
        return classService.getAllClasses();
    }

    @PostMapping
    public ClassDto addClass(@RequestBody ClassDto klasa) {
        return classService.addClass(klasa);
    }

    // TODO: Implement rest methods

}
