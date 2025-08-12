package com.wez.crm.controller;

import static com.wez.crm.util.constant.RouteConstant.PATH_ID;

import com.wez.crm.dto.ApiResponse;
import com.wez.crm.dto.CreateLeadSourceRequestDto;
import com.wez.crm.dto.LeadSourceResponseDto;
import com.wez.crm.dto.PaginationDto;
import com.wez.crm.service.LeadSourceService;
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
@RequestMapping(RouteConstant.LEAD_SOURCE_BASE_PATH)
public class LeadSourceController {

  private final LeadSourceService leadSourceService;

  @PostMapping
  public ResponseEntity<ApiResponse<LeadSourceResponseDto>> createLeadSource(@Valid @RequestBody CreateLeadSourceRequestDto createLeadSourceRequestDto) {
    return ApiResponseUtil.success(leadSourceService.createLeadSource(createLeadSourceRequestDto),
        String.format(ApiResponseConstant.CREATE_SUCCESSFUL_TEMPLATE, AppConstant.LEAD_SOURCE));
  }

  @GetMapping(PATH_ID)
  public ResponseEntity<ApiResponse<LeadSourceResponseDto>> getLeadSourceById(@PathVariable Long id) {
    return ApiResponseUtil.success(leadSourceService.getLeadSourceResponseDtoById(id),
        String.format(ApiResponseConstant.FETCH_SUCCESSFUL_TEMPLATE, AppConstant.LEAD_SOURCE));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<Page<LeadSourceResponseDto>>> getAllLeadSources(PaginationDto paginationDto) {
    return ApiResponseUtil.success(leadSourceService.findAllLeadSourceResponseDto(paginationDto.toPageable()),
        String.format(ApiResponseConstant.FETCH_SUCCESSFUL_TEMPLATE, AppConstant.LEAD_SOURCE));
  }

  @PatchMapping(PATH_ID)
  public ResponseEntity<ApiResponse<LeadSourceResponseDto>> updateLeadSource(@PathVariable Long id,
      @RequestBody CreateLeadSourceRequestDto createLeadSourceRequestDto) {
    return ApiResponseUtil.success(leadSourceService.updateLeadSource(id, createLeadSourceRequestDto),
        String.format(ApiResponseConstant.UPDATE_SUCCESSFUL_TEMPLATE, AppConstant.LEAD_SOURCE));
  }

  @DeleteMapping(PATH_ID)
  public ResponseEntity<ApiResponse<String>> deleteLeadSource(@PathVariable Long id) {
    return ApiResponseUtil.success(leadSourceService.deleteLeadSource(id),
        String.format(ApiResponseConstant.DELETE_SUCCESSFUL_TEMPLATE, AppConstant.LEAD_SOURCE));
  }
}
