package com.byq.demo.document.service;

import java.io.OutputStream;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
	boolean supportType(String type);
	public String store(MultipartFile file) ;
	public byte[] read(String ducumentId);
	public void read(OutputStream out, String ducumentId);
	
}
