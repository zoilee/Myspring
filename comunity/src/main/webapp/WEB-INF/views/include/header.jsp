<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>    
<div class="row">
   <div class='col-md-1'>
      <h1 class="mx-4"><a href="/comunity"><i class="ri-bar-chart-horizontal-line"></i></a></h1>
   </div>
   <div class="col-md-6">
   		<ul class="nav mt-3 justify-content-between">
   			<c:forEach var="list" items="${bbsAdminLists}">
   				<sec:authorize access="isAuthenticated()">
   					<c:choose>
   						<c:when test="${list.lgrade > member.grade}">
   							<li class="nav-item">
				   				<a href="javascript:void(0)" onClick="alert('권한이 없습니다.')">${list.bbstitle}</a>
				   			</li>
   						</c:when>
   						<c:otherwise>
   							<li class="nav-item">
				   				<a href="/comunity/bbs/list?bbsid=${list.id }">${list.bbstitle}</a>
				   			</li>
   						</c:otherwise>
   					</c:choose>
   				</sec:authorize>
   				<sec:authorize access="!isAuthenticated()">
   					<c:choose>
   						<c:when test="${list.lgrade > 0}">
   							<li class="nav-item">
				   				<a href="javascript:void(0)" onClick="alert('회원제입니다 로그인해주세요')">${list.bbstitle}</a>
				   			</li>
   						</c:when>
   						<c:otherwise>
   							<li class="nav-item">
				   				<a href="/comunity/bbs/list?bbsid=${list.id }">${list.bbstitle}</a>
				   			</li>
   						</c:otherwise>
   					</c:choose>
   				</sec:authorize>
	   			
   			</c:forEach>
   		</ul>
   </div>
   <div class="col-md-2">
   	<a href="javascript:void(0)" class="message-icon" data-toggle="modal" data-target="#messageModal">
   		<i class="ri-chat-1-fill"></i>
   		<span id="message-count" class="badge badge-danger">1</span>
   	</a>
   </div>
   <form class="form-group col-md-3" action="/comunity/bbs/search" method="get">
		<div class="input-group mt-3 mb-3">
		  <input type="text" class="form-control" name="searchVal" placeholder="통합검색...">
		  <div class="input-group-append">
            <button class="btn btn-primary" type="submit">OK</button>
          </div>
		</div>
   </form>
</div>

<!--  message midal -->
<div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-labelledby="messageModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">KDT 커뮤니티 메시지 함</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body">
				<div class="mt-3">
					<form action="" class="d-flex py-2 px-3 flex-wrap">
						<div class="col-3 mb-3">아이디</div>
						<div class="col-9 mb-3">
							<input type="text" id="recipient-id" class="form-control" placeholder="받는 사람 아이디" />
						</div>
						<div class="col-3 mb-3">이름</div>
						<div class="col-9 mb-3">
							<input type="text" id="recipient-name" class="form-control" placeholder="받는 사람 이름"/>
						</div>
						<div class="col-12 mb-3">
							<textarea name="" id="message"  rows="3" class="form-control" placeholder="메시지"></textarea>
						</div>
						<div class="col-12 text-center mb-3">
							<button type="button" class="btn btn-primary" onclick="sendMessage()">전송</button>
						</div>
					</form>
					
					<!-- 보낸 메시지 목록  -->
					<div class="mt-4">
						<h6>보낸 메시지 목록</h6>
						<ul class="list-group" id="sent-message-list">
						</ul>
					</div>
					
					<!-- 받은 메시지 목록 -->
					<div class="mt-4">
						<h6>받은 메시지 목록</h6>
						<ul class="list-group" id="message-list">
						</ul>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>