package com.wez.crm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateContactRequestDto {
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String companyName;
  private String jobTitle;
}
