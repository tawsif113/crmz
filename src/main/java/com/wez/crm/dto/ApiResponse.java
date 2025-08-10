package com.wez.crm.dto;

import java.time.Instant;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse<T> {
  private T data;
  private String message;
  private boolean success;
  private int statusCode;
  private Instant timestamp;
}