package com.wez.crm.serviceImpl;

import com.wez.crm.dto.ActivityTypeResponseDto;
import com.wez.crm.dto.CreateActivityTypeDto;
import com.wez.crm.entity.ActivityType;
import com.wez.crm.exception.NotFoundException;
import com.wez.crm.mapper.ActivityTypeMapper;
import com.wez.crm.repository.ActivityTypeRepository;
import com.wez.crm.service.ActivityTypeService;
import com.wez.crm.util.constant.ApiResponseConstant;
import com.wez.crm.util.constant.AppConstant;
import com.wez.crm.util.constant.ExceptionMessageConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityTypeServiceImpl implements ActivityTypeService {

  private final ActivityTypeRepository activityTypeRepository;
  private final ActivityTypeMapper activityTypeMapper;

  @Override
  public ActivityType getActivityTypeById(Long id) {
    return activityTypeRepository.findById(id).orElseThrow(()->
        new NotFoundException(String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID, AppConstant.ACTIVITY_TYPE, id)));
  }

  @Override
  public ActivityTypeResponseDto getActivityTypeResponseDtoById(Long id) {
    return activityTypeMapper.toResponseDto(getActivityTypeById(id));
  }

  @Override
  public ActivityTypeResponseDto createActivityType(CreateActivityTypeDto createActivityTypeDto) {
    return activityTypeMapper.toResponseDto(activityTypeRepository.save(activityTypeMapper.toEntity(createActivityTypeDto)));
  }

  @Override
  public ActivityTypeResponseDto updateActivityType(Long id, CreateActivityTypeDto activityTypeDto) {
    ActivityType activityType = getActivityTypeById(id);
    activityTypeMapper.updateEntityFromDto(activityTypeDto, activityType);
    return activityTypeMapper.toResponseDto(activityTypeRepository.save(activityType));
  }

  @Override
  public String deleteActivityType(Long id) {
    return activityTypeRepository.findById(id)
        .map(activityType -> {
          activityTypeRepository.delete(activityType);
          return String.format(ApiResponseConstant.DELETE_SUCCESSFUL_TEMPLATE, AppConstant.ACTIVITY_TYPE);
        })
        .orElseThrow(() -> new NotFoundException(String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID, AppConstant.ACTIVITY_TYPE, id)));
  }

  @Override
  public Boolean isActivityTypeExistsById(Long id) {
    return activityTypeRepository.existsById(id);
  }

  @Override
  public ActivityType getReferenceById(Long id) {
    return activityTypeRepository.getReferenceById(id);
  }

  @Override
  public Page<ActivityTypeResponseDto> getAllActivityTypes(Pageable pageable) {
    return activityTypeRepository.findAll(pageable)
        .map(activityTypeMapper::toResponseDto);
  }
}
