<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>사용자 수정</title>
</head>
<body>
    <h2>사용자 수정</h2>
    <form action="${pageContext.request.contextPath}/users/edit/${user.id}" method="post">
        <label for="username">이름:</label>
        <input type="text" id="username" name="username" value="${user.username}" required><br/>

        <label for="email">이메일:</label>
        <input type="email" id="email" name="email" value="${user.email}" required><br/>

        <input type="submit" value="수정">
    </form>
    <br/>
    <a href="${pageContext.request.contextPath}/users">목록으로 돌아가기</a>
</body>
</html>