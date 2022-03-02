package com.avinty.hr.domain.entity.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditedBaseEntity<T extends Serializable> extends BaseEntity<T> {

  @Column(name = "created_by", length = 20)
  @CreatedBy
  protected Long createdBy;

  @Column(name = "updated_by", length = 20)
  @LastModifiedBy
  protected Long updatedBy;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at", length = 10)
  @CreatedDate
  protected Date createdAt;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updated_at", length = 10)
  @LastModifiedDate
  protected Date updatedAt;
}
