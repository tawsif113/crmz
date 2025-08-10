package com.wez.crm.controller;


import static com.wez.crm.util.constant.RouteConstant.CRM_USER_BASE_PATH;
import static com.wez.crm.util.constant.RouteConstant.PATH_ID;

import com.wez.crm.dto.ApiResponse;
import com.wez.crm.dto.CreateUserRequestDto;
import com.wez.crm.dto.PaginationDto;
import com.wez.crm.dto.UpdateUserRequestDto;
import com.wez.crm.dto.UserResponseDto;
import com.wez.crm.service.UserService;
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
@RequestMapping(CRM_USER_BASE_PATH)
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<ApiResponse<UserResponseDto>> createUser(@Valid @RequestBody CreateUserRequestDto createUserRequestDto) {
    return ApiResponseUtil.success(userService.createUser(createUserRequestDto),
        String.format(ApiResponseConstant.CREATE_SUCCESSFUL_TEMPLATE, AppConstant.USER));
  }

  @GetMapping(PATH_ID)
  public ResponseEntity<ApiResponse<UserResponseDto>> getUserById(@PathVariable Long id) {
    return ApiResponseUtil.success(userService.getUserById(id),
        String.format(ApiResponseConstant.FETCH_SUCCESSFUL_TEMPLATE, AppConstant.USER));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<Page<UserResponseDto>>> getAllUsers(PaginationDto paginationDto) {
    return ApiResponseUtil.success(userService.getAllUsers(paginationDto.toPageable()),
        String.format(ApiResponseConstant.FETCH_SUCCESSFUL_TEMPLATE, AppConstant.USER));
  }

  @PatchMapping(PATH_ID)
  public ResponseEntity<ApiResponse<UserResponseDto>> updateUser(@PathVariable Long id,
      @RequestBody UpdateUserRequestDto updateUserRequestDto) {
    return ApiResponseUtil.success(userService.updateUser(id, updateUserRequestDto),
        String.format(ApiResponseConstant.UPDATE_SUCCESSFUL_TEMPLATE, AppConstant.USER));
  }

  @DeleteMapping(PATH_ID)
  public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Long id) {
    return ApiResponseUtil.success(userService.deleteUser(id),
        String.format(ApiResponseConstant.DELETE_SUCCESSFUL_TEMPLATE, AppConstant.USER));
  }

}
