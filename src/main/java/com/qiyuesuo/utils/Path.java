package com.qiyuesuo.utils;

import javax.servlet.http.HttpServletRequest;

/*
 *Administrator
 *2020��2��20��
 *
*/
public class Path {
  
    /**
     * @author snow 
     * @description �õ���Ŀ�ĸ�Ŀ¼�ľ���·��
     */
    public String getPath(HttpServletRequest request){
        String path = request.getSession().getServletContext().getRealPath("/");//��ʾ����Ŀ�ĸ�Ŀ¼�£�Ҫ���뵽Ŀ¼�µ����ļ��У��޸�"/"����
        System.out.println(path);
        /*path = path.replaceAll("\\\\", "/");*/
        return path;
    }
}

