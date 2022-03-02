package com.avinty.hr.domain.entity;

import com.avinty.hr.domain.entity.base.AuditedBaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "departments")
@Getter
@Setter
@Data
public class Department extends AuditedBaseEntity<Long> {

  @Column(name = "name", length = 100, nullable = false)
  private String name;

  @OneToOne
  @JoinColumn(name = "manager_id", nullable = false)
  private Employees manager;

  @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
  private Set<Employees> employeesList = new HashSet<>();

  @PreRemove
  public void onPreRemove() {
    getEmployeesList().forEach(employees -> employees.setDepartment(null));
  }
}
