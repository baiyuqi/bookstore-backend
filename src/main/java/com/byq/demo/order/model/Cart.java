package com.byq.demo.order.model;

import java.util.List;

import javax.persistence.*;
@Entity
@Table(name ="CARTX")
public class Cart {
	public List<CartItem> getItems() {
		return items;
	}
	public void setItems(List<CartItem> items) {
		this.items = items;
	}
	@Id
//	@Column(name="USERIDX")
	String userId;
	@Transient
	double totalPrice;
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	@Transient
	List<CartItem> items;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
//API: application programing interface