package com.avinty.hr.service;

import com.avinty.hr.domain.entity.Employees;
import com.avinty.hr.domain.repository.EmployeesRepository;
import com.avinty.hr.dto.EmployeesDto;
import com.avinty.hr.dto.EmployeesInsertDto;
import com.avinty.hr.error.exception.DepartmentNotFoundException;
import com.avinty.hr.mapper.EmployeesMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class EmployeesServiceTest {

  @Mock private EmployeesRepository employeesRepository;
  @Mock private EmployeesMapper employeesMapper;
  @Autowired EmployeesService employeesService;

  @Test
  public void findAllEmployes() {
    Employees test1 = createEmployees(1L, "Test1", "tt@tt.com");
    Employees test2 = createEmployees(2L, "Test2", "tt2@tt.com");
    List<Employees> empList = Arrays.asList(test1, test2);

    Mockito.when(employeesRepository.findAll()).thenReturn(empList);
    List<EmployeesDto> fetchedList = employeesService.getAll();

    Assertions.assertThat(fetchedList.size()).isGreaterThan(0);
  }

  @Test
  public void when_save_without_department() {
    EmployeesInsertDto emp = createEmployeesInsertDto(1L, "Empl", "tt@tt.com");
    Mockito.when(employeesRepository.findById(22L))
        .thenThrow(new DepartmentNotFoundException("22"));
    org.junit.jupiter.api.Assertions.assertThrows(
        DepartmentNotFoundException.class,
        () -> {
          EmployeesDto employeesDto = employeesService.create(emp);
          Assertions.assertThat(employeesDto).isNotNull();
        });
  }

  @Test
  public void authorized_with_wrong_email() {
    EmployeesInsertDto emp = createEmployeesInsertDto(1L, "Empl", "tt@tt.com");
    Mockito.when(employeesRepository.findByEmail(emp.getEmail()))
        .thenThrow(NullPointerException.class);
    org.junit.jupiter.api.Assertions.assertThrows(
        Exception.class,
        () -> {
          User user = employeesService.loadUserByUsername(emp.getEmail());
          Assertions.assertThat(user).isNotNull();
        });
  }

  @Test
  public void authorized_with_good_email() {
    Employees emp = createEmployees(1L, "Empl", "tt1@tt.com");
    Mockito.when(employeesRepository.findByEmail(emp.getEmail())).thenReturn(emp);
    User user = employeesService.loadUserByUsername(emp.getEmail());
    Assertions.assertThat(user).isNotNull();
  }

  private EmployeesDto createEmployeesDto(long l, String name, String email) {
    EmployeesDto emp = new EmployeesDto();
    emp.setId(l);
    emp.setFullName(name);
    emp.setPassword("manager");
    emp.setEmail(email);
    return emp;
  }

  private EmployeesInsertDto createEmployeesInsertDto(long l, String name, String email) {
    EmployeesInsertDto emp = new EmployeesInsertDto();
    emp.setId(l);
    emp.setFullName(name);
    emp.setPassword("manager");
    emp.setEmail(email);
    emp.setDepartmentId(22L);
    return emp;
  }

  private Employees createEmployees(Long id, String name, String email) {
    Employees emp = new Employees();
    emp.setId(id);
    emp.setFullName(name);
    emp.setPassword("manager");
    emp.setEmail(email);
    return emp;
  }
}
