package com.qiyuesuo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qiyuesuo.utils.Path;
import com.qiyuesuo.utils.UpLoadUtil1;


/*
 *Administrator
 *
 *
*/
@Controller
public class FileUploadController {
	
	private Logger logger = Logger.getLogger(FileUploadController.class);
	
	@ResponseBody
	@RequestMapping("/uploadFile")
	public String uploadFile(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		/*
		// 
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		*/
		logger.info("into controller");
		
		//the save file path
		String saveFile = new Path().getPath(request)+"\\file";
		
		String filePath = new UpLoadUtil1().upLoadFile(request,saveFile);
	
		return "<p style='color: red'>上传成功</p>the file is:"+filePath+"<br><hr><a href='getHome'>返回继续上传</a>|||||<a href='getDownload'>去下载已上传文件</a>";
		
		/*Map<String, String> path = new HashMap<>();
		path.put("path", filePath);
		JSONObject m = JSONObject.fromObject(path);
		response.getWriter().println(m);
		*/
		
	}
	

}
