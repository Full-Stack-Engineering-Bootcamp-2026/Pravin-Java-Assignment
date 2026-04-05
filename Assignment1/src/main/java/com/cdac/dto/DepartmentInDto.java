package com.cdac.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DepartmentInDto {

	@NotBlank(message = "Name is required!")
	@Length(min = 2, max = 20, message = "Name must have 2 characters!")
	private String name;

	@NotBlank(message = "Location is required!")
	private String location;

}
