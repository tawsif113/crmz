package com.wez.crm.service;

import com.wez.crm.dto.RoleRequestDto;
import com.wez.crm.dto.RoleResponseDto;
import com.wez.crm.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RolePermissionService {

  Role findRoleById(Long id);

  RoleResponseDto findRoleResponseById(Long id);

  RoleResponseDto createRole(RoleRequestDto roleRequestDto);

  RoleResponseDto updateRole(Long id, RoleRequestDto roleRequestDto);

  String deleteRole(Long id);

  Page<RoleResponseDto> findAllRoles(Pageable pageable);

  Boolean isRoleExistsById(Long id);

  Role getReferenceById(Long id);
}
