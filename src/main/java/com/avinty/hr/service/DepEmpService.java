package com.avinty.hr.service;

import com.avinty.hr.domain.entity.Department;
import com.avinty.hr.domain.repository.DepartmentRepository;
import com.avinty.hr.dto.DepEmpDto;
import com.avinty.hr.mapper.DepEmpMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepEmpService {
  private final DepartmentRepository departmentRepository;
  private final DepEmpMapper depEmpMapper;

  public DepEmpService(DepartmentRepository departmentRepository, DepEmpMapper depEmpMapper) {
    this.departmentRepository = departmentRepository;
    this.depEmpMapper = depEmpMapper;
  }

  public List<DepEmpDto> getAll() {
    List<Department> departmentList = departmentRepository.findAll();
    return depEmpMapper.toDto(departmentList);
  }
}
