package com.mb.exception;

import jakarta.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mb.dto.ApiResponse;

@RestControllerAdvice
public class ExceptionHandler {

  @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleException(Exception e) {
    System.out.println("in catch all ");

    return ResponseEntity
      .status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(new ApiResponse(false, e.getMessage(), null));
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(NullPointerException.class)
  public ResponseEntity<ApiResponse<Object>> handleNullPointer(NullPointerException ex) {
    return ResponseEntity
      .status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(new ApiResponse(false, "Unexpected null value encountered"));
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(
    ConstraintViolationException.class
  )
  public ResponseEntity<ApiResponse<Object>> handleConstraintViolation(
    ConstraintViolationException ex
  ) {
    return ResponseEntity
      .badRequest()
      .body(new ApiResponse(false, "Validation failed for request parameters"));
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFound.class)
  public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFound e) {
    System.out.println("in catch - ResourceNotFoundException");
    return ResponseEntity
      .status(HttpStatus.NOT_FOUND)
      .body(new ApiResponse(false, e.getMessage(), null));
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(
    MethodArgumentNotValidException.class
  )
  public ResponseEntity<?> handleMethodArgumentNotValidException(
    MethodArgumentNotValidException e
  ) {
    System.out.println("in catch - MethodArgumentNotValidException");

    List<FieldError> list = e.getFieldErrors();

    Map<String, String> map = list
      .stream()
      .collect(
        Collectors.toMap(
          FieldError::getField,
          FieldError::getDefaultMessage,
          (v1, v2) -> v1 + " " + v2
        )
      );
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
  }
}
