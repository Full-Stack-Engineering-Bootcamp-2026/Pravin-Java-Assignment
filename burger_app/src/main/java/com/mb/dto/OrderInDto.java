package com.mb.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OrderInDto {
  @NotNull(message = "Product id is required!")
  private Long id;

  @NotNull(message = "Quanity is required!")
  @Min(1)
  @Max(100)
  private Integer quantity;
}
