<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<title><tiles:getAsString name="title"/></title>
<link rel="stylesheet" href="res/css/bootstrap.min.css">
<link rel="stylesheet" href="res/css/style.css">

<script src="res/js/jquery.min.js"></script>
<script src="res/js/popper.min.js"></script>
<script src="res/js/bootstrap.min.js"></script>
<script src="res/js/regex.js"></script>
<script src="res/js/script.js"></script>
</head>
<body>
	<div class="container">
		<tiles:insertAttribute name="header"/>
		<div class="row">
			<tiles:insertAttribute name="aside"/>
			<div class="col-md-9 col-12 mt-3">
				<div class="row">
					<div class="bg-white rounded p-5 w-100 ml-3">
						<tiles:insertAttribute name="body"/>
					</div>
				</div>
			</div>
		</div>
		<tiles:insertAttribute name="footer"/>
	</div>
	
</body>
</html>