package pl.mlisowski.lab4.application;

import pl.mlisowski.lab4.domain.Student;
import pl.mlisowski.lab4.domain.dto.StudentDto;

import java.util.List;

public interface StudentService {

    StudentDto getStudent(Long studentId);

    List<StudentDto> getAllStudents();

    void deleteStudent(Long studentId);

    StudentDto addStudent(StudentDto student);

}
