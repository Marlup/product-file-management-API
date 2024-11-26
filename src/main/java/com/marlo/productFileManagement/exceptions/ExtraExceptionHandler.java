package com.marlo.productFileManagement.exceptions;

import com.marlo.productFileManagement.exceptions.restController.EmptyFileException;
import com.marlo.productFileManagement.exceptions.restController.InvalidFileExtensionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExtraExceptionHandler extends Throwable {

    @ExceptionHandler(EmptyFileException.class)
    //public ResponseEntity<String> handleEmptyFileException(FileEmptyException ex) {
    public ResponseEntity<Map<String, Object>> handleFileEmptyException(EmptyFileException ex) {
        Map<String, Object> errorDetails = fillErrorDetails(
                ex.getMessage(),
                "Empty file."
        );

        // Returns only the exception message
        //return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        // Returns the exception details
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFileExtensionException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidFileExtensionException(InvalidFileExtensionException ex) {
        Map<String, Object> errorDetails = fillErrorDetails(
                ex.getMessage(),
                "Invalid file extension"
        );

        // Returns the exception details
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    private Map<String, Object> fillErrorDetails(String message, String error) {
        Map<String, Object> errorDetails = new HashMap<>();
                errorDetails.put("timestamp", LocalDateTime.now());
                errorDetails.put("status", HttpStatus.BAD_REQUEST.value());
                errorDetails.put("error", error);
                errorDetails.put("message", message);
                errorDetails.put("path", "/uploadFile"); // Optionally, add the request path

        return errorDetails;
    }
}
