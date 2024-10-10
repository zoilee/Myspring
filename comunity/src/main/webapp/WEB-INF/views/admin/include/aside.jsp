<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>   
<div class="btn-group list-group-item list-group-item-action active">
	<button type="button" class="dropdown-toggle btn btn-default text-white" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		회원관리
	</button>
	<div class="dropdown-memu">
		<a href="#" class="dropdown-item">회원관리</a>
		<a href="#" class="dropdown-item" data-toggle="modal" data-target="gradeModel">회원등급관리</a>
	</div>
	<a href="admim/notice?mode=write" class="list-group-item list-group-item-action">공지사항등록</a>
	<a href="admim/" class="list-group-item list-group-item-action">커뮤니티관리</a>
</div>    

