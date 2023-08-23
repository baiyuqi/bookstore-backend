package com.byq.demo.order.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.byq.demo.order.model.Order;
import com.byq.demo.order.model.OrderItem;
import com.byq.demo.product.model.Product;



public interface OrderItemRepository extends JpaRepository<OrderItem, String >{
	
	public List<OrderItem> findByOrderId(String orderId);//select * from Product where name = ...
}
