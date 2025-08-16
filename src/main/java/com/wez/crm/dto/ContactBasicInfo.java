package com.wez.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ContactBasicInfo {
  private Long id;
  private String email;
  private String firstName;
  private String phoneNumber;
}
