package com.byq.demo.product.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;

import com.byq.demo.product.model.Product;

public interface ProductService {

	Product save(Product product);

	List<Product> list();

	boolean remove(String id);

	Product update(Product product);

	List<Product> searchByName(String name);

	List<Product> searchByQuantity(int quantity);

	List<Product> searchByQuantityBetween(int q1, int q2);
	
	void storeProductImage(String productId, String documentId);

	Product get(String id);
}