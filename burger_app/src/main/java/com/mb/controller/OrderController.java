package com.mb.controller;

import com.mb.dto.ApiResponse;
import com.mb.dto.BillPreviewInDto;
import com.mb.dto.BillPreviewOutDto;
import com.mb.dto.OrderOutDto;
import com.mb.dto.ProductCartInDto;
import com.mb.entities.Order;
import com.mb.service.OrderService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/orders")
public class OrderController {
  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<ApiResponse<OrderOutDto>> createOrder(
    @RequestBody @Valid ProductCartInDto dto
  ) {
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(
        new ApiResponse<OrderOutDto>(true, "Order saved!", orderService.saveOrder(dto))
      );
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<Order>>> getOrderHistory() {
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(new ApiResponse<List<Order>>(true, null, orderService.getOrderHistory()));
  }

  @PostMapping("/preview")
  public ResponseEntity<ApiResponse<BillPreviewOutDto>> previewBill(
    @RequestBody BillPreviewInDto dto
  ) {
    return ResponseEntity.ok(
      new ApiResponse<>(true, null, orderService.previewBill(dto))
    );
  }
}
