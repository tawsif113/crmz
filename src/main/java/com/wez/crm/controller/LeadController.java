package com.wez.crm.controller;

import static com.wez.crm.util.constant.RouteConstant.PATH_ID;

import com.wez.crm.dto.ApiResponse;
import com.wez.crm.dto.CreateLeadRequestDto;
import com.wez.crm.dto.LeadResponseDto;
import com.wez.crm.dto.PaginationDto;
import com.wez.crm.dto.UpdateLeadRequestDto;
import com.wez.crm.service.LeadService;
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
@RequestMapping(RouteConstant.LEAD_BASE_PATH)
public class LeadController {

  private final LeadService leadService;

  @PostMapping
  public ResponseEntity<ApiResponse<LeadResponseDto>> createLead(@Valid @RequestBody CreateLeadRequestDto createLeadRequestDto) {
    return ApiResponseUtil.success(leadService.createLead(createLeadRequestDto),
        String.format(ApiResponseConstant.CREATE_SUCCESSFUL_TEMPLATE, AppConstant.LEAD));
  }

  @GetMapping(PATH_ID)
  public ResponseEntity<ApiResponse<LeadResponseDto>> getLeadById(@PathVariable Long id) {
    return ApiResponseUtil.success(leadService.getLeadResponseDtoById(id),
        String.format(ApiResponseConstant.FETCH_SUCCESSFUL_TEMPLATE, AppConstant.LEAD));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<Page<LeadResponseDto>>> getAllLeads(PaginationDto paginationDto) {
    return ApiResponseUtil.success(leadService.findAllLeadResponseDto(paginationDto.toPageable()),
        String.format(ApiResponseConstant.FETCH_SUCCESSFUL_TEMPLATE, AppConstant.LEAD));
  }

  @PatchMapping(PATH_ID)
  public ResponseEntity<ApiResponse<LeadResponseDto>> updateLead(@PathVariable Long id,
      @RequestBody UpdateLeadRequestDto updateLeadRequestDto) {
    return ApiResponseUtil.success(leadService.updateLead(id, updateLeadRequestDto),
        String.format(ApiResponseConstant.UPDATE_SUCCESSFUL_TEMPLATE, AppConstant.LEAD));
  }

  @DeleteMapping(PATH_ID)
  public ResponseEntity<ApiResponse<String>> deleteLead(@PathVariable Long id) {
    return ApiResponseUtil.success(leadService.deleteLead(id),
        String.format(ApiResponseConstant.DELETE_SUCCESSFUL_TEMPLATE, AppConstant.LEAD));
  }
}
