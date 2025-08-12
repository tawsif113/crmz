package com.wez.crm.dto;

import com.wez.crm.enums.ActivityStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ActivityResponseDto {
  private Long id;
  private ContactBasicInfo contact;
  private LeadBasicInfo lead;
  private ActivityTypeBasicInfo type;
  private String subject;
  private LocalDateTime activityDateTime;
  private UserBasicInfo assignedUser;
  private ActivityStatus status;
  private LocalDateTime createdAt;
}
