package pl.mlisowski.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.mlisowski.figures.Figure;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PrismDTO {

    private Figure f;
    private double h;

}
