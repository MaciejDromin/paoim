package pl.mlisowski.lab4.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.mlisowski.lab4.application.port.ClassRepository;
import pl.mlisowski.lab4.domain.dto.ClassDto;
import pl.mlisowski.lab4.domain.factory.ClassDtoFactory;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClassServiceImpl implements ClassService{

    private final ClassRepository classRepository;
    private final ClassDtoFactory classDtoFactory;

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
        classRepository.deleteById(classId);
    }

    @Override
    public ClassDto addClass(ClassDto klasa) {
        return classDtoFactory.from(classRepository.save(classDtoFactory.to(klasa)));
    }

}
