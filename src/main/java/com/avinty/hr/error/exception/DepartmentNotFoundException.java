package com.avinty.hr.error.exception;

import com.avinty.hr.error.ErrorDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class DepartmentNotFoundException extends BaseException {

  public DepartmentNotFoundException(String depId) {
    super(
        new ErrorDto(
            HttpStatus.BAD_REQUEST,
            "Department not found!",
            "ID: " + depId + " department id not found in the database"));
  }
}
