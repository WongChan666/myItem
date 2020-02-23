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
 *2020��2��23��
 *
*/
@Controller
public class FileDownloadController {

	private Logger logger = Logger.getLogger(FileDownloadController.class);

	@RequestMapping("/getFile")
	public void fileDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ���ַ���ת��Ϊint��
		int id = Integer.parseInt(request.getParameter("f_id"));
		// ��ȡ�ļ���Ϣ
		File file = new FileServiceImp().getFile(id);
		// ��ȡԭʼ�ļ���
		String fileName = file.getF_oldName();

		// ��ȡ���ܺ�ĶԳ��ܳײ��������
		String aesKey = RSACode.decode(file.getF_key());
		// ��ȡ���������ܺ���ļ�
		java.io.File fil = new java.io.File(file.getF_savePath());
		// ���ļ�ת��Ϊ�ַ���
		String strFile = FileToString.toStringByFile(fil);

		// ���ļ���AES���н���
		String encodeStr = AESTest.decrypt(strFile, aesKey);
		logger.info("�ļ����ܳɹ�");

		String savePath = new Path().getPath(request) + "\\downLoadFile";

		// �����ܺ���ļ�д���������
		String path = DownloadUtil.downloadFileWrite(encodeStr, fileName, savePath);
		logger.info("���ܺ���ļ�д��ɹ�");

		// �ļ�����
		java.io.File f = new java.io.File(path);
		if (!f.exists()) {
			response.sendError(404, "File not found!");
			return;
		}
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
		byte[] buf = new byte[64];
		int len = 0;

		response.reset(); // �ǳ���Ҫ

		response.setContentType("application/x-msdownload");
		response.setHeader("Content-Disposition", "attachment; filename=" + f.getName());
		OutputStream out = response.getOutputStream();
		while ((len = br.read(buf)) > 0)
			out.write(buf, 0, len);
		br.close();
		out.close();
	}

}
