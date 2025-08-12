package com.wez.crm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateContactRequestDto {
  private Long userId;
  private String phoneNumber;
  private String companyName;
  private String jobTitle;
}
