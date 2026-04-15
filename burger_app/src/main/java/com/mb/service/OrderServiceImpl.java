package com.mb.service;

import com.mb.dto.BillPreviewInDto;
import com.mb.dto.BillPreviewOutDto;
import com.mb.dto.OrderInDto;
import com.mb.dto.OrderOutDto;
import com.mb.dto.ProductCartInDto;
import com.mb.entities.Combo;
import com.mb.entities.Order;
import com.mb.entities.OrderCombo;
import com.mb.entities.OrdersProduct;
import com.mb.entities.Product;
import com.mb.exception.ResourceNotFound;
import com.mb.repository.ComboRepository;
import com.mb.repository.OrderComboRepository;
import com.mb.repository.OrderProductRepository;
import com.mb.repository.OrderRepository;
import com.mb.repository.ProductRepository;
import com.mb.repository.ProductsComboRepository;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
  private final ProductRepository productRepository;
  private final ProductsComboRepository comboRepository2;
  private final OrderRepository orderRepository;
  private final OrderProductRepository orderProductRepository;

  private final ComboRepository comboRepository;
  private final OrderComboRepository orderComboRepository;

  @Override
  public OrderOutDto saveOrder(ProductCartInDto dto) {
    // we have order , product order , combo products
    //step 1 check if products exists.
    //step 2 check the product  and there combos and check if they exist  in current order payload to detect combos.

    //step 2.2 manage quantity and prices and adjust how much combo or individual product..how to put it in object?

    //step 2.3 calculate actuall bill optimized bil and persist each details.
    //step 3 create current order object with bill how much comobo etc.
    //step 4 send payload to response.

    Map<Long, Integer> qtyMap = dto
      .getOrder()
      .stream()
      .collect(Collectors.toMap(OrderInDto::getId, OrderInDto::getQuantity));

    List<Product> products = dto
      .getOrder()
      .stream()
      .map(
        item ->
          productRepository
            .findById(item.getId())
            .orElseThrow(
              () -> new ResourceNotFound("Product " + item.getId() + " not found")
            )
      )
      .toList();

    Set<Long> orderedIds = qtyMap.keySet();

    List<Combo> applicableCombos = comboRepository
      .findAll()
      .stream()
      .filter(
        combo -> {
          Set<Long> comboProductIds = combo
            .getProduct()
            .stream()
            .map(pc -> pc.getProduct().getId())
            .collect(Collectors.toSet());
          return orderedIds.containsAll(comboProductIds);
        }
      )
      .toList();

    double actualBill = products
      .stream()
      .mapToDouble(p -> p.getPrice() * qtyMap.get(p.getId()))
      .sum();

    double optimizedBill = actualBill;
    for (Combo combo : applicableCombos) {
      optimizedBill -= combo.getActualPrice();
      optimizedBill += combo.getComboPrice();
    }

    Order order = new Order();
    order.setUserName(dto.getUserName());
    order.setEmail(dto.getEmail());
    order.setActuallBil(actualBill);
    order.setOptimizedBill(optimizedBill);
    Order savedOrder = orderRepository.save(order);

    List<OrdersProduct> lineItems = products
      .stream()
      .map(
        p -> {
          OrdersProduct op = new OrdersProduct();
          op.setProduct(p);
          op.setOrder(savedOrder);
          op.setQuantity(qtyMap.get(p.getId()));
          return op;
        }
      )
      .toList();
    orderProductRepository.saveAll(lineItems);

    List<OrderCombo> appliedCombos = applicableCombos
      .stream()
      .map(combo -> new OrderCombo(null, savedOrder, combo))
      .toList();
    orderComboRepository.saveAll(appliedCombos);

    List<String> comboNames = applicableCombos.stream().map(Combo::getComboName).toList();

    OrderOutDto response = new OrderOutDto();
    response.setOrderId(savedOrder.getId());
    response.setUserName(savedOrder.getUserName());
    response.setEmail(savedOrder.getEmail());
    response.setActualBill(actualBill);
    response.setOptimizedBill(optimizedBill);
    response.setSavings(actualBill - optimizedBill);
    response.setAppliedCombos(comboNames);
    response.setTimestamp(savedOrder.getCreatedAt());

    return response;
  }

  public Order calcPrice() {
    return null;
  }

  public List<Order> getOrderHistory() {
    List<Order> orders = orderRepository.findAll();
    return orders;
  }

  @Override
  public BillPreviewOutDto previewBill(BillPreviewInDto dto) {
    Map<Long, Integer> qtyMap = dto
      .getOrder()
      .stream()
      .collect(Collectors.toMap(OrderInDto::getId, OrderInDto::getQuantity));

    List<Product> products = dto
      .getOrder()
      .stream()
      .map(
        item ->
          productRepository
            .findById(item.getId())
            .orElseThrow(
              () -> new ResourceNotFound("Product " + item.getId() + " not found")
            )
      )
      .toList();

    Set<Long> orderedIds = qtyMap.keySet();

    List<Combo> applicableCombos = comboRepository
      .findAll()
      .stream()
      .filter(
        combo -> {
          Set<Long> comboProductIds = combo
            .getProduct()
            .stream()
            .map(pc -> pc.getProduct().getId())
            .collect(Collectors.toSet());
          return orderedIds.containsAll(comboProductIds);
        }
      )
      .toList();

    double actualBill = products
      .stream()
      .mapToDouble(p -> p.getPrice() * qtyMap.get(p.getId()))
      .sum();

    double optimizedBill = actualBill;
    for (Combo combo : applicableCombos) {
      optimizedBill -= combo.getActualPrice();
      optimizedBill += combo.getComboPrice();
    }

    BillPreviewOutDto response = new BillPreviewOutDto();
    response.setActualBill(actualBill);
    response.setOptimizedBill(optimizedBill);
    response.setSavings(actualBill - optimizedBill);
    response.setAppliedCombos(
      applicableCombos.stream().map(Combo::getComboName).toList()
    );

    return response;
  }
}
