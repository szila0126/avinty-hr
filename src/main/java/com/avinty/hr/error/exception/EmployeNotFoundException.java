package com.avinty.hr.error.exception;

import com.avinty.hr.error.ErrorDto;
import org.springframework.http.HttpStatus;

public class EmployeNotFoundException extends BaseException {

  public EmployeNotFoundException() {
    super(
        new ErrorDto(
            HttpStatus.NOT_FOUND, "Employe not found!", "Employe not found in the database"));
  }
}
