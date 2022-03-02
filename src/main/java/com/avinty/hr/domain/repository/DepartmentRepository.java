package com.avinty.hr.domain.repository;

import com.avinty.hr.domain.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
  List<Department> findByNameContains(String name);

  Optional<Department> findById(Long id);
}
