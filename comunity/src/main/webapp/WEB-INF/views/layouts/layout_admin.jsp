<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%
   String mode = "list";
   if(request.getParameter("mode")!=null && !request.getParameter("mode").isEmpty()){
	   mode = request.getParameter("mode");
   }
   mode= "/bbs/"+mode+".jsp";
%>    
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자모드</title>
    
    <link rel="stylesheet" href="/comunity/res/css/reset.css">
    <link rel="stylesheet" href="/comunity/res/css/fonts.css">
    <link rel="stylesheet" href="/comunity/res/css/remixicon.css">
    <link rel="stylesheet" href="/comunity/res/css/bootstrap.min.css">
    <link rel="stylesheet" href="/comunity/res/css/style.css">
    <script src="/comunity/res/js/jquery.min.js"></script>
    <script src="/comunity/res/js/popper.min.js"></script>
    <script src="/comunity/res/js/regex.js"></script>
    <script src="/comunity/res/js/bootstrap.min.js"></script>
    <script src="/comunity/res/js/pycs-layout.jquery.js"></script>
    <script src="/comunity/res/js/jquery.validate.min.js"></script>
    <script src="/comunity/res/js/script.js"></script>
</head>
<html>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <div class="bg-white bbs">
  <tiles:insertAttribute name="header" />
  </div>
  <div class="bg-white bbs">
  
  	<title><tiles:getAsString name="title"/></title>
                 
  	<tiles:insertAttribute name="body" />
  </div>

  	<tiles:insertAttribute name="footer" />
  </body>
</html>