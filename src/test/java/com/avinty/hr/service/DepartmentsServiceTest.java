package com.avinty.hr.service;

import com.avinty.hr.domain.entity.Department;
import com.avinty.hr.domain.entity.Employees;
import com.avinty.hr.domain.repository.DepartmentRepository;
import com.avinty.hr.dto.DepartmentDto;
import com.avinty.hr.error.exception.DepartmentNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class DepartmentsServiceTest {

  private Department department;
  private Employees manager;
  private List<Department> departmentList = new ArrayList<>();
  @Mock DepartmentRepository departmentRepository;
  @Autowired DepartmentsService departmentsService;

  @BeforeEach
  public void setUp() {

    department = createDepartmentEntity(1L, "Company1");
    manager = new Employees();
    manager.setId(1L);
    manager.setFullName("Manager Name");
    manager.setPassword("manager");
    manager.setEmail("manager@teszt.com");

    department.setManager(manager);
  }

  @Test
  public void getfindByNameContains() {
    String departmentName = "Company";
    Department department2 = createDepartmentEntity(2L, "Company2");
    departmentList.add(department);
    departmentList.add(department2);

    Mockito.when(departmentRepository.findByNameContains(departmentName))
        .thenReturn(departmentList);
    List<DepartmentDto> byNameContains1 = departmentsService.getAllDepartments(departmentName);
    Assertions.assertNotNull(byNameContains1);
    Assertions.assertEquals(2, byNameContains1.size());
  }

  @Test
  public void departmentDeleteIfFound() {
    Department dep1 = createDepartmentEntity(1L, "Company");

    Mockito.when(departmentRepository.findById(dep1.getId())).thenReturn(Optional.of(dep1));

    departmentsService.departmentDelete(dep1.getId());
    Mockito.verify(departmentRepository, Mockito.times(1)).deleteById(dep1.getId());
  }

  @Test
  public void should_throw_exception_when_department_doesnt_exist()
      throws DepartmentNotFoundException {
    Department dep1 = createDepartmentEntity(1L, "Company");
    Mockito.when(departmentRepository.findById(22L))
        .thenThrow(new DepartmentNotFoundException(dep1.getId().toString()));
    departmentsService.departmentDelete(dep1.getId());
  }

  private Department createDepartmentEntity(Long id, String name) {
    Department department2 = new Department();
    department2.setId(id);
    department2.setName(name);
    department2.setManager(manager);
    return department2;
  }
}
