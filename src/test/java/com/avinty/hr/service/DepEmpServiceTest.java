package com.avinty.hr.service;

import com.avinty.hr.domain.entity.Department;
import com.avinty.hr.domain.entity.Employees;
import com.avinty.hr.domain.repository.DepartmentRepository;
import com.avinty.hr.dto.DepEmpDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class DepEmpServiceTest {

  @Mock private DepartmentRepository departmentRepository;
  @Autowired private DepEmpService depEmpService;

  private Employees manager;

  @BeforeEach
  void initUseCase() {}

  @Test
  public void findAll() {
    Department dep = createDepartmentEntity(1L, "Company1");
    Department dep2 = createDepartmentEntity(2L, "Company2");
    List<Department> departmentList = Arrays.asList(dep, dep2);

    Mockito.when(departmentRepository.findAll()).thenReturn(departmentList);
    List<DepEmpDto> fetchedList = depEmpService.getAll();
    Assertions.assertThat(fetchedList.size()).isGreaterThan(0);
  }

  @BeforeEach
  public void setUp() {

    manager = new Employees();
    manager.setId(1L);
    manager.setFullName("Manager Name");
    manager.setPassword("manager");
    manager.setEmail("manager@teszt.com");
  }

  private Department createDepartmentEntity(Long id, String name) {
    Department department2 = new Department();
    department2.setId(id);
    department2.setName(name);
    department2.setManager(manager);
    return department2;
  }
}
