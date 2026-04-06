package com.cdac.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RoleInDto {
  @NotBlank(message = "Title s required!")
  private String title;

  @NotBlank(message = "Description is required!")
  private String description;
}
