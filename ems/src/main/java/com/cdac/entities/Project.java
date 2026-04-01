package com.cdac.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "project")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "employees" })
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	private String status;

	@ManyToMany(mappedBy = "projects")
	private List<Employee> employees;

}
