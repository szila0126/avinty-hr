package com.avinty.hr.error.exception;

import com.avinty.hr.error.ErrorDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public abstract class BaseException extends RuntimeException {
  private final HttpStatus responseStatus;
  private final List<ErrorDto> errors;

  public BaseException(ErrorDto error) {
    this.errors = Collections.singletonList(error);
    this.responseStatus = HttpStatus.BAD_REQUEST;
  }
}
