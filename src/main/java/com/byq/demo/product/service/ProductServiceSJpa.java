package com.byq.demo.product.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.byq.demo.product.model.Product;
import com.byq.demo.product.repo.ProductRepository;


@Service
@Primary
public class ProductServiceSJpa  implements ProductService{
	@Autowired ProductRepository repo;
	
	@Transactional
	public Product save( Product product) {
		String id = UUID.randomUUID().toString();
		product.setId(id);
		repo.save(product);
		return product;
	}
	
	public List<Product>  list() {
		
		List<Product> list = repo.findAll();
	
		return list;
	}
	@Transactional
	public boolean  remove(String id) {
		//Product p = entityManager.find(Product.class, id);
		//entityManager.remove(p);
	/*	
		Query query = entityManager.createQuery( "delete from Product where id = ?1").setParameter(1, id);
		int rst = query.executeUpdate();*/
		repo.deleteById(id);
		return true;//rst == 1;
	}
	
	public Product update(@RequestBody Product product) {
		//this.entityManager.merge(product);
		repo.save(product);
		return product;
	}

	@Override
	public List<Product> searchByName(String name) {
		List<Product> rst = repo.findByNameLike(name);
		return rst;
	}

	@Override
	public List<Product> searchByQuantity(int quantity) {
		// TODO Auto-generated method stub
		return repo.findByQuantityGreaterThan(quantity);
	}

	@Override
	public List<Product> searchByQuantityBetween(int q1, int q2) {
		
		return repo.findByQuantityGreaterThanAndQuantityLessThan(q1, q2);
	}

	@Override
	public void storeProductImage(String productId, String documentId) {
		Product product = repo.getById(productId);
		product.setDocumentId(documentId);
		repo.save(product);
		
	}

	@Override
	public Product get(String id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}
}
