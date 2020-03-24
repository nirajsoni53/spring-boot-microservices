package com.rest.webservices.restwebservices.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

 /*   @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Object> handleExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                new ResponseExceptionDTO(ex.getMessage(), new Date(), "This is Exception"),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }*/

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ResponseExceptionDTO exceptionDTO =
                new ResponseExceptionDTO("Validation Fail", new Date(), ex.getBindingResult().getAllErrors().toString());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }
}
