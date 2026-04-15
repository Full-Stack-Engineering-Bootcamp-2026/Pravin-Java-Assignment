package com.mb.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderOutDto {
  private Long orderId;
  private String userName;
  private String email;
  private Double actualBill;
  private Double optimizedBill;
  private Double savings;
  private List<String> appliedCombos;
  private LocalDateTime timestamp;
}
