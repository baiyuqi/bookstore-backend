package com.byq.demo.product.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.byq.demo.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, String >{
	public List<Product> findByNameLike(String name);//select * from Product where name = ...
	public List<Product> findByQuantityGreaterThan(int quantity);
	public List<Product> findByQuantityGreaterThanAndQuantityLessThan(int q1, int q2);
	
// Dynamic Proxy
}
