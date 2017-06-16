<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>NOTICEVIEW</h2>
	
	<h3>WRITER: ${dto.writer}</h3>
	<h3>TITLE: ${dto.title}</h3>
	<h3>Contents : ${dto.contents}</h3>
	
	
	<a href="./noticeUpdate?num=${dto.num}">noticeUpdate</a>
	<a href="./noticeDelete?num=${dto.num}">noticeDelete</a>

</body>
</html>