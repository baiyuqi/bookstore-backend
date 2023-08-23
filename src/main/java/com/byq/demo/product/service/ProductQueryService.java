package com.byq.demo.product.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.byq.demo.product.model.Product;
import com.byq.demo.product.model.ProductQuery;

public class ProductQueryService {
	@PersistenceContext EntityManager entityManager;
	public List<Product>  query(ProductQuery q) {
		String jql = q.jql();
		Query query = entityManager.createQuery( jql, Product.class);
		List<Product> list = (List<Product>)query.getResultList();
		
		return list;
	}
}
