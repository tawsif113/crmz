package com.wez.crm.mapper;

import com.wez.crm.dto.ActivityResponseDto;
import com.wez.crm.dto.CreateActivityRequestDto;
import com.wez.crm.dto.UpdateActivityRequestDto;
import com.wez.crm.entity.Activity;
import com.wez.crm.entity.ActivityType;
import com.wez.crm.entity.Contact;
import com.wez.crm.entity.Lead;
import com.wez.crm.entity.User;
import com.wez.crm.enums.ActivityStatus;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ActivityMapper {

  ActivityResponseDto toResponseDto(Activity activity);

  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "lead", source = "lead")
  @Mapping(target = "contact", source = "contact")
  @Mapping(target = "type", source = "type")
  @Mapping(target = "assignedUser", source = "assignedUser")
  @Mapping(target = "subject", source = "createActivityRequestDto.subject")
  @Mapping(target = "description", source = "createActivityRequestDto.description")
  @Mapping(target = "activityDateTime", source = "createActivityRequestDto.activityDateTime")
  @Mapping(target = "status", constant = "PLANNED")
  Activity toEntity(CreateActivityRequestDto createActivityRequestDto,
                    Lead lead,
                    Contact contact,
                    ActivityType type,
                    User assignedUser);

  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "lead", source = "lead")
  @Mapping(target = "contact", source = "contact")
  @Mapping(target = "type", source = "type")
  @Mapping(target = "assignedUser", source = "assignedUser")
  @Mapping(target = "subject", source = "updateActivityRequestDto.subject")
  @Mapping(target = "description", source = "updateActivityRequestDto.description")
  @Mapping(target = "activityDateTime", source = "updateActivityRequestDto.activityDateTime")
  @Mapping(target = "status", constant = "PLANNED")
  void toUpdateRequestDto(UpdateActivityRequestDto updateActivityRequestDto, @MappingTarget Activity activity,
                          Lead lead, Contact contact, ActivityType type, User assignedUser);
}
