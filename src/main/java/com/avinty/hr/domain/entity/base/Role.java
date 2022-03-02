package com.avinty.hr.domain.entity.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
@Getter
@Setter
public class Role {
  @Id private Long id;
  private String name;
}
