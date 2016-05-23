package com.empresaprivadaservicios.informesui;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorControllerAdvice {

  private static final Logger LOG = LoggerFactory.getLogger(ErrorControllerAdvice.class);
  
  @ExceptionHandler(Throwable.class)
  public ResponseEntity<Object> handle(HttpServletRequest request, Throwable throwable) {
    LOG.error("Exception handled", throwable);
    return buildResponseEntity(request, throwable, HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
  @ExceptionHandler({IllegalArgumentException.class, TypeMismatchException.class, NullPointerException.class})
  public ResponseEntity<Object> handle(HttpServletRequest request, Exception ex) {
    LOG.error("Exception handled", ex);
    return buildResponseEntity(request, ex, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(TechnicalException.class)
  public ResponseEntity<Object> handle(HttpServletRequest request, TechnicalException ex) {
    LOG.error("Exception handled", ex);
    return buildResponseEntity(request, ex, HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<Object> handle(HttpServletRequest request, BusinessException ex) {
    LOG.error("Exception handled", ex);
    Map<String, Object> response = buildResponse(request, ex, ex.getStatus());
    if (ex.hasMultipleMessages()) {
      response.put("messages", ex.getMessages());
    }
     return buildResponseEntity(request, ex, ex.getStatus());
  }

  private ResponseEntity<Object> buildResponseEntity(HttpServletRequest request, Throwable throwable, HttpStatus status) {
    Map<String, Object> response = buildResponse(request, throwable, status);
    return new ResponseEntity<Object>(response, status);
  }

  private Map<String, Object> buildResponse(HttpServletRequest request, Throwable throwable, HttpStatus status) {

    Map<String, Object> response = new HashMap<>();
    response.put("status", status.value());
    response.put("error", status.getReasonPhrase());
    response.put("message", throwable.getMessage());
    response.put("path", request.getRequestURI());
    if (throwable.getCause() != null) {
      response.put("dev_cause", throwable.getCause().getClass());
      response.put("dev_message", throwable.getCause().getMessage());
    }

    return response;
  }

}
