package com.wez.crm.service;

import com.wez.crm.dto.CreateNotificationMessageDto;
import com.wez.crm.dto.NotificationResponseDto;
import java.util.List;

public interface NotificationService {

  List<NotificationResponseDto> createNotificationMessage(CreateNotificationMessageDto createNotificationMessageDto);
}
