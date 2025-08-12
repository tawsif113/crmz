package com.wez.crm.repository;

import com.wez.crm.dto.LeadResponseDto;
import com.wez.crm.entity.Lead;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LeadRepository extends JpaRepository<Lead, Long> {

  @Query("""
      SELECT new com.wez.crm.dto.LeadResponseDto(
        l.id,
        new com.wez.crm.dto.ContactBasicInfo(
          c.id,
          c.user.id,
          c.phoneNumber
          ),
        new com.wez.crm.dto.LeadSourceBasicInfo(
          s.id,
          s.name
          ),
        l.status,
        l.estimatedValue,
        new com.wez.crm.dto.UserBasicInfo(
          a.id,
          a.username,
          a.email
          ),
        l.lastActivityDate,
        l.followUpDate,
        l.expectedCloseDate,
        l.createdAt
      )
      FROM Lead l
      INNER JOIN l.contact c
      INNER JOIN l.source s
      INNER JOIN l.assignedSalesRep a
      WHERE l.id = :id
      ORDER BY l.createdAt DESC
  """)
  Optional<LeadResponseDto> findLeadResponseDtoById(Long id);
}
