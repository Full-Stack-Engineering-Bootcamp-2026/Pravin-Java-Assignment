package com.cdac.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdac.model.Product;

@Repository
public class ProductRepository {

	private List<Product> list = List.of(new Product(1, "name1", 1209, "cate"), new Product(1, "name1", 1209, "cate"),
			new Product(1, "name1", 1209, "cate"));;

	public List<Product> findAll() {

		return list;
	}

	public Product findById(int id) {

		return list.stream().filter(t -> t.getId() == id).toList().getFirst();
	}
}
