package com.byq.demo.product.model;

import java.util.Date;

public class ProductQuery {
	String nameLike;
	int quantityStart;
	int quantityEnd;
	Date onboardTimeStart;
	Date onboardTimeEnd;
	public String jql() {
		return "from Product where ";
	}

}
