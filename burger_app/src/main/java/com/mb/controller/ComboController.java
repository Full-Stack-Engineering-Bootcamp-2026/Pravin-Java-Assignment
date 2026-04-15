package com.mb.controller;

import com.mb.dto.ApiResponse;
import com.mb.dto.ComboInDto;
import com.mb.entities.Combo;
import com.mb.service.ComboService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/combos")
@RequiredArgsConstructor
public class ComboController {
  private final ComboService comboService;

  @PostMapping
  public ResponseEntity<ApiResponse<Combo>> createDepartment(
    @RequestBody @Valid ComboInDto dto
  ) {
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(new ApiResponse<Combo>(true, "Combo Saved!", comboService.saveCombo(dto)));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<Combo>>> findDepartments() {
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(new ApiResponse<List<Combo>>(true, null, comboService.findCombos()));
  }
}
