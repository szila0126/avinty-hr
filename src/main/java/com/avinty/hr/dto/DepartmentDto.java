package com.avinty.hr.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentDto extends Dto<Long> {
  private String name;
  private String managerName;
}
