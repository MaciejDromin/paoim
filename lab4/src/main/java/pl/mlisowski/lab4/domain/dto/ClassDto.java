package pl.mlisowski.lab4.domain.dto;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassDto {

    private Long id;

    private String uuid;

    private String groupName;

    @NotNull
    private Set<StudentDto> students;

    private int maxStudents;

}
