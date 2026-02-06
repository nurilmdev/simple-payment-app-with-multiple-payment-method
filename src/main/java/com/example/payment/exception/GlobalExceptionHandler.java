package com.example.payment.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        List<String> errors = new ArrayList<>();
        errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getField() + " " + e.getDefaultMessage())
                .collect(Collectors.toList());

        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(400)
                .error("Bad Request")
                .message(errors.stream().collect(Collectors.joining(", ")))
                .path(request.getRequestURI())
                .build();

    }

    // 400 - illegal input error
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidation(
            IllegalArgumentException ex,
            HttpServletRequest request) {
        String msg = "";
        if(ex.getMessage().contains("No enum constant com.example.payment.domain.PaymentMethod"))
            msg = "Payment Method Doesn't Exists";
        log.error(ex.getMessage());
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(400)
                .error("Invalid Request")
                .message(msg)
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

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleNotFound(
            DataIntegrityViolationException ex,
            HttpServletRequest request) {
        log.error(ex.getMessage());
        String msg = "";
        if(ex.getMostSpecificCause().getMessage().contains("uk_reference_number"))
            msg = "Payment with same reference number already exists";
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error("Duplicate Data")
                .message(msg)
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
        ex.printStackTrace();
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(500)
                .error("Internal Server Error")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
    }
}
