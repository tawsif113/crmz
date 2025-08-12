package com.wez.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ContactBasicInfo {
  private Long id;
  private Long userId;
  private String phoneNumber;
}
