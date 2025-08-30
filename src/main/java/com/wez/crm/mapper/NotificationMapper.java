package com.wez.crm.mapper;

import com.wez.crm.dto.CreateNotificationMessageDto;
import com.wez.crm.dto.NotificationResponseDto;
import com.wez.crm.entity.Notification;
import com.wez.crm.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NotificationMapper {

  @Mapping(target = "userId", source = "receiver.id")
  NotificationResponseDto toResponseDto(Notification notification);

  @Mapping(target = "receiver", source = "receiver")
  Notification toEntity(CreateNotificationMessageDto createNotificationMessageDto, User receiver);

}
