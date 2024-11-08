<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>사용자 추가</title>
</head>
<body>
    <h2>사용자 추가</h2>
    <form action="${pageContext.request.contextPath}/users/add" method="post">
        <label for="username">이름:</label>
        <input type="text" id="username" name="username" required><br/>

        <label for="email">이메일:</label>
        <input type="email" id="email" name="email" required><br/>

        <input type="submit" value="추가">
    </form>
    <br/>
    <a href="${pageContext.request.contextPath}/users">목록으로 돌아가기</a>
</body>
</html>