package com.wez.crm.service;

import com.wez.crm.dto.CreateUserRequestDto;
import com.wez.crm.dto.UpdateUserRequestDto;
import com.wez.crm.dto.UserResponseDto;
import com.wez.crm.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

  UserResponseDto createUser(CreateUserRequestDto userRequestDto);

  UserResponseDto getUserById(Long id);

  UserResponseDto updateUser(Long id, UpdateUserRequestDto updateUserRequestDto);

  String deleteUser(Long id);

  Page<UserResponseDto> getAllUsers(Pageable pageable);

  Boolean isUserExistsById(Long id);

  User getReferenceById(Long id);
}
