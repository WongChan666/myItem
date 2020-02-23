package com.qiyuesuo.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qiyuesuo.entity.File;
import com.qiyuesuo.service.FileServiceImp;
import com.qiyuesuo.utils.AESTest;
import com.qiyuesuo.utils.DownloadUtil;
import com.qiyuesuo.utils.FileToString;
import com.qiyuesuo.utils.Path;
import com.qiyuesuo.utils.RSACode;

/*
 *Administrator
 *2020年2月23日
 *
*/
@Controller
public class FileDownloadController {

	private Logger logger = Logger.getLogger(FileDownloadController.class);

	@RequestMapping("/getFile")
	public void fileDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 将字符串转换为int形
		int id = Integer.parseInt(request.getParameter("f_id"));
		// 获取文件信息
		File file = new FileServiceImp().getFile(id);
		// 获取原始文件名
		String fileName = file.getF_oldName();

		// 获取加密后的对称密匙并将其解密
		String aesKey = RSACode.decode(file.getF_key());
		// 获取服务器加密后的文件
		java.io.File fil = new java.io.File(file.getF_savePath());
		// 将文件转换为字符串
		String strFile = FileToString.toStringByFile(fil);

		// 将文件用AES进行解密
		String encodeStr = AESTest.decrypt(strFile, aesKey);
		logger.info("文件解密成功");

		String savePath = new Path().getPath(request) + "\\downLoadFile";

		// 将解密后的文件写入服务器，
		String path = DownloadUtil.downloadFileWrite(encodeStr, fileName, savePath);
		logger.info("解密后的文件写入成功");

		// 文件下载
		java.io.File f = new java.io.File(path);
		if (!f.exists()) {
			response.sendError(404, "File not found!");
			return;
		}
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
		byte[] buf = new byte[64];
		int len = 0;

		response.reset(); // 非常重要

		response.setContentType("application/x-msdownload");
		response.setHeader("Content-Disposition", "attachment; filename=" + f.getName());
		OutputStream out = response.getOutputStream();
		while ((len = br.read(buf)) > 0)
			out.write(buf, 0, len);
		br.close();
		out.close();
	}

}
