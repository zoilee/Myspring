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
	<c:if test="${user != null}">
		<h1>${user.title}</h1>
		<p> 아이디 : ${user.id}, 이름 ${user.name }</p>
		<hr/>
		<p>${user.content}</p>
		
		<a href="../edit/${user.id}">수정</a>
		<a href="javascript:void(0)" onclick="del('${user.id}')">삭제</a>
		<script>
			function del(id){
				if(confirm("정말로 삭제하십니까?")){
					location.href="../delete/"+id;
				}
			}
		</script>
	</c:if>
	<c:if test="${user != null}">
		<p>내용이 없습니다.</p>
	</c:if>
</body>
</html>