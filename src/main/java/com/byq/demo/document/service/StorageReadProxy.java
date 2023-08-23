package com.byq.demo.document.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class StorageReadProxy{
	@Autowired ApplicationContext context;
	public byte[] read(String ducumentId) {
		 Map<String, StorageService> ss = context.getBeansOfType(StorageService.class);
		 byte[] rst = null;
		 for(StorageService  service : ss.values()) {
			 rst = service.read(ducumentId);
			 if(rst != null)
				 return rst;
		 }
		return rst;
	}

}
