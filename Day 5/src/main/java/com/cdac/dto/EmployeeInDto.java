package com.cdac.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeInDto {

	@NotBlank
	private String name;
	@NotNull
	private double salary;
	@Email
	@NotBlank
	private String email;

	@NotBlank
	private String department;
}
