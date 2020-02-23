package com.qiyuesuo.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.fileupload.FileItem;

/*
 *Administrator
 *2020年2月21日
 *
*/

//读取文件转换为String类型
public class FileToString {
	
	
	public static String toStringByFileItem(FileItem fileItem) throws IOException{
		
		StringBuffer sBuffer = new StringBuffer();
		BufferedInputStream bufferedInputStream = new BufferedInputStream(fileItem.getInputStream());
		
		//一次性取多少字节
        byte[] bytes = new byte[512];
		//接受读取的内容(n就代表的相关数据，只不过是数字的形式)
        int n = -1;
        //循环取出数据
        while ((n = bufferedInputStream.read(bytes,0,bytes.length)) != -1) {
            //转换成字符串
            String str = new String(bytes,0,n,"UTF-8");
            sBuffer.append(str);
            System.out.println(str);
        }
        //关闭流
        bufferedInputStream.close();
		
        //将sbuffer转换为string返回
		return sBuffer.toString();
		 
	}
	
public static String toStringByFile(File file) throws IOException{
		
		StringBuffer sBuffer = new StringBuffer();
		BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
		
		//一次性取多少字节
        byte[] bytes = new byte[512];
		//接受读取的内容(n就代表的相关数据，只不过是数字的形式)
        int n = -1;
        //循环取出数据
        while ((n = bufferedInputStream.read(bytes,0,bytes.length)) != -1) {
            //转换成字符串
            String str = new String(bytes,0,n,"UTF-8");
            sBuffer.append(str);
            System.out.println(str);
        }
        //关闭流
        bufferedInputStream.close();
		
        //将sbuffer转换为string返回
		return sBuffer.toString();
		 
	}

}
