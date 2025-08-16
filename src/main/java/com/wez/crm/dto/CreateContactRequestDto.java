package com.wez.crm.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateContactRequestDto {

  @NotNull(message = "First Name cannot be null")
  private String firstName;

  @NotNull(message = "Last Name cannot be null")
  private String lastName;

  @NotNull(message = "Email cannot be null")
  private String email;

  @NotNull(message = "Phone NUmber cannot be null")
  private String phoneNumber;

  private String companyName;
  private String jobTitle;
}
