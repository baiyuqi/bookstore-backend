package com.byq.demo.document.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.byq.demo.document.service.StorageService;

import cn.hutool.core.lang.Dict;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 文件上传 Controller
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-11-06 16:33
 */
@RestController
@Slf4j
@RequestMapping("/document")
public class UploadController {
	@Autowired StorageService storageService;


    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Dict local(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return Dict.create().set("code", 400).set("message", "文件内容为空");
		}

		String storedFileId = storageService.store(file);
		return Dict.create().set("code", 200).set("message", "上传成功");
    }
    @GetMapping(value= "/view")
    public void view(HttpServletResponse resp, String documentId) throws IOException {
    	//byte[] data = storageService.read(documentId);
    //	out.write(data);
    	ServletOutputStream out = resp.getOutputStream();
    	resp.setContentType("application/pdf");
    	storageService.read(out, documentId);
    	
    }

}
