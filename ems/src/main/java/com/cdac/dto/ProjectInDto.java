package com.cdac.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ProjectInDto {

	@NotBlank
	private String name;
	@NotBlank
	@Length(min = 30)
	private String description;

	@FutureOrPresent
	private LocalDate startDate;

	@FutureOrPresent
	private LocalDate endDate;
	@NotBlank
	private String status;
}
