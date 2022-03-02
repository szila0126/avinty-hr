package com.avinty.hr.error;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class ErrorDto implements Serializable {
  private HttpStatus httpStatus;
  private String message;
  private List<String> errors;

  public ErrorDto(HttpStatus status, String message, List<String> errors) {
    super();
    this.httpStatus = status;
    this.message = message;
    this.errors = errors;
  }

  public ErrorDto(HttpStatus status, String message, String error) {
    super();
    this.httpStatus = status;
    this.message = message;
    errors = Arrays.asList(error);
  }
}
