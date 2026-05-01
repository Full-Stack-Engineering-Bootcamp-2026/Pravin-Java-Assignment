package com.mb.common.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class StudentUpdateDto {

  @NotBlank(message = "Name is required")
  @Length(max = 50, min = 3)
  private String name;

  @NotBlank(message = "Enrollment number is required")
  @Length(min = 6, max = 6)
  private String enrollmentNumber;
}