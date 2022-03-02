package com.avinty.hr.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DepEmpDto extends DepartmentDto {
  private List<EmployeesDto> employeesList;
}
