package dev.ankush.productservicemanage.exceptions;


//@ControllerAdvice is often used for handling exceptions that might occur in your controllers.
// Instead of scattering exception handling code throughout your controllers,
// you can gather it in one place.
//@ControllerAdvice is an annotation that is used to define global controller advice for your application.
//@ControllerAdvice. It's like having a superhero that looks out for issues across your whole application.

import dev.ankush.productservicemanage.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDto> handelNotFoundException(NotFoundException notFoundException){
        return new ResponseEntity<>(new ExceptionDto(HttpStatus.NOT_FOUND,
                notFoundException.getMessage()),
                HttpStatus.NOT_FOUND);
    }
}