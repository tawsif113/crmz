package com.wez.crm.controller;

import static com.wez.crm.util.constant.RouteConstant.ACTIVITY_BASE_PATH;
import static com.wez.crm.util.constant.RouteConstant.PATH_ID;

import com.wez.crm.dto.ActivityResponseDto;
import com.wez.crm.dto.ApiResponse;
import com.wez.crm.dto.CreateActivityRequestDto;
import com.wez.crm.dto.PaginationDto;
import com.wez.crm.dto.UpdateActivityRequestDto;
import com.wez.crm.service.ActivityService;
import com.wez.crm.util.constant.ApiResponseConstant;
import com.wez.crm.util.constant.AppConstant;
import com.wez.crm.util.helper.ApiResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ACTIVITY_BASE_PATH)
public class ActivityController {

  private final ActivityService activityService;

  @PostMapping
  public ResponseEntity<ApiResponse<ActivityResponseDto>> createContact(@Valid @RequestBody CreateActivityRequestDto createActivityRequestDto) {
    return ApiResponseUtil.success(activityService.createActivity(createActivityRequestDto),
        String.format(ApiResponseConstant.CREATE_SUCCESSFUL_TEMPLATE, AppConstant.ACTIVITY));
  }

  @GetMapping(PATH_ID)
  public ResponseEntity<ApiResponse<ActivityResponseDto>> getContactById(@PathVariable Long id) {
    return ApiResponseUtil.success(activityService.getActivityResponseDtoById(id),
        String.format(ApiResponseConstant.FETCH_SUCCESSFUL_TEMPLATE, AppConstant.ACTIVITY));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<Page<ActivityResponseDto>>> getAllContacts(PaginationDto paginationDto) {
    return ApiResponseUtil.success(activityService.findAllActivityResponseDto(paginationDto.toPageable()),
        String.format(ApiResponseConstant.FETCH_SUCCESSFUL_TEMPLATE, AppConstant.ACTIVITY));
  }

  @PatchMapping(PATH_ID)
  public ResponseEntity<ApiResponse<ActivityResponseDto>> updateContact(@PathVariable Long id,
      @RequestBody UpdateActivityRequestDto updateActivityRequestDto) {
    return ApiResponseUtil.success(activityService.updateActivity(id, updateActivityRequestDto),
        String.format(ApiResponseConstant.UPDATE_SUCCESSFUL_TEMPLATE, AppConstant.ACTIVITY));
  }

  @DeleteMapping(PATH_ID)
  public ResponseEntity<ApiResponse<String>> deleteContact(@PathVariable Long id) {
    return ApiResponseUtil.success(activityService.deleteActivity(id),
        String.format(ApiResponseConstant.DELETE_SUCCESSFUL_TEMPLATE, AppConstant.ACTIVITY));
  }
}
