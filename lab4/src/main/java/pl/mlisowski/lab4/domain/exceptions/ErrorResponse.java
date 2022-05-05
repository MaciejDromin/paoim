package pl.mlisowski.lab4.domain.exceptions;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ErrorResponse {

    private int status;
    private String errorMessage;

}
