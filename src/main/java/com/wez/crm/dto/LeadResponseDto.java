package com.wez.crm.dto;

import com.wez.crm.enums.LeadStatus;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LeadResponseDto {
  private Long id;
  private ContactBasicInfo contact;
  private LeadSourceBasicInfo source;
  private LeadStatus status;
  private BigDecimal estimatedValue;
  private UserBasicInfo assignedSalesRep;
  private LocalDateTime lastActivityDate;
  private LocalDate followUpDate;
  private LocalDate expectedCloseDate;
  private LocalDateTime createdAt;
}
