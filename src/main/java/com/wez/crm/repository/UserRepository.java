package com.wez.crm.repository;

import com.wez.crm.dto.UserResponseDto;
import com.wez.crm.entity.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

  @Query("""
      SELECT new com.wez.crm.dto.UserResponseDto(
      u.id,
      u.username,
      u.email,
      new com.wez.crm.dto.RoleInfoDto(
          r.id,
          r.name
      ),
      u.status,
      u.createdAt
      )
      FROM User u
      LEFT JOIN u.role r
      WHERE u.id = :id
      ORDER BY u.createdAt DESC
  """)
  Optional<UserResponseDto> findUserResponseDtoById(Long id);

  @Query("""
      SELECT new com.wez.crm.dto.UserResponseDto(
      u.id,
      u.username,
      u.email,
      new com.wez.crm.dto.RoleInfoDto(
          r.id,
          r.name
      ),
      u.status,
      u.createdAt
      )
      FROM User u
      LEFT JOIN u.role r
      ORDER BY u.createdAt DESC
  """)
  Page<UserResponseDto> findAllUserResponseDto(Pageable pageable);
}
