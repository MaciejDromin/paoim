package pl.mlisowski.lab4.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mlisowski.lab4.application.port.StudentRepository;
import pl.mlisowski.lab4.domain.Student;
import pl.mlisowski.lab4.domain.dto.StudentDto;
import pl.mlisowski.lab4.domain.factory.StudentDtoFactory;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentDtoFactory studentDtoFactory;

    @Override
    public StudentDto getStudent(Long studentId) {
        return studentDtoFactory.from(
                studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("Student o id %d nie znaleziony", studentId))));
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentDtoFactory::from)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public StudentDto addStudent(StudentDto student) {
        Student s = new Student();
        s.setBirthYear(student.getBirthYear());
        s.setName(student.getName());
        s.setSurrname(student.getSurrname());
        s.setCondition(student.getCondition());
        s.setPoints(student.getPoints());
        return studentDtoFactory.from(studentRepository.save(s));
    }

    @Override
    public Student getBareStudent(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> new EntityNotFoundException(String
                .format("Student o id %d nie znaleziony", studentId)));
    }

}
