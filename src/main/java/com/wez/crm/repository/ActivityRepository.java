package com.wez.crm.repository;

import com.wez.crm.dto.ActivityResponseDto;
import com.wez.crm.entity.Activity;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

  @Query("""
      SELECT new com.wez.crm.dto.ActivityResponseDto(
      a.id,
      new com.wez.crm.dto.ContactBasicInfo(
          c.id,
          c.email,
          c.firstName,
          c.phoneNumber
      ),
      new com.wez.crm.dto.LeadBasicInfo(
          l.id
      ),
      new com.wez.crm.dto.ActivityTypeBasicInfo(
          at.id,
          at.name
      ),
      a.subject,
      a.activityDateTime,
      new com.wez.crm.dto.UserBasicInfo(
          u.id,
          u.username,
          u.email
      ),
      a.status,
      a.createdAt
      )
      FROM Activity a
      INNER JOIN a.contact c
      INNER JOIN a.lead l
      INNER JOIN a.type at
      INNER JOIN a.assignedUser u
      ORDER BY a.createdAt DESC
  """)
  Page<ActivityResponseDto> findAllActivityResponseDto(Pageable pageable);

  @Query("""
      SELECT new com.wez.crm.dto.ActivityResponseDto(
      a.id,
      new com.wez.crm.dto.ContactBasicInfo(
          c.id,
          c.email,
          c.firstName,
          c.phoneNumber
      ),
      new com.wez.crm.dto.LeadBasicInfo(
          l.id
      ),
      new com.wez.crm.dto.ActivityTypeBasicInfo(
          at.id,
          at.name
      ),
      a.subject,
      a.activityDateTime,
      new com.wez.crm.dto.UserBasicInfo(
          u.id,
          u.username,
          u.email
      ),
      a.status,
      a.createdAt
      )
      FROM Activity a
      INNER JOIN a.contact c
      INNER JOIN a.lead l
      INNER JOIN a.type at
      INNER JOIN a.assignedUser u
      WHERE a.id = :id
      ORDER BY a.createdAt DESC
  """)
  Optional<ActivityResponseDto> findActivityResponseDtoById(Long id);
}
