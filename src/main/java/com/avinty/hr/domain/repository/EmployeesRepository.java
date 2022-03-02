package com.avinty.hr.domain.repository;

import com.avinty.hr.domain.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Long> {
  Employees findByEmail(String username);
}
