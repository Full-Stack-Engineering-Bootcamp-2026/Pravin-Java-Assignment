package com.cdac.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class EmployeeInDto {

	@NotBlank(message = "Name is required!")
	private String name;

	@Email(message = "Invalid Email Format!")
	@NotBlank(message = "Email is required!")
	private String email;

	@NotNull
	@Positive(message = "Saraly can't be zero or nagative!")
	@Min(value = 1)
	private double salary;

	@NotBlank(message = "Status is required!")
	private String status;

	@NotNull
	@Positive(message = "id should be possitve!")
	@Min(value = 1, message = "id can't be zero")
	private Long department_id;

	@NotNull
	@Positive(message = "Role should be positive!")
	private Long role;

}
