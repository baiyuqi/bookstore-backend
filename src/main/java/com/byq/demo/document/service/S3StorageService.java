package com.byq.demo.document.service;

import java.io.File;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.hutool.core.util.StrUtil;

@Service
public class S3StorageService implements StorageService {

	@Override
	public String store(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String[] ss = fileName.split("\\.");
		String rawFileName = ss[0];// .subBefore(fileName, ".", true);
		String fileType = ss[1];// StrUtil.subAfter(fileName, ".", true);
		String localFilePath = StrUtil.appendIfMissing("d:/temp/s3", "/") + rawFileName + "-" + UUID.randomUUID() + "."
				+ fileType;

		try {
			file.transferTo(new File(localFilePath));
		} catch (Exception e) {
			throw new RuntimeException(e);

		}

		return localFilePath;

	}

	@Override
	public byte[] read(String ducumentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supportType(String type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void read(OutputStream out, String ducumentId) {
		
	}


}
