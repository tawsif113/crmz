package com.wez.crm.dto;

import com.wez.crm.enums.ActivityStatus;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateActivityRequestDto {
  @NotNull(message = "Contact ID cannot be null")
  private Long contactId;
  @NotNull(message = "Lead ID cannot be null")
  private Long leadId;
  @NotNull(message = "Type ID cannot be null")
  private Long typeId;
  @NotNull(message = "Subject cannot be null")
  private String subject;
  private String description;
  @NotNull(message = "Activity date and time cannot be null")
  private LocalDateTime activityDateTime;
  @NotNull(message = "Assigned user ID cannot be null")
  private Long assignedUserId;
  private ActivityStatus status;
}
