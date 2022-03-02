package com.avinty.hr.error.exception;

import com.avinty.hr.error.ErrorDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class InvalidUserNameOrPasswordException extends BaseException {

  public InvalidUserNameOrPasswordException() {
    super(new ErrorDto(HttpStatus.BAD_REQUEST, "Invalid username/password!", "Please try again"));
  }
}
