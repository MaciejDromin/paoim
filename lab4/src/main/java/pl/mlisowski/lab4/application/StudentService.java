package pl.mlisowski.lab4.application;

import org.springframework.core.io.FileSystemResource;
import pl.mlisowski.lab4.domain.Student;
import pl.mlisowski.lab4.domain.dto.StudentDto;

import java.io.IOException;
import java.util.List;

public interface StudentService {

    StudentDto getStudent(Long studentId);

    List<StudentDto> getAllStudents();

    void deleteStudent(Long studentId);

    StudentDto addStudent(StudentDto student);

    Student getBareStudent(Long studentId);

    FileSystemResource generateCsv() throws IOException;

}
