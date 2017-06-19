<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${board} VIEW</h1>

	Title: ${dto.title }
	Writer: ${dto.writer }
	Contents: ${dto.contents}
	Hit: ${dto.hit}
	RegDate: ${dto.reg_date}
		
	<a href="./${board}Update?num=${dto.num}" id="update">UPDATE</a>
	<input type="button" id="delete" value="DELETE">
</body>
	<script type="text/javascript">
		$('#delete').click(function() {
			
			var result = confirm("삭제");
			var board = '${board}';
			var num = '${dto.num}';
			
			if(result){
				location.href="./"+board+"Delete?num="+num;
			}
			
		});
	</script>
</html>