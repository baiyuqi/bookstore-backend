package com.byq.demo.product.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.byq.demo.document.service.StorageService;
import com.byq.demo.product.model.Product;
import com.byq.demo.product.service.ProductService;

import cn.hutool.core.lang.Dict;

//ORM

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired StorageService storageService;
	
	@Autowired ProductService service;

	@RequestMapping(value = "add", method=RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
	public Product create(@RequestBody Product product) {
		service.save(product);
	
		return product;
	}
	@RequestMapping(value = "list", method=RequestMethod.GET)
	public List<Product>  list() {
		
		List<Product> rst = service.list();
		return rst;
	}
	@RequestMapping(value = "get", method=RequestMethod.GET)
	public Product  get(String id) {
		
		Product rst = service.get(id);
		return rst;
	}
	@RequestMapping(value = "search", method=RequestMethod.GET)
	public List<Product>  search(String name) {
		
		List<Product> rst = service.searchByName("%" + name + "%");
		return rst;
	}
	@RequestMapping(value = "hasquantity", method=RequestMethod.GET)
	public List<Product>  hasQuantity(int quantity) {
		
		List<Product> rst = service.searchByQuantity(quantity);
		return rst;
	}
	@RequestMapping(value = "hasquantitybetween", method=RequestMethod.GET)
	public List<Product>  hasQuantityBetween(int q1, int q2) {
		
		List<Product> rst = service.searchByQuantityBetween(q1, q2);
		return rst;
	}
	@RequestMapping(value = "remove", method=RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
	public boolean  remove(String id) {
		service.remove(id);
		return true;
	}
	@RequestMapping(value = "update", method=RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
	public Product update(@RequestBody Product product) {
		service.update(product);
		return product;
	}
	@PostMapping(value = "/uploadImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@PreAuthorize("hasAuthority('ADMIN_USER')")
	public Dict uploadImage(@RequestParam("file") MultipartFile file, String productId) {
			if (file.isEmpty()) {
				return Dict.create().set("code", 400).set("message", "文件内容为空");
			}
			String storedFileId = storageService.store(file);
			
			this.service.storeProductImage(productId, storedFileId);
			
			return Dict.create().set("code", 200).set("message", "上传成功");
	    }
	    @GetMapping(value= "/view")
	    public void view(HttpServletResponse resp, String documentId) throws IOException {
	    	if(documentId == null || documentId.equals("null")|| documentId.endsWith(".pdf")) {
	    		documentId = "dummy.jpg";
	    	}
	    	//byte[] data = storageService.read(documentId);
	    //	out.write(data);
	    	ServletOutputStream out = resp.getOutputStream();
	    	//resp.setContentType("application/pdf");
	    	storageService.read(out, documentId);
	    	
	    }

}
