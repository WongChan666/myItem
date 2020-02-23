package com.qiyuesuo.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qiyuesuo.entity.File;
import com.qiyuesuo.service.FileServiceImp;

/*
 *Administrator
 *2020��2��22��
 *
 * �������ڴ�����תҳ���controller�����������ļ���Ϣ��ȡ
*/
@Controller
public class PageController {

	// ��ת���ϴ��ļ���ҳ��
	@RequestMapping("/getHome")
	public String getHomePage(HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		return "home";
	}

	// ��ת�������ļ�ҳ��
	@RequestMapping("/getDownload")
	public String getDownloadPage(HttpServletResponse response) {
		return "download";
	}

	// ajax��ȡԪ������Ϣͨ��json����
	@ResponseBody
	@RequestMapping("/getFileJson")
	public List<File> getFileJson() {
		List<File> files = new FileServiceImp().getAllFile();
		return files;
	}

}
