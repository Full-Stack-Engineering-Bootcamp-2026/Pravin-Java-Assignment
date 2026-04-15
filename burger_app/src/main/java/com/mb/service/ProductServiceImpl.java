package com.mb.service;

import com.mb.dto.ProductInDto;
import com.mb.entities.Product;
import com.mb.enums.CategoryEnum;
import com.mb.enums.TypeEnum;
import com.mb.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepository;

  @Override
  public List<Product> findAllProducts() {
    return productRepository.findAll();
  }

  @Override
  public Product saveProduct(ProductInDto dto) {
    Product product = new Product();

    product.setName(dto.getName());
    product.setCategory(dto.getCategory());

    product.setType(dto.getType());
    product.setPrice(dto.getPrice());

    Product res = productRepository.save(product);
    return res;
  }

  @Override
  public List<Product> filterProducts(String category, String type) {
    if (category != null && type != null) {
      return productRepository.findByCategoryAndType(
        CategoryEnum.valueOf(category),
        TypeEnum.valueOf(type)
      );
    } else if (category != null) {
      return productRepository.findByCategory(CategoryEnum.valueOf(category));
    } else if (type != null) {
      return productRepository.findByType(TypeEnum.valueOf(type));
    }

    return productRepository.findAll();
  }

  @Override
  public List<Product> findProductByName(String name) {
    if (name != null) {
      return productRepository.findByNameIgnoreCaseContaining(name);
    }
    return List.of();
  }
}
