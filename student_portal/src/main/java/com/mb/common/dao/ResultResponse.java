package com.mb.common.dao;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultResponse {

  private Long id;
  private Integer semesterNumber;
  private Double percentage;
  private Integer stars;
}