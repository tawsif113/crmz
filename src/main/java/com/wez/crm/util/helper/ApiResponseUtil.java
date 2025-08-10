package com.wez.crm.util.helper;

import com.wez.crm.dto.ApiResponse;
import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponseUtil {
  private ApiResponseUtil() {}

  public static <T> ResponseEntity<ApiResponse<T>> success(T data, String message) {
    ApiResponse<T> response = ApiResponse.<T>builder()
        .success(true)
        .message(message)
        .data(data)
        .timestamp(Instant.now())
        .statusCode(HttpStatus.OK.value())
        .build();

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  public static <T> ResponseEntity<ApiResponse<T>> failure(String message) {
    ApiResponse<T> response = ApiResponse.<T>builder()
        .success(false)
        .message(message)
        .data(null)
        .timestamp(Instant.now())
        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .build();

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }

  public static <T> ResponseEntity<ApiResponse<T>> failureWithHttpStatus(String message, HttpStatus httpStatus) {
    ApiResponse<T> response = ApiResponse.<T>builder()
        .success(false)
        .message(message)
        .data(null)
        .timestamp(Instant.now())
        .statusCode(httpStatus.value())
        .build();

    return ResponseEntity.status(httpStatus).body(response);
  }
}