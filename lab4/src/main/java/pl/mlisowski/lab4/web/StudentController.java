package pl.mlisowski.lab4.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.mlisowski.lab4.application.StudentService;
import pl.mlisowski.lab4.domain.dto.StudentDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<StudentDto> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public StudentDto addStudent(@Valid @RequestBody StudentDto student) {
        return studentService.addStudent(student);
    }

    //TODO: Implement rest methods

}
