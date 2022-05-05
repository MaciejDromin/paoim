package pl.mlisowski.lab4.domain.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.mlisowski.lab4.domain.Class;
import pl.mlisowski.lab4.domain.dto.ClassDto;

import java.util.HashSet;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClassDtoFactory implements AbstractFactory<Class, ClassDto> {

    @Setter
    private StudentDtoFactory studentDtoFactory;
    private final ObjectMapper objectMapper;

    @Override
    public ClassDto from(Class from) {
        try {
            log.debug("{}", objectMapper.writeValueAsString(from));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if(from.getStudents() != null && !from.getStudents().isEmpty()) {
            return ClassDto.builder()
                    .id(from.getId())
                    .uuid(from.getUuid())
                    .groupName(from.getGroupName())
                    .students(from.getStudents()
                            .stream()
                            .map(studentDtoFactory::from)
                            .collect(Collectors.toSet())
                    )
                    .maxStudents(from.getMaxStudents())
                    .build();
        }
        return ClassDto.builder()
                .id(from.getId())
                .uuid(from.getUuid())
                .groupName(from.getGroupName())
                .maxStudents(from.getMaxStudents())
                .students(new HashSet<>())
                .build();
    }

    @Override
    public Class to(ClassDto to) {
        try {
            log.debug("{}", objectMapper.writeValueAsString(to));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if(to.getStudents() != null && !to.getStudents().isEmpty()) {
            return Class.builder()
                    .id(to.getId())
                    .uuid(to.getUuid())
                    .groupName(to.getGroupName())
                    .students(to.getStudents()
                            .stream()
                            .map(studentDtoFactory::to)
                            .collect(Collectors.toSet())
                    )
                    .maxStudents(to.getMaxStudents())
                    .build();
        }
        return Class.builder()
                .id(to.getId())
                .uuid(to.getUuid())
                .groupName(to.getGroupName())
                .maxStudents(to.getMaxStudents())
                .students(new HashSet<>())
                .build();
    }
}
