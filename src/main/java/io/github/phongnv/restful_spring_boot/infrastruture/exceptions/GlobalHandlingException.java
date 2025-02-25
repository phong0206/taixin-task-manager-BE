package io.github.phongnv.restful_spring_boot.infrastruture.exceptions;

import io.github.phongnv.restful_spring_boot.infrastruture.dtos.response.ResponseData;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalHandlingException {

    // Handle error validation (Input data invalid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseData<List<String>>> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + " " + error.getDefaultMessage())
                .collect(Collectors.toList());
        return buildResponse(HttpStatus.BAD_REQUEST, errors, request);
    }

    // Handle error application
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ResponseData<String>> handleAppException(AppException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
    }

    // Handle error global
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseData<String>> handleGlobalException(Exception ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "System error: " + ex.getMessage(), request);
    }

    // Handle response data with status code and data
    private <T> ResponseEntity<ResponseData<T>> buildResponse(HttpStatus status, T data, HttpServletRequest request) {

        ResponseData<T> response = ResponseData.<T>builder()
                .timestamp(new Date())
                .status(status.value())
                .data(data)
                .method(request.getMethod())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(status).body(response);
    }
}
