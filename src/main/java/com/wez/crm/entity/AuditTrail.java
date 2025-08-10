package com.wez.crm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "audit_trails")
public class AuditTrail extends BaseEntity{

  @Column(nullable = false)
  private String entityName;

  @Column(nullable = false)
  private Long entityId;

  @Column(nullable = false)
  private String action;

  @ManyToOne(optional = false)
  @JoinColumn(name = "user_id")
  private User user;
}
