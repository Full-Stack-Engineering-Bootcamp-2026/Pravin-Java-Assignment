package com.mb.repository;

import com.mb.entities.Product;
import com.mb.enums.CategoryEnum;
import com.mb.enums.TypeEnum;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
  List<Product> findByCategoryOrType(CategoryEnum category, TypeEnum type);

  List<Product> findByCategoryAndType(CategoryEnum category, TypeEnum type);

  List<Product> findByType(TypeEnum type);

  List<Product> findByCategory(CategoryEnum category);

  List<Product> findByNameIgnoreCaseContaining(String name);
}
