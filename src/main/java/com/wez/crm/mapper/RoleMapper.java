package com.wez.crm.mapper;

import com.wez.crm.dto.RoleRequestDto;
import com.wez.crm.dto.RoleResponseDto;
import com.wez.crm.entity.Role;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RoleMapper {

   RoleResponseDto toResponseDto(Role role);
   Role toEntity(RoleRequestDto roleRequestDto);

   @InheritConfiguration(name = "toEntity")
   void updateEntityFromDto(RoleRequestDto roleRequestDto, @MappingTarget Role role);
}
