package com.mb.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;

@Getter
public class ComboInDto {
  @NotNull
  private String comboName;

  @NotNull
  private Double actualPrice;

  @NotNull
  private Double comboPrice;

  @NotEmpty(message = "No product added!")
  private List<Long> products;
}
