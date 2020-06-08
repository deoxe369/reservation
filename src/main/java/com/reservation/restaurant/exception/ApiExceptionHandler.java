package com.reservation.restaurant.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.reservation.restaurant.domain.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({ InvalidInputException.class,})
    protected ResponseEntity<ErrorResponse> handleInvalidInputException(InvalidInputException ex, HttpServletResponse response) throws IOException {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST,ex.getMessage(),Instant.now()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class,})
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletResponse response) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String message = "Field " + fieldErrors.get(0).getField() + " " + fieldErrors.get(0).getDefaultMessage();
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST,message,Instant.now()),HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler({ HttpMessageNotReadableException.class,})
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(HttpMessageNotReadableException ex, HttpServletResponse response) {
        InvalidFormatException cause = (InvalidFormatException)ex.getCause();
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST,cause.getOriginalMessage(),Instant.now()),HttpStatus.BAD_REQUEST);
    }
}
