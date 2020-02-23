package com.qiyuesuo.utils;
/*
 *Administrator
 *2020年2月23日
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
			newFile.getParentFile().mkdirs();// 创建父级文件路径
			newFile.createNewFile();// 创建文件
			logger.info("创建文件夹成功");
		}
		byte bytes[] = new byte[512];
		bytes = str.getBytes();
		int b = bytes.length; // 是字节的长度，不是字符串的长度
		FileOutputStream fos = new FileOutputStream(newFile);
		fos.write(bytes, 0, b);
		fos.write(bytes);
		logger.info("解密后数据写入服务器成功");
		fos.close();
		
		return directoryName;

	}

}
