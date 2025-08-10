package com.wez.crm.util.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Slf4j
public class ExceptionHandlerUtil {
  private ExceptionHandlerUtil() {}

  public static ResponseEntity<?> handleException(String errorMessage, HttpStatus httpStatus, Exception ex) {
    log.error(errorMessage, ex);
    return ApiResponseUtil.failureWithHttpStatus(errorMessage, httpStatus);
  }
}