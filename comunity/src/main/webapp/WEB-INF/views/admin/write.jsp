<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>   
<link rel="stylesheet" href="/comunity/res/css/summernote-bs5.css" />
<script src="/comunity/res/js/lang/summernote-ko-KR.js"></script>
<script src="/comunity/res/js/summernote-bs5.min.js"></script>
<script>
	$(function(){
		$("#content").summernote({
			lange: 'ko-KR',
			height: 350
			
		});
	})
</script>
<div class="p-5 m-5">
	<form class="row">
		<label class="col-2 text-right py-3 my-3">
			카테고리
		</label>
		<div class="col-10 py-3 my-3">
			<select name="category" id="" class="form-control">
				<option value="">공지</option>
				<option value="">이벤트</option>
				<option value="">긴급</option>
			</select>
		</div>
		<label class="col-2 text-right py-3 my-3">
			제목
		</label>
		<div class="col-10 py-3 my-3">
			<input type="text" class="form-control" name="title" od="title" />
		</div>
		<div class="col-12 py-3 my-3">
			<textarea name="content" id="content"></textarea>
		</div>
		<div class="col-12 text-center py-3 my-3">
			<button type="reset" class="btn btn-danger me=3"> 취 소 </button>
			<button type="submit" class="btn btn-primary ms-3"> 전 송 </button>
		</div>
	</form>
</div>