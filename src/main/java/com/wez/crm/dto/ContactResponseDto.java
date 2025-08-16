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
  private String firstName;
  private String email;
  private String jobTitle;
  private LocalDateTime createdAt;
}
