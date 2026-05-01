package com.mb.common.service;

import java.util.List;

import com.mb.common.dao.ResultResponse;
import com.mb.common.dto.LowPerformanceResponse;
import com.mb.common.dto.MeritResponse;
import com.mb.common.dto.ResultInDto;
import com.mb.common.dto.SemesterReportResponse;

public interface SemesterService {

    ResultResponse addResult(Long studentId, ResultInDto dto);

    ResultResponse updateResult(Long resultId, ResultInDto dto);

    List<MeritResponse> getMeritList(Integer semester);

    List<SemesterReportResponse> getSemesterReport(Integer semester);

    List<ResultResponse> findResultOfStudent(String auth0Id);

    List<LowPerformanceResponse> getLowPerformanceReport();



}