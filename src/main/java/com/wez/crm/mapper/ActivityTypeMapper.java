package com.wez.crm.mapper;

import com.wez.crm.dto.ActivityTypeResponseDto;
import com.wez.crm.dto.CreateActivityTypeDto;
import com.wez.crm.entity.ActivityType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ActivityTypeMapper {

   ActivityTypeResponseDto toResponseDto(ActivityType activityType);
   ActivityType toEntity(CreateActivityTypeDto createActivityTypeDto);
   void updateEntityFromDto(CreateActivityTypeDto createActivityTypeDto, @MappingTarget ActivityType activityType);
}
