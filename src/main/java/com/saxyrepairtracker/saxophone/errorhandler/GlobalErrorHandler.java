package com.saxyrepairtracker.saxophone.errorhandler;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalErrorHandler {
  private enum LogStatus {
    STACK_TRACE, MESSAGE_ONLY
  }
  
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  //Overwrites the 200 exception
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  public Map<String, Object> MethodArgumentTypeMismatchException(
      MethodArgumentTypeMismatchException e, WebRequest webRequest) {
    return createExceptionMessage(e, HttpStatus.BAD_REQUEST, webRequest,
        LogStatus.MESSAGE_ONLY);
  } 
  
  /**
   * 
   * @param e
   * @param webRequest
   * @return
   */
  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  public Map<String, Object> handleConstraintViolationException(
      ConstraintViolationException e, WebRequest webRequest) {
    return createExceptionMessage(e, HttpStatus.BAD_REQUEST, webRequest,
        LogStatus.MESSAGE_ONLY);
  }
  
  /**
   * 
   * @param e
   * @param webRequest
   * @return
   */
  @ExceptionHandler(NoSuchElementException.class)
  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  public Map<String, Object> handleNoSuchElementException(
      NoSuchElementException e, WebRequest webRequest) {
    return createExceptionMessage(e, HttpStatus.NOT_FOUND, webRequest, 
        LogStatus.MESSAGE_ONLY);
  }

  /**
   * 
   * @param e
   * @param webRequest
   * @return
   */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  public Map<String, Object> handleException(Exception e, 
      WebRequest webRequest) {
    return createExceptionMessage(e, HttpStatus.INTERNAL_SERVER_ERROR, 
        webRequest, LogStatus.STACK_TRACE);
  }
  
  /**
   * 
   * @param e
   * @param status
   * @param webRequest
   * @return
   */
  private Map<String, Object> createExceptionMessage(Exception e, 
      HttpStatus status, WebRequest webRequest, LogStatus logStatus) {
    Map<String, Object> error = new HashMap<>();
    String timestamp = 
        ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
    
    if(webRequest instanceof ServletWebRequest) {
      error.put("uri",  ((ServletWebRequest)webRequest).getRequest().getRequestURI());
    } else {
      error.put("uri", webRequest.getContextPath());
    }
    
    error.put("message", e.toString());
    error.put("status code", status.value());

    error.put("timestamp", timestamp);
    error.put("reason",  status.getReasonPhrase());
    
    if(logStatus == LogStatus.MESSAGE_ONLY) {
      log.error("Exception: {}", e.toString());
    }
    else {
      log.error("Exception:", e);
    }
    
    return error;
  }
}
