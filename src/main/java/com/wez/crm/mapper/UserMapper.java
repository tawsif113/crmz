package com.wez.crm.mapper;

import com.wez.crm.dto.CreateUserRequestDto;
import com.wez.crm.dto.UpdateUserRequestDto;
import com.wez.crm.dto.UserResponseDto;
import com.wez.crm.entity.Role;
import com.wez.crm.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserResponseDto toResponseDto(User user);

  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "role", source = "role")
  @Mapping(target = "username", source = "userRequestDto.username")
  @Mapping(target = "email", source = "userRequestDto.email")
  @Mapping(target = "firstName", source = "userRequestDto.firstName")
  @Mapping(target = "lastName", source = "userRequestDto.lastName")
  @Mapping(target = "passwordHash", source = "userRequestDto.passwordHash")
  @Mapping(target = "status", source = "userRequestDto.status")
  User toEntity(CreateUserRequestDto userRequestDto, Role role);

  @BeanMapping(ignoreByDefault = true, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  @Mapping(target = "role", source = "role")
  @Mapping(target = "username", source = "userRequestDto.username")
  @Mapping(target = "email", source = "userRequestDto.email")
  @Mapping(target = "firstName", source = "userRequestDto.firstName")
  @Mapping(target = "lastName", source = "userRequestDto.lastName")
  @Mapping(target = "passwordHash", source = "userRequestDto.passwordHash")
  @Mapping(target = "status", source = "userRequestDto.status")
  User updateEntityFromDto(UpdateUserRequestDto userRequestDto, @MappingTarget User existingUser, Role role);
}
