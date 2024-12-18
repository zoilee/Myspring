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
	<table border=1>
      <thead>
       <tr>
         <th>아이디</th>
         <th>이름</th>
         <th>제목</th>
         <th>내용</th>
       </tr>
      </thead>
      <tbody>
        <c:forEach var="user" items="${users}">
        <!-- 루프 -->
        <tr>
           <td>${user.id }</td>
           <td>${user.name }</td>
           <td><a href="./view/${user.id}">${user.title }</a></td>
           <td>${user.content }</td>
           <td><a href="./delete/${user.id}">삭제하기</a></td>
        </tr>
        
        <!-- /루프 -->
        </c:forEach>
      </tbody>
   </table>
</body>
</html>