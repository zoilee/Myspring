<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty errorMessage}">
	<script>
		alert('${errorMessage}');
	</script>
</c:if>

<h2 class="text-center mt-4 mb-5">ȸ�� �α���</h2>
<form action="./login" method="post">
	<table>
		<tr>
			<td>���̵� : </td>
			<td><input type="text" name="userid" id="userid"></td>
		</tr>
		<tr>
			<td>��й�ȣ : </td>
			<td><input type="password" name="userpass" id="userpass"></td>
		</tr>
		<tr>
			<td colspan="2" class="text-center">
				<input type="submit" value=" �� �� �� " />
			</td>
		</tr>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</table>

</form>