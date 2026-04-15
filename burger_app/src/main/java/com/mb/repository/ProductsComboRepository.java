package com.mb.repository;

import com.mb.entities.Product;
import com.mb.entities.ProductsCombo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsComboRepository extends JpaRepository<ProductsCombo, Long> {
  List<ProductsCombo> findByProduct(Product p);
}
