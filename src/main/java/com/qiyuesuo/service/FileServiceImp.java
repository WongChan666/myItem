package com.qiyuesuo.service;

import java.util.List;

import com.qiyuesuo.dao.FileDao;
import com.qiyuesuo.dao.FileDaoImp;
import com.qiyuesuo.entity.File;

/*
 *Administrator
 *2020Äê2ÔÂ21ÈÕ
 *
*/
public class FileServiceImp implements FileService{
	
	private FileDao fileDao = new FileDaoImp(); 

	@Override
	public int addFile(File file) {
		return fileDao.addFile(file);
	}

	@Override
	public List<File> getAllFile() {
		return fileDao.getAllFile();
	}

	@Override
	public File getFile(int f_id) {
		return fileDao.getFile(f_id);
	}

}
