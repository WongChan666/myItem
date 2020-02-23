package com.qiyuesuo.utils;

import javax.servlet.http.HttpServletRequest;

/*
 *Administrator
 *2020年2月20日
 *
*/
public class Path {
  
    /**
     * @author snow 
     * @description 得到项目的根目录的绝对路径
     */
    public String getPath(HttpServletRequest request){
        String path = request.getSession().getServletContext().getRealPath("/");//表示到项目的根目录下，要是想到目录下的子文件夹，修改"/"即可
        System.out.println(path);
        /*path = path.replaceAll("\\\\", "/");*/
        return path;
    }
}

