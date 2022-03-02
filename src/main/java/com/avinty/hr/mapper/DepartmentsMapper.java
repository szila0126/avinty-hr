package com.avinty.hr.mapper;

import com.avinty.hr.domain.entity.Department;
import com.avinty.hr.dto.DepartmentDto;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
@MapperConfig
public abstract class DepartmentsMapper extends EntityDtoMapper<Long, Department, DepartmentDto> {

  @Override
  @Mapping(target = "managerName", source = "manager.fullName")
  public abstract DepartmentDto toDto(Department entity);
}
