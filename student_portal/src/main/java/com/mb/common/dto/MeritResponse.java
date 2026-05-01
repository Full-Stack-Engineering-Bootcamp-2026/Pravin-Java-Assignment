package com.mb.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeritResponse {
    private Integer rank;
    private String studentName;
    private String enrollmentNumber;
    private Double percentage;
    private Integer stars;
}