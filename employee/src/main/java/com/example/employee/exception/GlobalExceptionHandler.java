package com.example.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AlreadyFoundException.class)
    public ResponseEntity<HashMap<String, String>> handle(AlreadyFoundException exception) {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);

    }

    //handle exception for validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<HashMap<String, String>> notfound(NotFoundException exception) {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);

    }


}
