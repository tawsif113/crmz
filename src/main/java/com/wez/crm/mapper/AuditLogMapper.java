package com.wez.crm.mapper;

import com.wez.crm.dto.AuditLogCreateRequestDto;
import com.wez.crm.entity.AuditLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuditLogMapper {

  AuditLog toEntity(AuditLogCreateRequestDto auditLogCreateRequestDto);
}