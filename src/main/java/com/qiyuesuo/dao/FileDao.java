package com.qiyuesuo.dao;


import java.util.List;

import com.qiyuesuo.entity.File;

/*
 *Administrator
 *2020Äê2ÔÂ21ÈÕ
 *
*/
public interface FileDao {
	
	int addFile(File file);
	
	List<File> getAllFile();

	File getFile(int f_id);

}
