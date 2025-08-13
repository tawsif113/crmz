package com.wez.crm.serviceImpl;

import com.wez.crm.dto.AuditLogCreateRequestDto;
import com.wez.crm.dto.AuditLogResponseDto;
import com.wez.crm.dto.PaginationDto;
import com.wez.crm.mapper.AuditLogMapper;
import com.wez.crm.repository.AuditLogRepository;
import com.wez.crm.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl implements AuditLogService {

  private final AuditLogMapper auditLogMapper;
  private final AuditLogRepository auditLogRepository;

  @Override
  public void create(AuditLogCreateRequestDto auditLogCreateRequestDto) {
    auditLogRepository.save(auditLogMapper.toEntity(auditLogCreateRequestDto));
  }

  @Override
  public Page<AuditLogResponseDto> findAll(PaginationDto paginationDto) {
    return auditLogRepository.findAllAuditLogDtoPage(paginationDto.toPageable());
  }
}
