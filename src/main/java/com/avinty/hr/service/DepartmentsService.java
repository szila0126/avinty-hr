package com.avinty.hr.service;

import com.avinty.hr.domain.entity.Department;
import com.avinty.hr.domain.repository.DepartmentRepository;
import com.avinty.hr.dto.DepartmentDto;
import com.avinty.hr.error.exception.DepartmentNotFoundException;
import com.avinty.hr.mapper.DepartmentsMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentsService {
  private final DepartmentRepository departmentRepository;
  private final DepartmentsMapper departmentsMapper;

  public DepartmentsService(
      DepartmentRepository departmentRepository, DepartmentsMapper departmentsMapper) {
    this.departmentRepository = departmentRepository;
    this.departmentsMapper = departmentsMapper;
  }

  @Transactional
  public List<DepartmentDto> getAllDepartments(String name) {
    List<Department> allByNameIsLike = departmentRepository.findByNameContains(name);
    return departmentsMapper.toDto(allByNameIsLike);
  }

  @Transactional
  public void departmentDelete(Long departmentId) {
    try {
      departmentRepository.deleteById(departmentId);
    } catch (Exception e) {
      throw new DepartmentNotFoundException(departmentId.toString());
    }
  }
}
