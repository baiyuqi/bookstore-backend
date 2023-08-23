package com.byq.demo.order.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import com.byq.demo.order.repo.CartItemRepository;
import com.byq.demo.order.repo.CartRepository;
import com.byq.demo.product.model.Product;
import com.byq.demo.product.repo.ProductRepository;
import com.byq.demo.product.service.ProductService;

import cn.hutool.core.lang.Dict;

//ORM

@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired CartRepository repo;
	@Autowired CartItemRepository irepo;
	@Autowired ProductRepository prepo;
	@GetMapping(value = "view")
    @PreAuthorize("hasAuthority('USER')")//ADMIN_USER USER
	public Cart view() {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		Cart cart = repo.findById(userId).get();
		List<CartItem> is = irepo.findByCartId(userId);
		
		cart.setItems(is);
		return cart;
		
	}
	
	@PostMapping(value = "addItem")
 //   @PreAuthorize("hasAuthority('USER')")//ADMIN_USER USER
	public CartItem addItem(@RequestBody CartItem item) {
	
		
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		String  id= UUID.randomUUID().toString();
		item.setId(id);
		item.setCartId(userId);
		Product pro = prepo.findById(item.getProductId()).get();
		item.setQuantity(1);
		item.setProductName(pro.getName());
		item.setProductPrice(pro.getPrice());
		irepo.save(item);
		
		return item;
		
	}
	@PostMapping(value = "removeItem")
    @PreAuthorize("hasAuthority('USER')")//ADMIN_USER USER
	public String removeItem(String itemId) {
		irepo.deleteById(itemId);
		return "success";
		
	}
	@PostMapping(value = "compute-price")
    @PreAuthorize("hasAuthority('USER')")//ADMIN_USER USER
	public PriceResult computePrice(@RequestBody Cart cart) {
		PriceResult rst = new PriceResult();
		rst.message ="折扣很大！";
		double total = 0;
		for(CartItem i : cart.getItems()) {
			total += i.getProductPrice() * i.getQuantity();
			//i.productId, 又有逻辑
		}
		//新客户老客户 String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		rst.totalPrice = total * 0.7;
		
		return rst;
		
	}
	class PriceResult{
		public String message;
		public double totalPrice;
	}
	
}
