package com.cdac.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeOutDto {

	private Long id;

	private String name;

	private String email;

	private double salary;

	private String departmentName;

	private String roleTitle;

	private List<String> projectNames;

}
