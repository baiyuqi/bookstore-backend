package com.byq.demo.document.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import cn.hutool.core.util.StrUtil;

@Service
@Primary
public class LocalStorageService implements StorageService {
	@Value("${spring.servlet.multipart.location}")

	private String fileTempPath;

	public String store(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String[] ss = fileName.split("\\.");
		String rawFileName = ss[0];// .subBefore(fileName, ".", true);
		String fileType = ss[1];// StrUtil.subAfter(fileName, ".", true);
		String documentId = rawFileName + "-" + UUID.randomUUID();
		String localFilePath = StrUtil.appendIfMissing(fileTempPath, "/") +documentId + "."
				+ fileType;

		try {
			file.transferTo(new File(localFilePath));
		} catch (Exception e) {
			throw new RuntimeException(e);

		}

		return documentId + "."	+ fileType;
	}

//https://www.cjavapy.com/article/740/
	@Override
	public byte[] read(String documentId) {
		String fullPath = fileTempPath + "/" + documentId;
		try {
			InputStream in = new FileInputStream(fullPath);
			ByteArrayOutputStream out = new ByteArrayOutputStream();// 内存中的输出流
			byte[] buffer = new byte[1024];
			int size = 0;
			while ((size = in.read(buffer)) != -1) {
				out.write(buffer, 0, size);
			}

			in.close();
			return out.toByteArray();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new byte[0];
		}

	}

	@Override
	public boolean supportType(String type) {
		// TODO Auto-generated method stub
		return type.equals("jpeg");
	}

	@Override
	public void read(OutputStream out, String documentId) {
		String fullPath = fileTempPath + "/" + documentId;

		try {
			InputStream in = new FileInputStream(fullPath);
			StreamUtils.copy(in, out);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
