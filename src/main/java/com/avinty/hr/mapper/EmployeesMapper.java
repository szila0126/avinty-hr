package com.avinty.hr.mapper;

import com.avinty.hr.domain.entity.Employees;
import com.avinty.hr.dto.EmployeesDto;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
@MapperConfig
public abstract class EmployeesMapper extends EntityDtoMapper<Long, Employees, EmployeesDto> {

  @Override
  @Mapping(target = "departmentName", source = "department.name")
  public abstract EmployeesDto toDto(Employees entity);
}
