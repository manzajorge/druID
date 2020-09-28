package com.personal.codechallenge.configuration;


import com.personal.codechallenge.utils.CustomError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
@Slf4j
public class ExceptionConfiguration extends ResponseEntityExceptionHandler {
  
  @ExceptionHandler(value = {ConstraintViolationException.class})
  protected ResponseEntity<Object> manageConstraintViolationException(ConstraintViolationException ex) {
    log.error("Constraint violation: " + ex.getMessage());
    CustomError error = CustomError.builder()
      .message(ex.getMessage())
      .httpCode(HttpStatus.BAD_REQUEST.value())
      .build();
    return new ResponseEntity<>(error, HttpStatus.valueOf(error.getHttpCode()));
  }
  
  @ExceptionHandler(value = {Exception.class})
  protected ResponseEntity<Object> manageUnhandledException(RuntimeException ex) {
    log.error("Unexpected error: " + ex.getMessage());
    CustomError error = CustomError.builder()
      .httpCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
      .message(ex.getMessage())
      .build();
    return new ResponseEntity<>(error, HttpStatus.valueOf(error.getHttpCode()));
  }
  
  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    log.error("Malformed JSON request");
    return new ResponseEntity<>(null, status);
  }
  
  // error handle for @Valid
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
    MethodArgumentNotValidException ex,
    HttpHeaders headers,
    HttpStatus status,
    WebRequest request) {
    log.error("Argument not valid exception");
    CustomError error = CustomError.builder()
      .httpCode(status.value())
      .message(ex.getLocalizedMessage())
      .build();
    return new ResponseEntity<>(error, HttpStatus.valueOf(error.getHttpCode()));
  }
  
}
