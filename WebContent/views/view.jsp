<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
	
</head>
<body>

	<div> 
		 <!-- 게시글  -->
		 게시글 번호 : ${board.no} <br>  
		 작성자 : ${board.author} <br>
		 조회수 : ${board.hit}<br>
		 IP : ${board.ip} <br>
		 작성일 : ${board.poseDate}<br>
		 제목 : ${board.title}<br>
		 내용 : <br>
		 <pre>${board.content}</pre>
	     
	     <br>
	     <!--   
	     	 목록 : 누구나
	     	 수정, 삭제 : 작성자만
	      -->
	     
      	 <input type="button" value="목록보기" onclick="location.href='list.board'">
	     <c:if test="${notice.writer == loginUser.id}"> 
	     	<input type="button" value="삭제하기" onclick="fnBoardDelete()">
	     </c:if>
	    
	     	
	     <script>
	     	function fnNoticeDelete() {
	     		if (comfrim('게시글을 삭제할까요?')) {
	     			location.href = 'delete.board?nNo=${board.nNo}'
	     		}
	     	}
	     </script>	
	     	
	     <hr>
	     
	     <!--  댓글 달기..!~ -->
	     <!-- 
	     	 작성자 : 로그인 유저 아이디
	     	 댓글달기 : 로그인 유저만 오픈
	      -->
	     <form action="insert.reply" method="post"> 
	     	<textarea rows="5" cols="30" name="content"></textarea><br>
	     	<label for="author">작성자</label>
	     	<input name="author" id="author" value="${loginUser.id}"><br>
	     	<input type="hidden" name="no" value="${board.no}">
	     	<c:if test="${loginUser != null } ">
	     		<button>작성</button>	
	     	</c:if>	
	     </form>
	     
	     <hr>
	 
	 	
	
	
</body>
</html>