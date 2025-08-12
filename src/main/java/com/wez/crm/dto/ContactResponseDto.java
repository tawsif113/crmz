package com.wez.crm.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ContactResponseDto {
  private Long id;
  private UserBasicInfo user;
  private String phoneNumber;
  private String companyName;
  private String jobTitle;
  private LocalDateTime createdAt;
}
