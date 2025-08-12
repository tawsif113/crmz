package com.wez.crm.service;

import com.wez.crm.dto.ActivityTypeResponseDto;
import com.wez.crm.dto.CreateActivityTypeDto;
import com.wez.crm.entity.ActivityType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActivityTypeService {

  ActivityType getActivityTypeById(Long id);

  ActivityTypeResponseDto getActivityTypeResponseDtoById(Long id);

  ActivityTypeResponseDto createActivityType(CreateActivityTypeDto createActivityTypeDto);

  ActivityTypeResponseDto updateActivityType(Long id, CreateActivityTypeDto activityTypeDto);

  String deleteActivityType(Long id);

  Boolean isActivityTypeExistsById(Long id);

  ActivityType getReferenceById(Long id);

  Page<ActivityTypeResponseDto> getAllActivityTypes(Pageable pageable);
}
