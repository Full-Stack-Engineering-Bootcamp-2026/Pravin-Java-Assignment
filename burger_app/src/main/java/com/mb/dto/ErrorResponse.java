package com.mb.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse<T> {
  private String message;

  private LocalDateTime timestamp;
  private T detail;

  public ErrorResponse(String message, T detail) {
    this.message = message;

    this.detail = detail;
    this.timestamp = LocalDateTime.now();
  }
}
