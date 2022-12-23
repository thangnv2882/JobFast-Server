package com.thangnv2882.jobfastserver.config.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class VsException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private HttpStatus status;
  private String message;


  public VsException(String message) {
    super(message);
    this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    this.message = message;
  }

}