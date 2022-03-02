package com.avinty.hr.domain.entity.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity<T extends Serializable> implements Entity<T> {
  @Id
  @Column(name = "id", unique = true, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected T id;

  @Override
  public Entity<T> setId(T id) {
    this.id = id;
    return this;
  }
}
