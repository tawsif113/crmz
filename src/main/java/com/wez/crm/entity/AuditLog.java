package com.wez.crm.entity;

import com.wez.crm.enums.ActionType;
import com.wez.crm.enums.EntityName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "audit_log")
@Getter
@Setter
public class AuditLog extends BaseEntity {

  @Enumerated(EnumType.STRING)
  @Column(name = "entity_name", length = 100, nullable = false)
  private EntityName entityName;

  @Column(name = "entity_id")
  private Long entityId;

  @Enumerated(EnumType.STRING)
  @Column(name = "action_type", length = 50, nullable = false)
  private ActionType actionType;

  @Column(name = "details", columnDefinition = "TEXT", nullable = false)
  private String details;
}