package pl.mlisowski.lab4.domain.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.persistence.EntityNotFoundException;
import java.util.Arrays;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler({
            EntityNotFoundException.class
    })
    public ResponseEntity<ErrorResponse> handleNotFoundException(EntityNotFoundException e) {
        return error(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler({
            ConstraintViolationException.class
    })
    public ResponseEntity<ErrorResponse> handleBadRequestException(ConstraintViolationException e){
        return error(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler({
            Exception.class
    })
    public ResponseEntity<ErrorResponse> handleAll(Exception e) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage() + e.getClass().getName());
    }

    private ResponseEntity<ErrorResponse> error(HttpStatus status, String message) {
        return ResponseEntity
                .status(status)
                .body(ErrorResponse.builder()
                        .status(status.value())
                        .errorMessage(message)
                        .build()
                );
    }

}
