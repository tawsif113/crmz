package com.wez.crm.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.wez.crm.util.constant.ExceptionMessageConstant;
import com.wez.crm.util.helper.ExceptionHandlerUtil;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleAllExceptions(Exception ex) {
    return ExceptionHandlerUtil.handleException("Internal server error occurred. Reason: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, ex);
  }
  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
    String errorMessage = String.format(ExceptionMessageConstant.ERROR_MESSAGE_CONSTANT, "Database", "invalid input");
    if (ex != null) {
      ex.getMostSpecificCause();
      errorMessage += " Reason: " + ex.getMostSpecificCause().getMessage();
    }
    return ExceptionHandlerUtil.handleException(errorMessage, HttpStatus.BAD_REQUEST, ex);
  }
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
    return ExceptionHandlerUtil.handleException(ex.getMessage(), HttpStatus.BAD_REQUEST, ex);
  }
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<?> handleNotFoundException(NotFoundException ex) {
    return ExceptionHandlerUtil.handleException(ex.getMessage(), HttpStatus.NOT_FOUND, ex);
  }
  @ExceptionHandler(ResourceAlreadyExistsException.class)
  public ResponseEntity<?> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
    return ExceptionHandlerUtil.handleException(ex.getMessage(), HttpStatus.CONFLICT, ex);
  }
  @ExceptionHandler(ConflictException.class)
  public ResponseEntity<?> handleConflictException(ConflictException ex) {
    return ExceptionHandlerUtil.handleException(ex.getMessage(), HttpStatus.CONFLICT, ex);
  }
  @ExceptionHandler(InvalidFormatException.class)
  public ResponseEntity<?> handleInvalidFormatException(InvalidFormatException ex) {
    return ExceptionHandlerUtil.handleException(ex.getMessage(), HttpStatus.BAD_REQUEST, ex);
  }
}