package com.byq.demo.order.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.byq.demo.order.model.Cart;
import com.byq.demo.order.model.CartItem;
import com.byq.demo.order.model.Order;
import com.byq.demo.order.model.OrderItem;
import com.byq.demo.order.repo.CartItemRepository;
import com.byq.demo.order.repo.CartRepository;
import com.byq.demo.order.repo.OrderItemRepository;
import com.byq.demo.order.repo.OrderRepository;
import com.byq.demo.product.repo.ProductRepository;

@Service
public class OrderService {
	@Autowired CartRepository cartRepo;
	@Autowired CartItemRepository carItemRepo;
	@Autowired ProductRepository productrepo;

@Autowired OrderRepository orderRepo;
@Autowired OrderItemRepository orderItemRepo;
	public Order createOrder( Cart cart) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		Order order = new Order();
		String orderId = UUID.randomUUID().toString();
		order.setId(orderId);
		order.setCreateTime(new Date());
		order.setUserId(userId);
		order.setPrice(cart.getTotalPrice());
		orderRepo.save(order);
		for(CartItem i : cart.getItems()) {
			OrderItem oi = extract(i, orderId); 
			
			orderItemRepo.save(oi);
			
		}
		return order;
		
	}

	private OrderItem extract(CartItem item,String orderId) {
		OrderItem rst = new OrderItem();
		String id = UUID.randomUUID().toString();
		rst.setId(id);
		rst.setOrderId(orderId);
		rst.setPrice(item.getProductPrice());
		rst.setProductId(item.getProductId());
		rst.setQuantity(item.getQuantity());
		rst.setProductName(item.getProductName());
		
		return rst;
	}
	public List<Order> myOrders() {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		List<Order> orders = orderRepo.findByUserId(userId);
		for(Order o : orders) {
			List<OrderItem> is = orderItemRepo.findByOrderId(o.getId());
			o.setItems(is);
			
		}
		
		return orders;
		
	}
}
