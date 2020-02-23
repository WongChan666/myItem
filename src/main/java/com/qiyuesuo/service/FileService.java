package com.qiyuesuo.service;

import java.util.List;

import com.qiyuesuo.entity.File;

/*
 *Administrator
 *2020年2月21日
 *
*/
public interface FileService {
	
	//上传文件时进行元数据的存储
	int addFile(File file);
	
	//获取用户上传的文件
	List<File> getAllFile();
	
	//获取指定文件下载
	File getFile(int f_id);

}
