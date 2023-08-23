package com.byq.demo.security.model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Role {
	@Id
	String id;
	String name;
	String descrition;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescrition() {
		return descrition;
	}
	public void setDescrition(String descrition) {
		this.descrition = descrition;
	}
	

}
