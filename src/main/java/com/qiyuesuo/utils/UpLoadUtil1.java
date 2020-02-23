package com.qiyuesuo.utils;

/*
 *Administrator
 *
 *
*/
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import com.qiyuesuo.utils.AESTest;
import com.qiyuesuo.utils.FileToString;
import com.qiyuesuo.utils.RSACode;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.qiyuesuo.service.FileServiceImp;

//upload file
public class UpLoadUtil1 {

	private Logger logger = Logger.getLogger(UpLoadUtil1.class);

	public String upLoadFile(HttpServletRequest request, String savepath) throws IOException {

		logger.info("in upload file");

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);

		request.setCharacterEncoding("utf-8");
		// set the encording
		upload.setHeaderEncoding("utf-8");

		String path = null;

		try {
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem fileItem : list) {
				if (!fileItem.isFormField() && fileItem.getName() != null && !"".equals(fileItem.getName())) {
					String filName = fileItem.getName();
					int fileSize = (int) fileItem.getSize();
					String fileType = filName.substring(filName.lastIndexOf("."));
					SimpleDateFormat sdFormat1 = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
					String f_date = sdFormat1.format(new java.util.Date());

					// 将fileitem对象转换为String字符串
					String fileToString = FileToString.toStringByFileItem(fileItem);

					// 进行aes对称加密 此aesStirng即为aes的对称密匙
					Map<String, String> map = AESTest.getInformation(fileToString);
					String key = map.get("key");
					logger.info("AES加密文件的对称密匙："+key);
					String aesString = map.get("aesString");
					logger.info("AES加密后的文件内容："+aesString);

					// 将对称密匙key进行RSA非对称加密
					String RSAString = RSACode.encode(key);
				    logger.info("REA密匙:"+RSAString);

					
					// 生成获取新文件信息
					// get the uuid
					String uuid = UUID.randomUUID().toString();
					logger.info("uuid:" + uuid);
					// get the suffix of the file
					String suffix = filName.substring(filName.lastIndexOf("."));

					// set the format of derectory by date
					SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
					String sDate = sdFormat.format(new java.util.Date());

					System.out.println(sDate);

					// the directoryName
					String directoryName = savepath + "\\" + sDate + "\\" + uuid + suffix;

					logger.info("save file path:"+directoryName);
					
					//将经过加密的文件写入服务器(本地内存)
					File newFile = new File(directoryName);
					if (!newFile.exists()) {
						newFile.getParentFile().mkdirs();//创建父级文件路径
						newFile.createNewFile();//创建文件
					}
					byte bytes[] = new byte[256];
					bytes = aesString.getBytes();
					int b = bytes.length; // 是字节的长度，不是字符串的长度
					FileOutputStream fos = new FileOutputStream(newFile);
					fos.write(bytes, 0, b);
					/*fos.write(bytes);*/
					logger.info("加密后数据写入服务器成功");
					fos.close();
					
					String filePath = newFile.getAbsolutePath();
					
					//将文件信息封装进入entity
					com.qiyuesuo.entity.File file = new com.qiyuesuo.entity.File(fileSize,fileType,filName,filePath,f_date,RSAString);

					//将文件信息写入数据库
					if(new FileServiceImp().addFile(file)>0){
						logger.info("the file into success");
					}
					
					path=uuid + suffix;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回文件存储路径
		return path;
	}

}
