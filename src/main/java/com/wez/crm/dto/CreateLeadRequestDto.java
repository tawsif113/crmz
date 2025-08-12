package com.wez.crm.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateLeadRequestDto {
  @NotNull(message = "Contact ID cannot be null")
  private Long contactId;
  @NotNull(message = "Source ID cannot be null")
  private Long sourceId;
  private BigDecimal estimatedValue;
  @NotNull(message = "Assigned Sales Rep ID cannot be null")
  private Long assignedSalesRepId;
  private LocalDateTime lastActivityDate;
  private LocalDate followUpDate;
  private LocalDate expectedCloseDate;
}
