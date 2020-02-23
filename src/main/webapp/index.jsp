<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="#" />
<title>首页</title>
</head>
<body>
	欢迎来到契约锁2020 年JAVA 校招测试项目。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
	<br>
	<hr>
	<a href="getHome">点击请求到上传文件页面</a>||||||||<a href="getDownload">点击请求到下载文件页面</a>
</body>


<script type="application/javascript" src="/static/jquery-1.11.0.min.js"></script>
<script type="application/javascript">
	
    $("#fil").change(function(){
    	alert("进入ajax");
    	var fileData = new FormData();
    	fileData.append('file',$('#fil')[0].files[0]);
    	fileData.append('sizeid',123);
    	var url = "uploadFile";
    	
    	$.ajax({
		     type : "POST", //提交方式 
		     url : url,//路径 
		     data : fileData,
		     processData: false, // 告诉jQuery不要去处理发送的数据
		     contentType: false, // 告诉jQuery不要去设置Content-Type请求头
		     success:function(d){
		    	 $(d).each(function(){
		    		 alert("成功上传！");
		    		 document.getElementById("filePath").value = this.path;
					 alert(this.path);
				  });
		     },
			  dataType:"json"
		    }); 
    	
    })




</script>
</html>