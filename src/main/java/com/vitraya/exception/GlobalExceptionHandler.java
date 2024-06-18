package com.vitraya.exception;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        String errorMessage = ex.getMostSpecificCause().getMessage();
        if (errorMessage != null && errorMessage.contains("duplicate key value")) {
            // Extract email
            String email = errorMessage.split("Key \\(email\\)=\\(")[1].split("\\)")[0];
            errorResponse.put("message", email + " already exists.");
        } else {
            errorResponse.put("message", "Data integrity violation: " + errorMessage);
        }
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}