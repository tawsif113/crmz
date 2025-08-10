package com.wez.crm.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoleResponseDto {
  private Long id;
  private String name;
  private String description;
  private LocalDateTime createdAt;
}
