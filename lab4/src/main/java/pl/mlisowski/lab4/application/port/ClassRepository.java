package pl.mlisowski.lab4.application.port;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mlisowski.lab4.domain.Class;

public interface ClassRepository extends JpaRepository<Class, Long> {
}
