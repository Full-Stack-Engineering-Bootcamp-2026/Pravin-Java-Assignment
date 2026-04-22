package com.cdac.entities;

import com.cdac.enums.EmployeeStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.Table;

import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "employees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = { "projects" })
@NamedEntityGraph(
  name = "Employee.withAll",
  attributeNodes = { @NamedAttributeNode("department"), @NamedAttributeNode("role") }
)
@DynamicUpdate
public class Employee   implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false , length =  50)
  private String name;

  @Column(unique = true, nullable = false ,length = 40)
  private String email;

  @Column(nullable = true,length = 100)
  private String password;

  @Column
  private double salary;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private EmployeeStatus status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "department_id")
  private Department department;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "role_id")
  private Role role;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "employee_project")
  @JsonIgnore
  private List<Project> project;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
     return List.of(  new SimpleGrantedAuthority(role.getTitle()));
  }

  @Override
  public String getUsername() {
    return email;
  }

  
}
