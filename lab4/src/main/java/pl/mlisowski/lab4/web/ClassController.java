package pl.mlisowski.lab4.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.mlisowski.lab4.application.ClassService;
import pl.mlisowski.lab4.domain.dto.ClassDto;
import pl.mlisowski.lab4.domain.dto.StudentClassDto;

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
    public ClassDto addClass(@Valid @RequestBody ClassDto klasa) {
        return classService.addClass(klasa);
    }

    @PostMapping("/student")
    public ClassDto addStudent(@Valid @RequestBody StudentClassDto studentClassDto) {
        return classService.addStudent(studentClassDto);
    }

    @DeleteMapping("/{id}")
    public void deleteClass(@PathVariable Long id) {
        classService.deleteClass(id);
    }

    // TODO: Implement rest methods

}
