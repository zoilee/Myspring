<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

            <aside class="col-md-3 col-12 list-group bg-white rounded p-4 mt-3">
                <div class="btn-group ">
                    <a href="javascript:void(0)" type="button" class="dropdown-toggle list-group-item list-group-item-action " data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    회원관리
                    </a>
                    <div class="dropdown-menu">
                        <a href="memlist.html" class="dropdown-item">회원관리</a>
                        <a href="#" class="dropdown-item" data-toggle="modal" data-target="#gradeModal" >회원등급관리</a>
                    </div>
                </div>    
                <a href="notice.html" class="list-group-item list-group-item-action">공지사항등록</a>
                <a href="list.html" class="list-group-item list-group-item-action active">커뮤니티관리</a>
                <a href="#" class="list-group-item list-group-item-action">etc</a>
                <a href="logout.html" class="list-group-item list-group-item-action disabled">로그아웃</a>
            </aside>