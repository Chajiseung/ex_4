<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>FreeBoard View</h1>
	<table>
		<tr>
			<td>num</td>
			<td>title</td>
			<td>writer</td>
			<td>date</td>
			<td>hit</td>
		</tr>
		<tr>
			<td>${dto.num}</td>
			<td>${dto.title}</td>
			<td>${dto.writer}</td>
			<td>${dto.reg_date}</td>
			<td>${dto.hit}</td>
		</tr>
		<tr>
			<td colspan="5">${dto.contents}</td>
		</tr>
	</table>
	<a href="freeBoardUpdate?num=${dto.num}">FreeBoardUpdate</a>
	<a href="freeBoardDelete?num=${dto.num}">FreeBoardDelete</a>
</body>
</html>