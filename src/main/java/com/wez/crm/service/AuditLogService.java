package com.wez.crm.service;

import com.wez.crm.dto.AuditLogCreateRequestDto;
import com.wez.crm.dto.AuditLogResponseDto;
import com.wez.crm.dto.PaginationDto;
import org.springframework.data.domain.Page;

public interface AuditLogService {

  void create(AuditLogCreateRequestDto auditLogCreateRequestDto);

  Page<AuditLogResponseDto> findAll(PaginationDto paginationDto);

}
