package br.com.ipvoll.infra.exceptions;

import br.com.ipvoll.domain.ValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<String> handle404Error() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorDataValidation>> handle400Error(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(errors.stream().map(ErrorDataValidation::new).toList());
    }

    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<String> businessRuleError(ValidationException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    private record ErrorDataValidation(String field, String message){

        public ErrorDataValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
