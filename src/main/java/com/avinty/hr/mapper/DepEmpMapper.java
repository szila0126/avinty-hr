package com.avinty.hr.mapper;

import com.avinty.hr.domain.entity.Department;
import com.avinty.hr.dto.DepEmpDto;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = EmployeesMapper.class)
@MapperConfig
public abstract class DepEmpMapper extends EntityDtoMapper<Long, Department, DepEmpDto> {

  @Override
  @Mapping(target = "managerName", source = "manager.fullName")
  public abstract DepEmpDto toDto(Department entity);
}
