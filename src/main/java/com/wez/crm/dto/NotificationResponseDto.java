package com.wez.crm.dto;

import com.wez.crm.enums.NotificationChannel;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NotificationResponseDto {
  private Long id;
  private Long userId;
  private NotificationChannel channel;
  private Map<String,Object> payload;
  private LocalDateTime timestamp;
  private Boolean isRead;
}
