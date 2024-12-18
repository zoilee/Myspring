<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>글쓰기 폼</h2>
	<form method="post">
		<label for="id">아이디</label>
		<input type="text" name="id" value="${user.id}" readonly/><br><br>
		<label for="name">이름</label>
		<input type="text" name="name" value="${user.name}" /><br><br>
		<label for="title">제목</label>
		<input type="text" name="title" value="${user.title}" /><br><br>
		<label for="content">내용</label>
		<textarea name="content"  cols="60" rows="10">${user.content }</textarea><br><br>
		<button type="submit">글 쓰 기</button>
	</form>
</body>
</html>