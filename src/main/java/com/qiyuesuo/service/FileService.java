package com.qiyuesuo.service;

import java.util.List;

import com.qiyuesuo.entity.File;

/*
 *Administrator
 *2020��2��21��
 *
*/
public interface FileService {
	
	//�ϴ��ļ�ʱ����Ԫ���ݵĴ洢
	int addFile(File file);
	
	//��ȡ�û��ϴ����ļ�
	List<File> getAllFile();
	
	//��ȡָ���ļ�����
	File getFile(int f_id);

}
