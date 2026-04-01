package com.cdac.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RoleInDto {

	@NotBlank
	private String title;

	@NotBlank
	private String description;

}
