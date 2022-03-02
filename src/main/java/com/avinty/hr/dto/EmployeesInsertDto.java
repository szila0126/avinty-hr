package com.avinty.hr.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeesInsertDto extends EmployeesDto {
  private Long departmentId;
}
