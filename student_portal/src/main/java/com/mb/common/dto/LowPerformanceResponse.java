package com.mb.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LowPerformanceResponse {
    private String studentName;
    private String enrollmentNumber;
    private Integer semesterNumber;
    private Double percentage;
    private String reason;
}