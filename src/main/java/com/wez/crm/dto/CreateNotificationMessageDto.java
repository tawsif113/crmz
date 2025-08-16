package com.wez.crm.dto;

import com.wez.crm.enums.NotificationChannel;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateNotificationMessageDto {
  private Long userId;
  private NotificationChannel channel;
  private Map<String,Object> payload;
}
