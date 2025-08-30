package com.wez.crm.dto;

import com.wez.crm.util.annotations.ValidLeadRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ValidLeadRequest
public class UpdateLeadRequestDto {
  private Long contactId;
  private Long sourceId;
  private String status;
  private String estimatedValue;
  private Long assignedSalesRepId;
  private String lastActivityDate;
  private String followUpDate;
  private String expectedCloseDate;
}
