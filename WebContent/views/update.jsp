<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form id="a" action="update.board" method="post">
			<label for="title">제목</label>
			<input type="text" name="title" id="title" value="${board.title}"> <br>
			
			<textarea rows="5" cols="30" id="content" name="content" placeholder="변경된 약관을 확인하세요.">
			 ${board.title}</textarea> <br><br>
			 
			<input type="hidden" name="no" value="${board.no}" >
			
			
		</form>
	</div>


</body>
</html>