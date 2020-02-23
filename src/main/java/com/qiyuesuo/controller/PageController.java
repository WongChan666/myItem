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
 *2020年2月22日
 *
 * 该类用于处理跳转页面的controller和请求下载文件信息获取
*/
@Controller
public class PageController {

	// 跳转到上传文件主页面
	@RequestMapping("/getHome")
	public String getHomePage(HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		return "home";
	}

	// 跳转到下载文件页面
	@RequestMapping("/getDownload")
	public String getDownloadPage(HttpServletResponse response) {
		return "download";
	}

	// ajax获取元数据信息通过json返回
	@ResponseBody
	@RequestMapping("/getFileJson")
	public List<File> getFileJson() {
		List<File> files = new FileServiceImp().getAllFile();
		return files;
	}

}
