<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件下载</title>
</head>
<body>
	欢迎来到契约锁2020 年JAVA 校招测试项目。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
	<br>
	<hr>
	这是文件下载页面.....................................................................
	<br>
	<hr>
	<table id="table1" class="table1" border="1" align="center"></table>

</body>
<script type="application/javascript" src="/static/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
$(function(){
	$(document).ready(function() {
		$.ajax({
			url : "getFileJson",
			type : "get",
			contentType : "application/json; charset=utf-8",//如不设置此项，默认也为此，设置发送给后端的类型
			dataType : "json",//设置接收后端的数据的类型
			async : true,//设置异步，不然可能接收不到后端返回的json
			success : function(data) {//data为后端返回的json
				var s ="<tr><th width='6%'>文件id</th><th width='6%'>文件大小(byte)</th><th width='6%'>文件类型</th><th width='12%'>文件名</th><th width='50%'>文件保存服务器目录</th><th width='10%'>上传时间</th><th width='10%'>操作</th></tr>";
				//遍历数据
				$(data).each(function(){
					s+="<tr><td>"+this.f_id+"</td><td>"
					+ this.f_size+ "</td><td>"
					+this.f_type+"</td><td>"
					+this.f_oldName+"</td><td>"
					+ this.f_savePath+"</td><td>"
					+ this.f_date
					+ "</td><td><a href='getFile?f_id="+this.f_id+"'"+">--下载--</a></td></tr>";
				});
				$("#table1").html(s);
			}
		});
	});
});
</script>
</html>