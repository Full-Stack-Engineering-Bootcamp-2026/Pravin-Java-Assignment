
package com.mb.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SemesterReportResponse {
    private String studentName;
    private String enrollmentNumber;
    private Integer semesterNumber;
    private String studentEmail;
    private Double percentage;
    private Integer rank;
    private Integer stars;
}