package com.wez.crm.controller;

import static com.wez.crm.util.constant.RouteConstant.ACTIVITY_TYPE_BASE_PATH;
import static com.wez.crm.util.constant.RouteConstant.PATH_ID;

import com.wez.crm.dto.ActivityTypeResponseDto;
import com.wez.crm.dto.ApiResponse;
import com.wez.crm.dto.CreateActivityTypeDto;
import com.wez.crm.dto.PaginationDto;
import com.wez.crm.service.ActivityTypeService;
import com.wez.crm.util.constant.ApiResponseConstant;
import com.wez.crm.util.constant.AppConstant;
import com.wez.crm.util.constant.RouteConstant;
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
@RequestMapping(ACTIVITY_TYPE_BASE_PATH)
public class ActivityTypeController {

  private final ActivityTypeService activityTypeService;

  @PostMapping
  public ResponseEntity<ApiResponse<ActivityTypeResponseDto>> createActivityType(@Valid @RequestBody CreateActivityTypeDto createActivityTypeDto) {
    return ApiResponseUtil.success(activityTypeService.createActivityType(createActivityTypeDto),
        String.format(ApiResponseConstant.CREATE_SUCCESSFUL_TEMPLATE, AppConstant.ACTIVITY_TYPE));
  }

  @GetMapping(PATH_ID)
  public ResponseEntity<ApiResponse<ActivityTypeResponseDto>> getActivityTypeById(@PathVariable Long id) {
    return ApiResponseUtil.success(activityTypeService.getActivityTypeResponseDtoById(id),
        String.format(ApiResponseConstant.FETCH_SUCCESSFUL_TEMPLATE, AppConstant.ACTIVITY_TYPE));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<Page<ActivityTypeResponseDto>>> getAllActivityTypes(PaginationDto paginationDto) {
    return ApiResponseUtil.success(activityTypeService.getAllActivityTypes(paginationDto.toPageable()),
        String.format(ApiResponseConstant.FETCH_SUCCESSFUL_TEMPLATE, AppConstant.ACTIVITY_TYPE));
  }

  @PatchMapping(PATH_ID)
  public ResponseEntity<ApiResponse<ActivityTypeResponseDto>> updateActivityType(@PathVariable Long id,
      @RequestBody CreateActivityTypeDto createActivityTypeDto) {
    return ApiResponseUtil.success(activityTypeService.updateActivityType(id, createActivityTypeDto),
        String.format(ApiResponseConstant.UPDATE_SUCCESSFUL_TEMPLATE, AppConstant.ACTIVITY_TYPE));
  }

  @DeleteMapping(PATH_ID)
  public ResponseEntity<ApiResponse<String>> deleteActivityType(@PathVariable Long id) {
    return ApiResponseUtil.success(activityTypeService.deleteActivityType(id),
        String.format(ApiResponseConstant.DELETE_SUCCESSFUL_TEMPLATE, AppConstant.ACTIVITY_TYPE));
  }

}
