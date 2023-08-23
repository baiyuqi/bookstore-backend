package com.byq.demo.order.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.byq.demo.document.service.StorageService;
import com.byq.demo.order.model.Cart;
import com.byq.demo.order.model.CartItem;
import com.byq.demo.order.model.Order;
import com.byq.demo.order.model.OrderItem;
import com.byq.demo.order.repo.OrderItemRepository;
import com.byq.demo.order.repo.OrderRepository;
import com.byq.demo.order.service.OrderService;
import com.byq.demo.product.model.Product;
import com.byq.demo.product.service.ProductService;

import cn.hutool.core.lang.Dict;

//ORM

@RestController
@RequestMapping("/order")
public class OrderController {

@Autowired OrderService service;

	@RequestMapping(value = "add", method=RequestMethod.POST)
    @PreAuthorize("hasAuthority('USER')")
	public Order create(@RequestBody Cart cart) {
		Order order = service.createOrder(cart);
		
		return order;
		
	}

	@GetMapping(value = "myorders")
	@PreAuthorize("hasAuthority('USER')")//ADMIN_USER USER
	public List<Order> myOrders() {
		
		List<Order> orders = service.myOrders();
		
		return orders;
		
	}
}
