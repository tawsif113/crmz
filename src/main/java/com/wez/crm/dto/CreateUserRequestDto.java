package com.wez.crm.dto;

import com.wez.crm.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequestDto {
  private String username;
  private String email;
  private String firstName;
  private String lastName;
  @NotNull
  private Long roleId;
  private Status status;
  private String passwordHash;
}
