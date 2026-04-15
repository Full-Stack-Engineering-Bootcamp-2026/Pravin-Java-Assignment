package com.mb.service;

import com.mb.dto.ProductInDto;
import com.mb.entities.Product;
import java.util.List;

public interface ProductService {
  List<Product> findAllProducts();

  Product saveProduct(ProductInDto dto);

  List<Product> filterProducts(String category, String type);

  List<Product> findProductByName(String name);
}
