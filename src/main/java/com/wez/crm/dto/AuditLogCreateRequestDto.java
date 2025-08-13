package com.wez.crm.dto;

import com.wez.crm.enums.ActionType;
import com.wez.crm.enums.EntityName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuditLogCreateRequestDto {
  @NotNull(message = "Entity name is required")
  private EntityName entityName;

  private Long entityId;

  @NotNull(message = "Action type is required")
  private ActionType actionType;

  @NotBlank(message = "Details cannot be blank")
  private String details;

}
