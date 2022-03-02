package com.avinty.hr.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeesDto extends Dto<Long> {
  @NotNull private String email;
  @NotNull private String password;
  @NotNull private String fullName;
  private String departmentName;
}
