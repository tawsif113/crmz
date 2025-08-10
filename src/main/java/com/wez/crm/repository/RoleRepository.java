package com.wez.crm.repository;

import com.wez.crm.dto.RoleResponseDto;
import com.wez.crm.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {

  @Query("""
      SELECT new com.wez.crm.dto.RoleResponseDto(
      r.id,
      r.name,
      r.description,
      r.createdAt
      )
      FROM Role r
      ORDER BY r.createdAt DESC
  """)
  Page<RoleResponseDto> findAllRoles(Pageable pageable);
}
