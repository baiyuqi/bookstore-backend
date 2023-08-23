package com.byq.demo.product.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Producty")
public class Product {
	@Id
	String id;
	@Column(name="NAMEX")
	String name;
	String desc;
	double price;
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	int quantity;
	Date onboardTime;
	String documentId;
	public String getDesc() {
		return desc;
	}

	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Date getOnboardTime() {
		return onboardTime;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setOnboardTime(Date onboardTime) {
		this.onboardTime = onboardTime;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
/*
CREATE TABLE IF NOT EXISTS PRODUCT(
id VARCHAR(255) NOT NULL,
name VARCHAR(255),
desc VARCHAR(255),
PRIMARY KEY (id)
);
*/