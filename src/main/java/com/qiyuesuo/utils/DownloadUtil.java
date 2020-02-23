package com.qiyuesuo.utils;
/*
 *Administrator
 *2020��2��23��
 *
*/

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

public class DownloadUtil {
	private static Logger logger = Logger.getLogger(DownloadUtil.class);

	public static String downloadFileWrite(String str, String fileName, String savePath) throws Exception {

		// set the format of derectory by date
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		String sDate = sdFormat.format(new java.util.Date());

		System.out.println(sDate);

		// the directoryName
		String directoryName = savePath + "\\" + sDate + "\\" + fileName;

		File newFile = new File(directoryName);
		if (!newFile.exists()) {
			newFile.getParentFile().mkdirs();// ���������ļ�·��
			newFile.createNewFile();// �����ļ�
			logger.info("�����ļ��гɹ�");
		}
		byte bytes[] = new byte[512];
		bytes = str.getBytes();
		int b = bytes.length; // ���ֽڵĳ��ȣ������ַ����ĳ���
		FileOutputStream fos = new FileOutputStream(newFile);
		fos.write(bytes, 0, b);
		fos.write(bytes);
		logger.info("���ܺ�����д��������ɹ�");
		fos.close();
		
		return directoryName;

	}

}
