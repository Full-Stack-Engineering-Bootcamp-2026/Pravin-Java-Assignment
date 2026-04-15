package com.mb.service;

import com.mb.dto.BillPreviewInDto;
import com.mb.dto.BillPreviewOutDto;
import com.mb.dto.OrderOutDto;
import com.mb.dto.ProductCartInDto;
import com.mb.entities.Order;
import java.util.List;

public interface OrderService {
  OrderOutDto saveOrder(ProductCartInDto dto);

  Order calcPrice();

  List<Order> getOrderHistory();
  BillPreviewOutDto previewBill(BillPreviewInDto dto);
}
