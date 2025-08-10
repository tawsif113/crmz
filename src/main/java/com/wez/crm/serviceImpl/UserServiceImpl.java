package com.wez.crm.serviceImpl;

import static com.wez.crm.util.constant.ApiResponseConstant.DELETE_SUCCESSFUL_TEMPLATE;
import static com.wez.crm.util.constant.AppConstant.USER;
import static com.wez.crm.util.constant.ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID;
import static com.wez.crm.util.constant.ExceptionMessageConstant.ILLEGAL_OBJECT;

import com.wez.crm.dto.CreateUserRequestDto;
import com.wez.crm.dto.UpdateUserRequestDto;
import com.wez.crm.dto.UserResponseDto;
import com.wez.crm.entity.Role;
import com.wez.crm.entity.User;
import com.wez.crm.exception.NotFoundException;
import com.wez.crm.mapper.UserMapper;
import com.wez.crm.repository.UserRepository;
import com.wez.crm.service.RolePermissionService;
import com.wez.crm.service.UserService;
import com.wez.crm.util.constant.AppConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final RolePermissionService rolePermissionService;

  @Override
  public UserResponseDto createUser(CreateUserRequestDto userRequestDto) {
    validateUserRequest(userRequestDto);
    User user = userMapper.toEntity(userRequestDto, rolePermissionService.getReferenceById(userRequestDto.getRoleId()));
    return userMapper.toResponseDto(userRepository.save(user));
  }

  @Override
  public UserResponseDto getUserById(Long id) {
    return userRepository.findUserResponseDtoById(id)
        .orElseThrow(() -> new NotFoundException(String.format(ENTITY_NOT_FOUND_BY_ID, USER, id)));
  }

  @Override
  public UserResponseDto updateUser(Long id, UpdateUserRequestDto updateUserRequestDto) {
    validateUserRequest(updateUserRequestDto);

    User existingUser = userRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(String.format(ENTITY_NOT_FOUND_BY_ID, USER, id)));
    Role role = (updateUserRequestDto.getRoleId() != null) ? rolePermissionService.getReferenceById(updateUserRequestDto.getRoleId())
        : existingUser.getRole();

    existingUser = userMapper.updateEntityFromDto(updateUserRequestDto, existingUser, role);
    return userMapper.toResponseDto(userRepository.save(existingUser));
  }

  @Override
  public String deleteUser(Long id) {
    if (!userRepository.existsById(id)) {
      throw new NotFoundException(String.format(ENTITY_NOT_FOUND_BY_ID, USER, id));
    }
    userRepository.deleteById(id);
    return String.format(DELETE_SUCCESSFUL_TEMPLATE, USER);
  }

  @Override
  public Page<UserResponseDto> getAllUsers(Pageable pageable) {
    return userRepository.findAllUserResponseDto(pageable);
  }

  @Override
  public Boolean isUserExistsById(Long id) {
    return userRepository.existsById(id);
  }

  @Override
  public User getReferenceById(Long id) {
    return userRepository.getReferenceById(id);
  }

  private void validateUserRequest(Object userRequestDto) {

    switch (userRequestDto) {
      case CreateUserRequestDto createUserRequestDto -> {
        if (!rolePermissionService.isRoleExistsById(createUserRequestDto.getRoleId())) {
          throw new NotFoundException(String.format(ENTITY_NOT_FOUND_BY_ID, AppConstant.ROLE, createUserRequestDto.getRoleId()));
        }
      }
      case UpdateUserRequestDto updateUserRequestDto -> {
        if (updateUserRequestDto.getRoleId() != null && !rolePermissionService.isRoleExistsById(updateUserRequestDto.getRoleId())) {
          throw new NotFoundException(String.format(ENTITY_NOT_FOUND_BY_ID, AppConstant.ROLE, updateUserRequestDto.getRoleId()));
        }
      }
      default -> throw new IllegalArgumentException(String.format(ILLEGAL_OBJECT, userRequestDto.getClass().getName()));
    }
  }
}
