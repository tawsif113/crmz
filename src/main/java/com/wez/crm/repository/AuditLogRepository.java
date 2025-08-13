package com.wez.crm.repository;

import com.wez.crm.dto.AuditLogResponseDto;
import com.wez.crm.entity.AuditLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

  @Query(value = """
    SELECT new com.wez.crm.dto.AuditLogResponseDto(
          a.id,
          a.entityName,
          a.entityId,
          a.actionType,
          a.details)
    FROM AuditLog a""",
    countQuery = "SELECT count(a) FROM AuditLog a")
  Page<AuditLogResponseDto> findAllAuditLogDtoPage(Pageable pageable);
}
