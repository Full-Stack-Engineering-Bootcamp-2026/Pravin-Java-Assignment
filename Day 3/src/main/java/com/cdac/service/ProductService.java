package com.cdac.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cdac.model.Product;
import com.cdac.repository.ProductRepository;

@Service
public class ProductService {

	private ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<Product> getProducts() {

		return productRepository.findAll();
	}

	public Product getProduct(int id) {

		return productRepository.findById(id);
	}
}
