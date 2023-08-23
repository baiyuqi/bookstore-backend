package com.byq.demo.order.model;
//订单

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name ="ORDERX")
public class Order {
	@Id
	String id;
	String userId;
	String status;
	double price;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm")
	Date createTime;
	@Transient
	List<OrderItem> items;
	@Transient
	public String getContent() {
		String content = "";
		if(items == null)
			return content;
		for(OrderItem i : items) {
			content += i.getProductName() + ":" + i.getQuantity() + ";";
		}
		return content;
	}
	
	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public Date getCreateTime() {
		return createTime;
	}
	public String getId() {
		return id;
	}
	public double getPrice() {
		return price;
	}
	public String getUserId() {
		return userId;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	//订单项 = product + 数量
	
	

}
