package com.wez.crm.controller;

import static com.wez.crm.util.constant.RouteConstant.GET_ROLE_BY_ID;
import static com.wez.crm.util.constant.RouteConstant.ROLE_BASE_PATH;
import static com.wez.crm.util.constant.RouteConstant.ROLE_PERMISSION_BASE_PATH;

import com.wez.crm.dto.ApiResponse;
import com.wez.crm.dto.RoleRequestDto;
import com.wez.crm.dto.RoleResponseDto;
import com.wez.crm.service.RolePermissionService;
import com.wez.crm.util.constant.ApiResponseConstant;
import com.wez.crm.util.constant.AppConstant;
import com.wez.crm.util.helper.ApiResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ROLE_PERMISSION_BASE_PATH)
@RequiredArgsConstructor
public class RolePermissionController {

  private final RolePermissionService rolePermissionService;

  @GetMapping(GET_ROLE_BY_ID)
  public ResponseEntity<ApiResponse<RoleResponseDto>> getRoleById(@PathVariable("id") Long id) {
    return ApiResponseUtil.success(rolePermissionService.findRoleResponseById(id),
        String.format(ApiResponseConstant.FETCH_SUCCESSFUL_TEMPLATE, id));
  }

  @PostMapping(ROLE_BASE_PATH)
  public ResponseEntity<ApiResponse<RoleResponseDto>> createRole(@Valid @RequestBody RoleRequestDto roleRequestDto) {
    return ApiResponseUtil.success(rolePermissionService.createRole(roleRequestDto),
        String.format(ApiResponseConstant.CREATE_SUCCESSFUL_TEMPLATE, AppConstant.ROLE));
  }
}
