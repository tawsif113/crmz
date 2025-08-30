package com.wez.crm.dto;

import com.wez.crm.enums.NotificationChannel;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Map;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateNotificationMessageDto {

  @NotEmpty(message = "Recipients cannot be empty")
  private Set<Long> recipients;

  @NotNull(message = "Notification channel is required")
  private NotificationChannel channel;

  @NotNull(message = "Payload is required")
  private Map<String, Object> payload;

  private String subject;
}
