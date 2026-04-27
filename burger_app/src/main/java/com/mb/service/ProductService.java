package com.mb.service;

import com.mb.dto.ProductInDto;
import com.mb.entities.Product;
import java.util.List;

import org.springframework.data.domain.Page;

public interface ProductService {

  List<Product> findAllProducts();

  Product saveProduct(ProductInDto dto);

  List<Product> filterProducts(String category, String type);

  List<Product> findProductByName(String name);

  Page<Product> findProductsPaginated( Integer page, Integer size);

  Page<Product> filteredProductPaginated(Integer page, Integer size  , String category, String type   );

}
