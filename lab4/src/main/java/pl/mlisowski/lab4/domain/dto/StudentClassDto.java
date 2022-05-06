package pl.mlisowski.lab4.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentClassDto {

    @Min(1)
    private Long classId;
    @Min(1)
    private Long studentId;

}
