package com.cdac.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DepartmentInDto {

	@NotBlank
	@Length(min = 2, max = 20)
	private String name;

	@NotBlank
	private String location;

}
