package com.wez.crm.dto;

import com.wez.crm.enums.ActivityStatus;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateActivityRequestDto {
  private Long contactId;
  private Long leadId;
  private Long typeId;
  private String subject;
  private String description;
  private LocalDateTime activityDateTime;
  private Long assignedUserId;
  private ActivityStatus status;
}
