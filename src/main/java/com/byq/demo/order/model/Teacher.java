package com.byq.demo.order.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Teacher {
	@Id
	String id;
	String name;
	String email;
	String title;

}
