package com.byq.demo.order.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.byq.demo.order.model.CartItem;
import com.byq.demo.order.model.Order;
import com.byq.demo.order.model.OrderItem;
import com.byq.demo.product.model.Product;



public interface CartItemRepository extends JpaRepository<CartItem, String >{
	
	public List<CartItem> findByCartId(String orderId);//select * from Product where name = ...
}
