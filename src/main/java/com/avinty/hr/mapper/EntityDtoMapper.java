package com.avinty.hr.mapper;

import com.avinty.hr.domain.entity.base.Entity;
import com.avinty.hr.dto.Dto;
import org.mapstruct.MapperConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

@MapperConfig
public abstract class EntityDtoMapper<
    T extends Serializable, E extends Entity<T>, D extends Dto<T>> {
  @Autowired protected JpaRepository<E, T> repository;

  public EntityDtoMapper() {}

  public abstract E toEntity(D dto);

  public abstract D toDto(E entity);

  public abstract List<D> toDto(List<E> entities);
}
