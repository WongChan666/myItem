package com.qiyuesuo.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.fileupload.FileItem;

/*
 *Administrator
 *2020��2��21��
 *
*/

//��ȡ�ļ�ת��ΪString����
public class FileToString {
	
	
	public static String toStringByFileItem(FileItem fileItem) throws IOException{
		
		StringBuffer sBuffer = new StringBuffer();
		BufferedInputStream bufferedInputStream = new BufferedInputStream(fileItem.getInputStream());
		
		//һ����ȡ�����ֽ�
        byte[] bytes = new byte[512];
		//���ܶ�ȡ������(n�ʹ����������ݣ�ֻ���������ֵ���ʽ)
        int n = -1;
        //ѭ��ȡ������
        while ((n = bufferedInputStream.read(bytes,0,bytes.length)) != -1) {
            //ת�����ַ���
            String str = new String(bytes,0,n,"UTF-8");
            sBuffer.append(str);
            System.out.println(str);
        }
        //�ر���
        bufferedInputStream.close();
		
        //��sbufferת��Ϊstring����
		return sBuffer.toString();
		 
	}
	
public static String toStringByFile(File file) throws IOException{
		
		StringBuffer sBuffer = new StringBuffer();
		BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
		
		//һ����ȡ�����ֽ�
        byte[] bytes = new byte[512];
		//���ܶ�ȡ������(n�ʹ����������ݣ�ֻ���������ֵ���ʽ)
        int n = -1;
        //ѭ��ȡ������
        while ((n = bufferedInputStream.read(bytes,0,bytes.length)) != -1) {
            //ת�����ַ���
            String str = new String(bytes,0,n,"UTF-8");
            sBuffer.append(str);
            System.out.println(str);
        }
        //�ر���
        bufferedInputStream.close();
		
        //��sbufferת��Ϊstring����
		return sBuffer.toString();
		 
	}

}
