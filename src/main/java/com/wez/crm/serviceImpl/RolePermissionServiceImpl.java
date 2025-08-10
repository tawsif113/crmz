package com.wez.crm.serviceImpl;

import static com.wez.crm.util.constant.ApiResponseConstant.DELETE_SUCCESSFUL_TEMPLATE;
import static com.wez.crm.util.constant.AppConstant.ROLE;
import static com.wez.crm.util.constant.ExceptionMessageConstant.ENTITY_NOT_FOUND_BY_ID;

import com.wez.crm.dto.RoleRequestDto;
import com.wez.crm.dto.RoleResponseDto;
import com.wez.crm.entity.Role;
import com.wez.crm.exception.NotFoundException;
import com.wez.crm.mapper.RoleMapper;
import com.wez.crm.repository.RoleRepository;
import com.wez.crm.service.RolePermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolePermissionServiceImpl implements RolePermissionService {

  private final RoleRepository roleRepository;
  private final RoleMapper roleMapper;

  @Override
  public Role findRoleById(Long id) {
    return roleRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(String.format(ENTITY_NOT_FOUND_BY_ID, ROLE, id)));
  }

  @Override
  public RoleResponseDto findRoleResponseById(Long id) {
    return roleMapper.toResponseDto(findRoleById(id));
  }

  @Override
  public RoleResponseDto createRole(RoleRequestDto roleRequestDto) {
    Role role = roleMapper.toEntity(roleRequestDto);
    return roleMapper.toResponseDto(roleRepository.save(role));
  }

  @Override
  public RoleResponseDto updateRole(Long id, RoleRequestDto roleRequestDto) {
    Role existingRole = findRoleById(id);
    roleMapper.updateEntityFromDto(roleRequestDto, existingRole);
    return roleMapper.toResponseDto(roleRepository.save(existingRole));
  }

  @Override
  public String deleteRole(Long id) {
    if (!isRoleExistsById(id)) {
      throw new NotFoundException(String.format(ENTITY_NOT_FOUND_BY_ID, ROLE, id));
    }
    roleRepository.deleteById(id);
    return String.format(DELETE_SUCCESSFUL_TEMPLATE, ROLE);
  }

  @Override
  public Page<RoleResponseDto> findAllRoles(Pageable pageable) {
    return roleRepository.findAllRoles(pageable);
  }

  @Override
  public Boolean isRoleExistsById(Long id) {
    return roleRepository.existsById(id);
  }

  @Override
  public Role getReferenceById(Long id) {
    return roleRepository.getReferenceById(id);
  }
}
