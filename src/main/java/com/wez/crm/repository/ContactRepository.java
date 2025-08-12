package com.wez.crm.repository;

import com.wez.crm.dto.ContactResponseDto;
import com.wez.crm.entity.Contact;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContactRepository extends JpaRepository<Contact, Long> {

  @Query("""
      SELECT new com.wez.crm.dto.ContactResponseDto(
      c.id,
      new com.wez.crm.dto.UserBasicInfo(
          u.id,
          u.username,
          u.email
      ),
      c.phoneNumber,
      c.companyName,
      c.jobTitle,
      c.createdAt
      )
      FROM Contact c
      INNER JOIN c.user u
      WHERE c.id = :id
      ORDER BY c.createdAt DESC
  """)
  Optional<ContactResponseDto> findContactResponseDtoById(Long id);

  @Query("""
      SELECT new com.wez.crm.dto.ContactResponseDto(
      c.id,
      new com.wez.crm.dto.UserBasicInfo(
          u.id,
          u.username,
          u.email
      ),
      c.phoneNumber,
      c.companyName,
      c.jobTitle,
      c.createdAt
      )
      FROM Contact c
      INNER JOIN c.user u
      ORDER BY c.createdAt DESC
  """)
  Page<ContactResponseDto> findAllContactResponseDto(Pageable pageable);
}
