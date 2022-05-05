package pl.mlisowski.lab4.application;

import pl.mlisowski.lab4.domain.dto.ClassDto;

import java.util.List;

public interface ClassService {

    ClassDto getClass(Long classId);

    List<ClassDto> getAllClasses();

    void deleteClass(Long classId);

    ClassDto addClass(ClassDto klasa);

}
