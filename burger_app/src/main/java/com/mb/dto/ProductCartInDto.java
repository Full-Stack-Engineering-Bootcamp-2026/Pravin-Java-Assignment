package com.mb.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Getter
@ToString
public class ProductCartInDto {
  @NotBlank(message = "username is required!")
  @Length(min = 1, max = 25)
  private String userName;

  @NotBlank(message = "Email is required!")
  @Email
  @Length(min = 1, max = 25)
  private String email;

  @NotEmpty(message = "No product added!")
  @Valid
  private List<OrderInDto> order;
}
