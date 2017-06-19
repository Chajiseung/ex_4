<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${board}</h1>
	<form action="./${board}${path}" method="post">
		<input type="hidden" name="num" value="${dto.num}"> 
		<p>Title: <input type="text" name="title" value="${dto.title}"></p>
		<p>Writer: <input type="text" name="writer" value="${dto.writer}"></p>
		<p>Contents: <textarea rows="" cols="" name="contents">${dto.contents}</textarea></p>
		<p><button>WRITE</button></p>
</form>
</body>
</html>