package com.mb.common.dto;

import java.time.LocalDateTime;

import com.mb.common.enums.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

  private Boolean success;
  private String message;
  private ErrorCode errorCode;
  private LocalDateTime timestamp;

  public ErrorResponse(Boolean success, String message, ErrorCode errorCode) {

    this.success = success;
    this.message = message;
    this.errorCode = errorCode;
    timestamp = LocalDateTime.now();
  }

}