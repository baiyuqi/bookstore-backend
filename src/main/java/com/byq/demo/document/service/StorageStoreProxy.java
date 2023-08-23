package com.byq.demo.document.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.multipart.MultipartFile;

public class StorageStoreProxy{
	@Autowired ApplicationContext context;
	public String store(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String[] sss = fileName.split("\\.");
		String fileType = sss[1];// StrUtil.subAfter(fileName, ".", true);
		 Map<String, StorageService> ss = context.getBeansOfType(StorageService.class);
		
		 for(StorageService  service : ss.values()) {
			 if(service.supportType(fileType)){
				 return service.store(file);
			 }
			
		 }
		 return null;

	}

}
