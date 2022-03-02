package com.avinty.hr.service;

import com.avinty.hr.domain.entity.Employees;
import com.avinty.hr.domain.entity.base.HrUser;
import com.avinty.hr.domain.entity.base.Role;
import com.avinty.hr.domain.repository.DepartmentRepository;
import com.avinty.hr.domain.repository.EmployeesRepository;
import com.avinty.hr.dto.EmployeesDto;
import com.avinty.hr.dto.EmployeesInsertDto;
import com.avinty.hr.error.exception.DepartmentNotFoundException;
import com.avinty.hr.mapper.EmployeesMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class EmployeesService implements UserDetailsService {
  private final EmployeesMapper employeesMapper;
  private final EmployeesRepository employeesRepository;
  private final DepartmentRepository departmentRepository;

  public EmployeesService(
      EmployeesMapper employeesMapper,
      EmployeesRepository employeesRepository,
      DepartmentRepository departmentRepository) {
    this.employeesMapper = employeesMapper;
    this.employeesRepository = employeesRepository;
    this.departmentRepository = departmentRepository;
  }

  @Transactional
  public List<EmployeesDto> getAll() {
    List<Employees> all = employeesRepository.findAll();
    return employeesMapper.toDto(all);
  }

  @Transactional
  public EmployeesDto create(EmployeesInsertDto dto) {
    Employees employees = employeesMapper.toEntity(dto);
    findDepartment(dto, employees);
    Employees save = employeesRepository.save(employees);
    return employeesMapper.toDto(save);
  }

  private void findDepartment(EmployeesInsertDto dto, Employees employees) {
    if (Objects.nonNull(dto.getDepartmentId())) {
      departmentRepository
          .findById(dto.getDepartmentId())
          .ifPresentOrElse(
              departments -> employees.setDepartment(departments),
              () -> {
                throw new DepartmentNotFoundException(String.valueOf(dto.getDepartmentId()));
              });
    }
  }

  @Override
  @Transactional
  public User loadUserByUsername(String username) throws UsernameNotFoundException {
    Employees user = employeesRepository.findByEmail(username);
    return new HrUser(user.getId(), user.getEmail(), user.getPassword(), getAuthenticate(user));
  }

  private Collection<? extends GrantedAuthority> getAuthenticate(Employees user) {
    Set<Role> userGroups = user.getUserGroups();
    Collection<GrantedAuthority> authority = new ArrayList<>(userGroups.size());
    userGroups.forEach(
        group -> authority.add(new SimpleGrantedAuthority(group.getName().toUpperCase())));
    return authority;
  }

  public void activate(EmployeesDto authRequest) {
    Employees employees = employeesRepository.findByEmail(authRequest.getEmail());
    employees.setActive(true);

    employeesRepository.save(employees);
  }
}
