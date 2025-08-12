package com.wez.crm.serviceImpl;

import com.wez.crm.dto.ActivityResponseDto;
import com.wez.crm.dto.CreateActivityRequestDto;
import com.wez.crm.dto.UpdateActivityRequestDto;
import com.wez.crm.entity.Activity;
import com.wez.crm.entity.ActivityType;
import com.wez.crm.entity.Contact;
import com.wez.crm.entity.Lead;
import com.wez.crm.entity.User;
import com.wez.crm.exception.NotFoundException;
import com.wez.crm.mapper.ActivityMapper;
import com.wez.crm.repository.ActivityRepository;
import com.wez.crm.service.ActivityService;
import com.wez.crm.service.ActivityTypeService;
import com.wez.crm.service.ContactService;
import com.wez.crm.service.LeadService;
import com.wez.crm.service.UserService;
import com.wez.crm.util.constant.ApiResponseConstant;
import com.wez.crm.util.constant.AppConstant;
import com.wez.crm.util.constant.ExceptionMessageConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

  private final ActivityRepository activityRepository;
  private final ActivityMapper activityMapper;
  private final ContactService contactService;
  private final LeadService leadService;
  private final UserService userService;
  private final ActivityTypeService activityTypeService;

  @Override
  public Activity getActivityById(Long id) {
    return activityRepository.findById(id).orElseThrow(()-> new NotFoundException(String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID,
        AppConstant.ACTIVITY,id)));
  }

  @Override
  public ActivityResponseDto createActivity(CreateActivityRequestDto createActivityRequestDto) {
    validateActivityRequestDto(createActivityRequestDto);

    Lead lead = leadService.getReferenceById(createActivityRequestDto.getLeadId());
    Contact contact = contactService.getReferenceById(createActivityRequestDto.getContactId());
    ActivityType activityType = activityTypeService.getReferenceById(createActivityRequestDto.getTypeId());
    User assignedUser = userService.getReferenceById(createActivityRequestDto.getAssignedUserId());

    Activity activity = activityMapper.toEntity(createActivityRequestDto, lead, contact, activityType, assignedUser);
    return activityMapper.toResponseDto(activityRepository.save(activity));
  }

  @Override
  public ActivityResponseDto updateActivity(Long id, UpdateActivityRequestDto updateActivityRequestDto) {
    validateActivityRequestDto(updateActivityRequestDto);

    Activity activity = getActivityById(id);
    Lead lead = leadService.getReferenceById(updateActivityRequestDto.getLeadId());
    Contact contact = contactService.getReferenceById(updateActivityRequestDto.getContactId());
    ActivityType activityType = activityTypeService.getReferenceById(updateActivityRequestDto.getTypeId());
    User assignedUser = userService.getReferenceById(updateActivityRequestDto.getAssignedUserId());

    activityMapper.toUpdateRequestDto(updateActivityRequestDto, activity,lead, contact, activityType, assignedUser);

    return activityMapper.toResponseDto(activityRepository.save(activity));
  }

  @Override
  public String deleteActivity(Long id) {
    if (!activityRepository.existsById(id)) {
      throw new NotFoundException(String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID,
          AppConstant.ACTIVITY, id));
    }
    activityRepository.deleteById(id);
    return String.format(ApiResponseConstant.DELETE_SUCCESSFUL_TEMPLATE, AppConstant.ACTIVITY);
  }

  @Override
  public ActivityResponseDto getActivityResponseDtoById(Long id) {
    return activityRepository.findActivityResponseDtoById(id)
        .orElseThrow(() -> new NotFoundException(String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID,
            AppConstant.ACTIVITY, id)));
  }

  @Override
  public Boolean isActivityExistsById(Long id) {
    return activityRepository.existsById(id);
  }

  @Override
  public Activity getReferenceById(Long id) {
    return activityRepository.getReferenceById(id);
  }

  @Override
  public Page<ActivityResponseDto> findAllActivityResponseDto(Pageable pageable) {
    return activityRepository.findAllActivityResponseDto(pageable);
  }

  private void validateActivityRequestDto(Object requestDto){
    switch (requestDto) {
      case CreateActivityRequestDto createActivityRequestDto -> {
        if(!contactService.isContactExistsById(createActivityRequestDto.getContactId())) {
          throw new NotFoundException(String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID,
              AppConstant.CONTACT, createActivityRequestDto.getContactId()));
        }
        if(!leadService.isLeadExistsById(createActivityRequestDto.getLeadId())) {
          throw new NotFoundException(String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID,
              AppConstant.LEAD, createActivityRequestDto.getLeadId()));
        }
        if(!userService.isUserExistsById(createActivityRequestDto.getAssignedUserId())) {
          throw new NotFoundException(String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID,
              AppConstant.USER, createActivityRequestDto.getAssignedUserId()));
        }
        if(!activityTypeService.isActivityTypeExistsById(createActivityRequestDto.getTypeId())) {
          throw new NotFoundException(String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID,
              AppConstant.ACTIVITY_TYPE, createActivityRequestDto.getTypeId()));
        }
      }
      case UpdateActivityRequestDto updateActivityRequestDto -> {
        if (updateActivityRequestDto.getContactId() != null && !contactService.isContactExistsById(updateActivityRequestDto.getContactId())) {
          throw new NotFoundException(String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID,
              AppConstant.CONTACT, updateActivityRequestDto.getContactId()));
        }
        if (updateActivityRequestDto.getLeadId() != null && !leadService.isLeadExistsById(updateActivityRequestDto.getLeadId())) {
          throw new NotFoundException(String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID,
              AppConstant.LEAD, updateActivityRequestDto.getLeadId()));
        }
        if (updateActivityRequestDto.getAssignedUserId() != null && !userService.isUserExistsById(updateActivityRequestDto.getAssignedUserId())) {
          throw new NotFoundException(String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID,
              AppConstant.USER, updateActivityRequestDto.getAssignedUserId()));
        }
        if (updateActivityRequestDto.getTypeId() != null && !activityTypeService.isActivityTypeExistsById(updateActivityRequestDto.getTypeId())) {
          throw new NotFoundException(String.format(ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID,
              AppConstant.ACTIVITY_TYPE, updateActivityRequestDto.getTypeId()));
        }
      }
      default -> throw new IllegalArgumentException(String.format(ExceptionMessageConstant.ILLEGAL_OBJECT,requestDto.getClass().getName()));
    }
  }
}
