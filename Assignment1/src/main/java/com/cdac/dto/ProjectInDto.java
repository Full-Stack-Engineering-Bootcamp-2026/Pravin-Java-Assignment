package com.cdac.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class ProjectInDto {
  @NotBlank(message = "Name is required!")
  private String name;

  @NotBlank(message = "Description is requrire!")
  @Length(min = 30)
  private String description;

  @FutureOrPresent
  private LocalDate startDate;

  @FutureOrPresent
  private LocalDate endDate;

  @NotBlank
  private String status;
}
