package com.byq.demo.order.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.byq.demo.order.model.Cart;
import com.byq.demo.order.model.Order;
import com.byq.demo.product.model.Product;



public interface CartRepository extends JpaRepository<Cart, String >{


}
