package com.wez.crm.dto;

import com.wez.crm.enums.Status;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserResponseDto {
  private Long id;
  private String username;
  private String email;
  private RoleInfoDto role;
  private Status status;
  private LocalDateTime createdAt;
}
