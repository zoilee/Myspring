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
<html>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <tiles:insertAttribute name="header" />
  <div class="bg-white bbs">
  	<title><tiles:getAsString name="title"/></title>
 
<!-- <tiles:insertAttribute name="aside" /> -->                  
  	<tiles:insertAttribute name="body" />
  </div>

  	<tiles:insertAttribute name="footer" />
  </body>
</html>