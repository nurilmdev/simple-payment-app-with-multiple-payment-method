package com.example.payment.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 400 - validation error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        log.error(ex.getMessage());
        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getField() + " " + e.getDefaultMessage())
                .findFirst()
                .orElse("Validation error");

        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(400)
                .error("Bad Request")
                .message(message)
                .path(request.getRequestURI())
                .build();

    }

    // 404
    @ExceptionHandler(PaymentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(
            PaymentNotFoundException ex,
            HttpServletRequest request) {
        log.error(ex.getMessage());
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(404)
                .error("Not Found")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    // 500 fallback
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGeneral(
            Exception ex,
            HttpServletRequest request) {
        log.error(ex.getMessage());
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(500)
                .error("Internal Server Error")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
    }
}
