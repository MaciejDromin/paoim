package pl.mlisowski.lab4.domain.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassDto {

    private Long id;

    private String uuid;

    @NotBlank
    private String groupName;


    private Set<StudentDto> students;

    @Min(1)
    @Max(10)
    private int maxStudents;

}
