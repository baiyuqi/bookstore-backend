package com.byq.demo.order.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.byq.demo.order.model.Order;
import com.byq.demo.product.model.Product;



public interface OrderRepository extends JpaRepository<Order, String >{
	public List<Order> findByCreateTimeGreaterThan(Date t);//select * from Product where name = ...

	public List<Order> findByUserId(String userId);

	
// Dynamic Proxy
}
