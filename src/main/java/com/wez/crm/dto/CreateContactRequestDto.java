package com.wez.crm.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateContactRequestDto {

  @NotNull(message = "User ID cannot be null")
  private Long userId;
  @NotNull(message = "Phone NUmber cannot be null")
  private String phoneNumber;

  private String companyName;
  private String jobTitle;
}
