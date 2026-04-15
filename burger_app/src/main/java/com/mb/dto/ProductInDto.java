package com.mb.dto;

import com.mb.enums.CategoryEnum;
import com.mb.enums.TypeEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class ProductInDto {
  @NotBlank(message = "Name is required!")
  @Length(min = 2, max = 25, message = "Name should have 2 t0 25 characters!")
  private String name;

  @NotNull(message = "price should be provided!")
  @Positive(message = "price should be positive.")
  @Min(0)
  private Double price;

  @NotNull(message = "Category must be provided!")
  private CategoryEnum category;

  @NotNull(message = "Type must be provided!")
  private TypeEnum type;
}
