package pl.mlisowski.lab4.application.port;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mlisowski.lab4.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
