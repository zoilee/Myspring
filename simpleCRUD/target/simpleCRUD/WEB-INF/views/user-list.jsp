<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>사용자 목록</title>
</head>
<body>
    <h2>사용자 목록</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>이름</th>
                <th>이메일</th>
                <th>수정</th>
                <th>삭제</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td><a href="${pageContext.request.contextPath}/users/edit/${user.id}">수정</a></td>
                    <td><a href="${pageContext.request.contextPath}/users/delete/${user.id}">삭제</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br/>
    <a href="${pageContext.request.contextPath}/users/add">사용자 추가</a>
</body>
</html>