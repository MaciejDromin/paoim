package pl.mlisowski.lab4.domain.dto;

import lombok.*;
import pl.mlisowski.lab4.domain.enums.StudentCondition;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    private Long id;

    private String uuid;

    @NotBlank
    private String name;
    @NotBlank
    private String surrname;
    private StudentCondition condition;
    @Min(1)
    private int birthYear;
    @DecimalMin("2.0") @DecimalMax("5.0")
    private double points;

}
