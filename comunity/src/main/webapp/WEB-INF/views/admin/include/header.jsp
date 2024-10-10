<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>   
 <h1>관리자 모드</h1>
 <nav class="d-flex justify-content between">
	 <div>
	 	<a href="admin/mkbbs" class="btn btn-outline-dark mx-2">커뮤니티사항등록성</a>
	 	<a href="admin/mkbbs" class="btn btn-outline-dark mx-2">커뮤니티생성</a>
	 	<a href="admin/mkbbs" class="btn btn-outline-dark mx-2">커뮤니티관리</a>
	 	<a href="admin/mkbbs" class="btn btn-outline-dark mx-2">회원관리</a>
	 </div>	
	 <div>
	 	<a href="/" class="btn btn-outline-dark mx-2" target="_blank">홈페이지로</a>
	 	<a href="#" data-log="logout" class="btn btn-outline-dark mx-2 logout">로그아웃</a>
	 	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrf" />
	 </div>
 	
 </nav>
 <script>
 	$(function(){
 		$(".logout").click(function(e){
 			e.preventDefault();
 			
 			const cname = $("#csrf").attr("name");
 			const cvalue = $("#csrf").val();
 			
 			console.log(cname, cvalue);
 			//폼테그 만들기
 			let form = $("<form></form>");
 			//폼태그안에 input 추가하기
 			form.attr('action', "./logout");
 			form.attr('method', 'post');
 			form.append($("<input type='hidden' name='" + cname + "' value='" + cvalue + "'/>"));
 			form.appendTo('body');
 			form.submit();
 		});
 	});
 </script>	