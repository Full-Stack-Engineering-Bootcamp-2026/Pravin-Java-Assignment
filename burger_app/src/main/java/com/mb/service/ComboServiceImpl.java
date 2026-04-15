package com.mb.service;

import com.mb.dto.ComboInDto;
import com.mb.entities.Combo;
import com.mb.entities.Product;
import com.mb.entities.ProductsCombo;
import com.mb.exception.ResourceNotFound;
import com.mb.repository.ComboRepository;
import com.mb.repository.ProductRepository;
import com.mb.repository.ProductsComboRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ComboServiceImpl implements ComboService {
  private final ComboRepository comboRepository;
  private final ProductRepository productRepository;

  private final ProductsComboRepository comboRepository2;

  @Override
  public Combo saveCombo(ComboInDto dto) {
    Combo combo = new Combo();
    combo.setActualPrice(dto.getActualPrice());
    combo.setComboName(dto.getComboName());
    combo.setComboPrice(dto.getComboPrice());
    System.out.println(dto.getProducts().size());

    List<Product> existedProducts = dto
      .getProducts()
      .stream()
      .map(
        product -> {
          return productRepository
            .findById(product)
            .orElseThrow(() -> new ResourceNotFound("Order " + product + "doesn't exit"));
        }
      )
      .toList();

    Combo res = comboRepository.save(combo);

    List<ProductsCombo> combos = new ArrayList<ProductsCombo>();

    for (Product p : existedProducts) {
      ProductsCombo productsCombo = new ProductsCombo();
      productsCombo.setCombo(combo);

      productsCombo.setProduct(p);
      combos.add(comboRepository2.save(productsCombo));
    }
    System.out.println(combos.toString());

    comboRepository2.saveAll(combos);

    return res;
  }

  @Override
  public List<Combo> findCombos() {
    return comboRepository.findAll();
  }
}
