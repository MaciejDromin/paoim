package pl.mlisowski.lab4.application;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import pl.mlisowski.lab4.application.port.StudentRepository;
import pl.mlisowski.lab4.domain.Student;
import pl.mlisowski.lab4.domain.dto.StudentDto;
import pl.mlisowski.lab4.domain.factory.StudentDtoFactory;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentDtoFactory studentDtoFactory;
    private final ObjectMapper objectMapper;

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

    @Override
    public FileSystemResource generateCsv() throws IOException {
        List<Student> students = studentRepository.findAll();
        if(students.isEmpty()) return null;
        JsonNode tree = objectMapper.valueToTree(students);
        Builder csvSchemaBuilder = CsvSchema.builder();
        JsonNode fo = tree.elements().next();
        fo.fieldNames().forEachRemaining(csvSchemaBuilder::addColumn);
        CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        csvMapper.writerFor(JsonNode.class)
                .with(csvSchema)
                .writeValue(new File("src/main/resources/students.csv"), tree);
        return new FileSystemResource(new File("src/main/resources/students.csv"));
    }

}
