package pl.mlisowski.lab4.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.mlisowski.lab4.application.port.ClassRepository;
import pl.mlisowski.lab4.domain.Class;
import pl.mlisowski.lab4.domain.Student;
import pl.mlisowski.lab4.domain.dto.ClassDto;
import pl.mlisowski.lab4.domain.dto.StudentClassDto;
import pl.mlisowski.lab4.domain.factory.ClassDtoFactory;
import pl.mlisowski.lab4.domain.factory.StudentDtoFactory;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClassServiceImpl implements ClassService{

    private final ClassRepository classRepository;
    private final StudentService studentService;
    private final ClassDtoFactory classDtoFactory;
    private final StudentDtoFactory studentDtoFactory;

    @Override
    public ClassDto getClass(Long classId) {
        return classDtoFactory.from(
                classRepository.findById(classId)
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("Klasa o id %d nie znaleziona", classId))));
    }

    @Override
    public List<ClassDto> getAllClasses() {
        return classRepository.findAll()
                .stream()
                .map(classDtoFactory::from)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteClass(Long classId) {
        Class c = classRepository.findById(classId)
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("Klasa o id %d nie znaleziona", classId)));
        for(Student s : c.getStudents()) s.remove(c);
        classRepository.deleteById(classId);
    }

    @Override
    public ClassDto addClass(ClassDto klasa) {
        Class k = new Class();
        k.setGroupName(klasa.getGroupName());
        k.setMaxStudents(klasa.getMaxStudents());
        for(Student s : k.getStudents()) s.addClass(k);
        return classDtoFactory.from(classRepository.save(k));
    }

    @Override
    public ClassDto addStudent(StudentClassDto studentClassDto) {
        Student s = studentService.getBareStudent(studentClassDto.getStudentId());
        Class c = classRepository.findById(studentClassDto.getClassId())
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("Klasa o id %d nie znaleziona", studentClassDto.getClassId())));
        c.addStudent(s);
        return classDtoFactory.from(classRepository.save(c));
    }

}
