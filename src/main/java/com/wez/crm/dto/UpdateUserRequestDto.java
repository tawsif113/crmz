package com.wez.crm.dto;

import com.wez.crm.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequestDto {
  private String username;
  private String email;
  private String firstName;
  private String lastName;
  private Long roleId;
  private Status status;
  private String passwordHash;
}
