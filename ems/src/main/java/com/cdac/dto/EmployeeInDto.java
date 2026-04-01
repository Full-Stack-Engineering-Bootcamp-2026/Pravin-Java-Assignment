package com.cdac.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

//@NoArgsConstructor
@Getter
//@Setter
//@ToString
public class EmployeeInDto {

	@NotBlank
	private String name;

	@Email
	@NotBlank
	private String email;

	@NotNull
	@Positive
	private double salary;

	@NotBlank
	private String status;

	@NotNull
	@Positive
	private Long department_id;

	@NotNull
	@Positive
	private Long role;

}
