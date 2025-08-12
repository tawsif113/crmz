package com.wez.crm.service;

import com.wez.crm.dto.ActivityResponseDto;
import com.wez.crm.dto.CreateActivityRequestDto;
import com.wez.crm.dto.UpdateActivityRequestDto;
import com.wez.crm.entity.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActivityService {

  Activity getActivityById(Long id);

  ActivityResponseDto createActivity(CreateActivityRequestDto createActivityRequestDto);

  ActivityResponseDto updateActivity(Long id, UpdateActivityRequestDto updateActivityRequestDto);

  String deleteActivity(Long id);

  ActivityResponseDto getActivityResponseDtoById(Long id);

  Boolean isActivityExistsById(Long id);

  Activity getReferenceById(Long id);

  Page<ActivityResponseDto> findAllActivityResponseDto(Pageable pageable);

}
