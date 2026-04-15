package com.mb.repository;

import com.mb.entities.OrdersProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends JpaRepository<OrdersProduct, Long> {}
