package com.mb.common.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "students")
@Getter
@Setter
@ToString
@DynamicUpdate
public class Student extends BaseEntity {

  @Column(name = "auth0_id", unique = true, nullable = false)
  private String auth0Id;

  @Column(nullable = false)
  private String name;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(name = "enrollment_number")
  private String enrollmentNumber;

  @ManyToOne
  @JoinColumn(name = "role_id")
  private Role role;

  @Column(name = "is_active")
  private Boolean isActive = true;

}