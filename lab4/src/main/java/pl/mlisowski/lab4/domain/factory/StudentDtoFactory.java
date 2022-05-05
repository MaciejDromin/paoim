package pl.mlisowski.lab4.domain.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mlisowski.lab4.domain.Class;
import pl.mlisowski.lab4.domain.Student;
import pl.mlisowski.lab4.domain.dto.StudentDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StudentDtoFactory implements AbstractFactory<Student, StudentDto> {

    private final ClassDtoFactory classDtoFactory;

    @Override
    public StudentDto from(Student from) {
        if(from.getClasses() != null) {
            return StudentDto.builder()
                    .id(from.getId())
                    .uuid(from.getUuid())
                    .name(from.getName())
                    .surrname(from.getSurrname())
                    .condition(from.getCondition())
                    .birthYear(from.getBirthYear())
                    .points(from.getPoints())
                    .classes(from.getClasses()
                            .stream()
                            .map(classDtoFactory::from)
                            .collect(Collectors.toSet())
                    )
                    .build();
        }
        return StudentDto.builder()
                .id(from.getId())
                .uuid(from.getUuid())
                .name(from.getName())
                .surrname(from.getSurrname())
                .condition(from.getCondition())
                .birthYear(from.getBirthYear())
                .points(from.getPoints())
                .classes(new HashSet<>())
                .build();
    }

    @Override
    public Student to(StudentDto to) {
        if(to.getClasses() != null) {
            return Student.builder()
                    .id(to.getId())
                    .uuid(to.getUuid())
                    .name(to.getName())
                    .surrname(to.getSurrname())
                    .condition(to.getCondition())
                    .birthYear(to.getBirthYear())
                    .points(to.getPoints())
                    .classes(to.getClasses()
                            .stream()
                            .map(classDtoFactory::to)
                            .collect(Collectors.toSet())
                    )
                    .build();
        }
        return Student.builder()
                .id(to.getId())
                .uuid(to.getUuid())
                .name(to.getName())
                .surrname(to.getSurrname())
                .condition(to.getCondition())
                .birthYear(to.getBirthYear())
                .points(to.getPoints())
                .classes(new HashSet<>())
                .build();
    }
}
