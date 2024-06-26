package ru.practicum.confidence.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Object> handle(final NotFoundException e) {
        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Object> handle(final ValidateException e) {
        Map<String, String> resp = new HashMap<>();
        resp.put("error", e.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Map<String, String>> exc(MethodArgumentTypeMismatchException ex) {
        Map<String, String> resp = new HashMap<>();
        resp.put("Error message", String.format("Unknown %s: %s", ex.getName(), ex.getValue()));
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }
}

