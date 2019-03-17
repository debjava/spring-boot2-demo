package com.ddlab.rnd.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({AccessDeniedException.class})
  public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
    return new ResponseEntity<Object>(
        toJSON("Access denied message here"), new HttpHeaders(), HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler({NullPointerException.class})
  public ResponseEntity<Object> handleNullPointerException(Exception ex, WebRequest request) {
    return new ResponseEntity<Object>(
        toJSON(ex.getMessage()), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler({IllegalArgumentException.class})
  public ResponseEntity<Object> handleIllegalArgumentException(Exception ex, WebRequest request) {
    return new ResponseEntity<Object>(
        toJSON(ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  private static String toJSON(Object obj) {
    ObjectMapper mapper = new ObjectMapper();
    String toJson = null;
    try {
      toJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return toJson;
  }
}
