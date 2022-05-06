package pl.mlisowski.lab4.domain.factory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.mlisowski.lab4.domain.Class;
import pl.mlisowski.lab4.domain.Student;
import pl.mlisowski.lab4.domain.dto.StudentDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

@Component
@Slf4j
public class StudentDtoFactory implements AbstractFactory<Student, StudentDto> {

    @Override
    public StudentDto from(Student from) {
        log.debug("UUID Studenta: {}", from.getUuid());
        return StudentDto.builder()
                .id(from.getId())
                .uuid(from.getUuid())
                .name(from.getName())
                .surrname(from.getSurrname())
                .condition(from.getCondition())
                .birthYear(from.getBirthYear())
                .points(from.getPoints())
                .build();
    }

    @Override
    public Student to(StudentDto to) {
        return Student.builder()
                .id(to.getId())
                .uuid(to.getUuid())
                .name(to.getName())
                .surrname(to.getSurrname())
                .condition(to.getCondition())
                .birthYear(to.getBirthYear())
                .points(to.getPoints())
                .build();
    }
}
