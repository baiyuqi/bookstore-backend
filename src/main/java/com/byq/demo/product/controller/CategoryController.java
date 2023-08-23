package com.byq.demo.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
	@GetMapping("list")
	public String list() {
		return null;
	}

}
