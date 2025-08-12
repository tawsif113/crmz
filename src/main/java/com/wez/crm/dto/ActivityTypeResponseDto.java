package com.wez.crm.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityTypeResponseDto {
  private Long id;
  private String name;
  private String description;
  private Boolean isActive;
  private LocalDateTime createdAt;
}
