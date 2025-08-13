package com.wez.crm.controller;

import static com.wez.crm.util.constant.RouteConstant.AUDIT_LOG_BASE_PATH;

import com.wez.crm.dto.ApiResponse;
import com.wez.crm.dto.AuditLogResponseDto;
import com.wez.crm.dto.PaginationDto;
import com.wez.crm.service.AuditLogService;
import com.wez.crm.util.constant.ApiResponseConstant;
import com.wez.crm.util.constant.AppConstant;
import com.wez.crm.util.helper.ApiResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AUDIT_LOG_BASE_PATH)
@RequiredArgsConstructor
public class AuditLogController {

  private final AuditLogService auditLogService;

  @GetMapping
  public ResponseEntity<ApiResponse<Page<AuditLogResponseDto>>> findAll(
      PaginationDto paginationDto) {
    return ApiResponseUtil.success(auditLogService.findAll(paginationDto),
        String.format(ApiResponseConstant.FETCH_SUCCESSFUL_TEMPLATE, AppConstant.AUDIT_LOG));
  }
}
