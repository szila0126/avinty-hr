package com.avinty.hr.domain.entity;

import com.avinty.hr.domain.entity.base.AuditedBaseEntity;
import com.avinty.hr.domain.entity.base.Role;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
@Getter
@Setter
public class Employees extends AuditedBaseEntity<Long> {

  @NotNull private String email;

  @Column(length = 50, nullable = false)
  private String password;

  @Column(length = 50, nullable = false)
  private String fullName;

  @ManyToOne
  @JoinColumn(name = "dep_id")
  private Department department;

  private boolean isActive;

  @ManyToMany(
      cascade = {CascadeType.PERSIST, CascadeType.MERGE},
      fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> userGroups = new HashSet<>();
}
