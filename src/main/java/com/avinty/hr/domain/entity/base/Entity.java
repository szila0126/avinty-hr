package com.avinty.hr.domain.entity.base;

import java.io.Serializable;

public interface Entity<T extends Serializable> extends Serializable {
  T getId();

  Entity<T> setId(T id);
}
