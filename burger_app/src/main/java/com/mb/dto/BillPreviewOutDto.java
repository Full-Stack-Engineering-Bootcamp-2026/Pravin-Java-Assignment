package com.mb.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillPreviewOutDto {
  private Double actualBill;
  private Double optimizedBill;
  private Double savings;
  private List<String> appliedCombos;
}
