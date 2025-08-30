package com.wez.crm.serviceImpl;

import com.wez.crm.dto.CreateNotificationMessageDto;
import com.wez.crm.dto.NotificationResponseDto;
import com.wez.crm.mapper.NotificationMapper;
import com.wez.crm.repository.NotificationRepository;
import com.wez.crm.service.NotificationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

  private final NotificationRepository notificationRepository;
  private final NotificationMapper notificationMapper;

  @Override
  public List<NotificationResponseDto> createNotificationMessage(CreateNotificationMessageDto createNotificationMessageDto) {

    return null;

  }
}
