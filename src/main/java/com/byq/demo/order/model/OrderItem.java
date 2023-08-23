package com.byq.demo.order.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderItem {
	@Id
	String id;
	String productId;
	String productName;
	double price;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	int quantity;
	String orderId;
	public String getId() {
		return id;
	}
	public String getOrderId() {
		return orderId;
	}
	public String getProductId() {
		return productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
