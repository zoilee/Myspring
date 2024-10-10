<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>   
 

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자모드</title>
    
    <link rel="stylesheet" href="res/css/reset.css">
    <link rel="stylesheet" href="res/css/fonts.css">
    <link rel="stylesheet" href="res/css/remixicon.css">
    <link rel="stylesheet" href="res/css/bootstrap.min.css">
    <link rel="stylesheet" href="res/css/style.css">
    <script src="res/js/jquery.min.js"></script>
    <script src="res/js/popper.min.js"></script>
    <script src="res/js/regex.js"></script>
    <script src="res/js/bootstrap.min.js"></script>
    <script src="res/js/pycs-layout.jquery.js"></script>
    <script src="res/js/jquery.validate.min.js"></script>
    <script src="res/js/script.js"></script>
</head>
<body>
    <div class="container position-relative pl-300">
       <header class="position-absolute">
          <div class="logo"><a href="redirect:/"><img src="res/images/logo.png" alt="kdt community"></a></div>
<sec:authorize access="isAuthenticated()">
      <sec:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
      	<p>안녕하세요? ${member.username}회원님 (level: ${member.grade })</p>
      </sec:authorize>
      <sec:authorize access="hasRole('ROLE_ADMIN')">
      	<p>당신은 관리자 입니다.</p>
      </sec:authorize>
      <form action="${pageContext.request.contextPath}/logout" method="post">
      	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
      	<button type="submit">로그아웃</button>
      </form>
</sec:authorize>   
      <sec:authorize access="!isAuthenticated()">
              <form id="loginform" name="loginform" method="post" action="./login">
                  <table>
                      <tr>
                          <td><input type="text" class="form-control" name="userid" id="userid" placeholder="아이디" required /></td>
                      </tr>
                      <tr>
                          <td><input type="password" class="form-control" name="userpass" id="userpass" placeholder="비밀번호" required /></td>
                      </tr>
                      <tr>
                          <td>
                              <div class="text-right">
                                  <label><input type="checkbox" name="rid" id="rid" value="ok" /> 아이디 저장</label>
                              </div>
                          </td>
                      </tr>
                      <tr>
                          <td>
                              <button type="submit" class="btn btn-success btn-block">로그인</button>
                          </td>
                      </tr>
                  </table>
                  <div class="d-flex justify-content-between px-2 mb-5">
                      <a href="findidpass.jsp">아이디/비밀번호 찾기</a>
                      <a href="./register">회원가입</a>
                  </div>
                  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
              </form>
          </sec:authorize>
          <div>이미지배너</div>
       </header>