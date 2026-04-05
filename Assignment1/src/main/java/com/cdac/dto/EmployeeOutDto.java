package com.cdac.dto;

import java.util.List;

import lombok.Getter;

@Getter
public class EmployeeOutDto {

	private Long id;

	private String name;

	private String email;

	private double salary;

	private String departmentName;

	private String roleTitle;

	private List<String> projectNames;

}
