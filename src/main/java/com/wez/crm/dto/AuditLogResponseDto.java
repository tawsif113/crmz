package com.wez.crm.dto;

import com.wez.crm.enums.ActionType;
import com.wez.crm.enums.EntityName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogResponseDto {

  private Long id;

  private EntityName entityName;

  private Long entityId;

  private ActionType actionType;

  private String details;
}
