package pl.mlisowski.lab4.domain.dto;

import lombok.*;
import pl.mlisowski.lab4.domain.enums.StudentCondition;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    private Long id;

    private String uuid;

    private String name;
    private String surrname;
    private StudentCondition condition;
    private int birthYear;
    private double points;

    @NotNull
    private Set<ClassDto> classes;

}
