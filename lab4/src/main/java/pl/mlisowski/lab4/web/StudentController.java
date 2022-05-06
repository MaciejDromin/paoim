package pl.mlisowski.lab4.web;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;
import pl.mlisowski.lab4.application.StudentService;
import pl.mlisowski.lab4.domain.dto.StudentDto;

import javax.validation.Valid;
import java.io.IOException;
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

    @GetMapping("/csv")
    public FileSystemResource generateCsv() throws IOException {
        return studentService.generateCsv();
    }
    //TODO: Implement rest methods

}
