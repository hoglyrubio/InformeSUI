package com.empresaprivadaservicios.informesui;

import java.util.List;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

  private static final long serialVersionUID = 1335709938450373121L;
  
  private HttpStatus status;
  private List<String> messages;

  public BusinessException(String message) {
    this(message, HttpStatus.BAD_REQUEST);
  }
  public BusinessException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }
  
  public BusinessException(String message, Throwable cause, HttpStatus status) {
    super(message, cause);
    this.status = status;
  }
  
  public BusinessException(String message, List<String> messages, HttpStatus status) {
    super(message);
    this.messages = messages;
    this.status = status;
  }
  
  public BusinessException(String message, List<String> messages, Throwable cause, HttpStatus status) {
    super(message, cause);
    this.messages = messages;
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public List<String> getMessages() {
    return messages;
  }
  
  public boolean hasMultipleMessages() {
    return messages != null && !messages.isEmpty();
  }
}
