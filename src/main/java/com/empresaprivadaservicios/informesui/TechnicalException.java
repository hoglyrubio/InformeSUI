package com.empresaprivadaservicios.informesui;

public class TechnicalException extends RuntimeException {

  private static final long serialVersionUID = 5515588387818710053L;
  
  public TechnicalException(String message) {
    super(message);
  }

  public TechnicalException(String message, Throwable cause) {
    super(message, cause);
  }

}
