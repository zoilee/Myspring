<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
  $(function(){
    ${script} 
    
    $("button[id^='edit']").click(function(){
    	const tr = $(this).closest("tr");
    	const id = tr.find("input[name='id']").val();
    	const bbstitle = tr.find("input[name='bbstitle']").val();
    	const skin = tr.find("select[name='skin']").val();
    	const category = tr.find("select[name='category']").val();
    	const listcount = tr.find("input[name='listcount']").val();
    	const pagecount = tr.find("input[name='pagecount']").val();
    	const lgrade = tr.find("select[name='lgrade']").val();
    	const rgrade = tr.find("select[name='rgrade']").val();
    	const fgrade = tr.find("select[name='fgrade']").val();
    	const regrade = tr.find("select[name='regrade']").val();
    	const comgrade = tr.find("select[name='comgrade']").val();
    	
    	const data = {
			id,
			bbstitle,
			skin,
			category,
			listcount,
			pagecount,
			lgrade,
			rgrade,
			fgrade,
			regrade,
			comgrade    			
		}
    	
    	console.dir(data);
    	
    	
    	$.ajax({
    		url: "/comunity/admin/editBbsAdmin",
    		method: "post",
    		data: {
    			id,
    			bbstitle,
    			skin,
    			category,
    			listcount,
    			pagecount,
    			lgrade,
    			rgrade,
    			fgrade,
    			regrade,
    			comgrade,
    			${_csrf.parameterName} : "${_csrf.token}"
    		},
    		success: function(res){
    			if(res == 1){
    				alert("수정되었습니다.");
    				location.reload(true);  // 페이지 새로고침
    			}else{
    				alert("문제가 발생했습니다. 다시 시도해 주세요.");
    				location.reload(true);
    			}
    		},
    		error: function(xhr, status, error){
    			alert("문제가 발생했습니다. 다시 시도해 주세요." + error);
    		}
    	});
    	

    });
    
    $( ".sortable" ).sortable();
    
    $(".addCate").click(function(){
    	const form = $(this).closest("form");
    	const bbsid = form.find('input[name="bbsid"]').val();
    	const categorytext = form.find('input[name="categorytext"]').val();
    	const data = {
    			bbsid: bbsid,
    			categorytext: categorytext
    	}
    	alert(bbsid + "," + categorytext);
    	$.ajax({
    		url: "/comunity/admin/addCategory?${_csrf.parameterName}=${_csrf.token}",
    		data: JSON.stringify(data),
    		contentType: 'application/json;charset=UTF=8',
    		success: function(res){
    			if(res){
    				alert("카테고리가 등록 되었습니다,");
    			}else{
    				alert("문제가 발생했습니다.");
    			}
    		},
    		error: function(error){
    			alert("문제가 발생했다.")
    		}
    	});
    	
    });

    $(".delCate").click(function(){
    	
    });
    
  });
</script>
<h1 class="text-center mb-4">관리자 모드</h1>
<table class="table table-striped table-hover comtable">
  <thead>
     <tr>
        <th>번호</th>
        <th>커뮤니티이름</th>
        <th>스킨</th>
        <th>카테고리</th>
        <th>목록크기</th>
        <th>페이지크기</th>
        <th>보기권한</th>
        <th>쓰기권한</th>
        <th>파일</th>
        <th>답글</th>
        <th>코멘트</th>
        <th></th>
     </tr>   
  </thead>
  <tbody>
     <c:forEach var="list" items="${lists }">
        <tr>
           <td>${list.id }
           		<input type="hidden" name="id" class="id" value="${list.id}"/>
           </td>
           <td><input type="text" name="bbstitle" class="bbstitle" value="${list.bbstitle}" /></td>
           <td>
               <select name="skin" id="skin${list.id }">
                  <option value="bbs">게시판형</option>
                  <option value="gallery">갤러리형</option>
                  <option value="article">기사형</option>
                  <option value="blog">블로그형</option>
               </select>
           </td>
           <td>
              <select name="category" id="category${list.id}">
                 <option value="0">사용안함</option>
                 <option value="1">사용함</option>
              </select>
              <c:if test="${list.category ==1}">
              	<br />
              	<a href="javascript:void(0)" data-toggle="modal" data-target="#cate-${list.id}">카테고리 관리</a>
              	<div class="modal" id="cate-${list.id}">
              		<div class="modal-content" style="padding:30px;">              		
              			<div class="modal-body">
              				<form action="">
	              				<input type="hidden" name="bbsid" value="${list.id}"/>
	              				<div class="input-group mb-3">
	              					<input type="text" name="categorytext" class="form-control" placeholder="카테고리이름" style="max-width:100%;" />
	              					<div class="input-group-append">
	              						<button class="btn btn-danger" type="button">Cancel</button>
	              						<button class="btn btn-primary addCate" type="button">카테고리등록</button>
	              					</div>
	              				</div>
              				</form>
              				<form action="">
              					<input type="hidden" name="bbsid" value="${list.id}" />
              					<ul class="sortable">
              					<c:if test="${not empty list.bbsCategory}">
              					<c:forEach var="categoryList" items='${list.bbsCategory}'>
								  <li class="ui-state-default d-flex">
								  	<div class="col-1">
								  		${categoryList.categorynum}
								  	</div>
								  	<div class="col-8">
								  		<input type="text" name="categorytext[]" value="${categoryList.categorytext} " class="form-control categorylist"/>
								  		<input type="hidden" name="bbsid[]" value="${categoryList.bbsid}" />
								  		<input type="hidden" name="id[]" value="${categoryList.id}" />
								  	</div>
								  	<div class="col-4">
								  		<button type="button" class="categoryEdit btn btn-warning editCate">수정</button>
								  		<button type="button" class="categoryDelete btn btn-danger delCate">삭제</button>
								  	</div>
								  </li>
								</c:forEach>
								</c:if>
								</ul>								
              				</form>
              			</div>
              			
              			<div class="modal-footer">
              				<button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
              			</div>
              		</div>
              	</div>
              </c:if>
           </td>
           <td>
              <input type="text" name="listcount" value="${list.listcount }" />
           </td>  
           <td>
              <input type="text" name="pagecount" value="${list.pagecount}" />
          </td>
           
           <td>
              <select name="lgrade" id="lgrade${list.id }">
                 <option value="0">허용</option>
                 <option value="1">회원(1)</option>
                 <option value="2">회원(2)</option>
                 <option value="3">회원(3)</option>
                 <option value="99">관리자</option>
              </select>
           </td>
           <td>         
              <select name="rgrade" id="rgrade${list.id }">
                 <option value="0">허용</option>
                 <option value="1">회원(1)</option>
                 <option value="2">회원(2)</option>
                 <option value="3">회원(3)</option>
                 <option value="99">관리자</option>
              </select>
         
           </td>
           <td> 
              <select name="fgrade" id="fgrade${list.id }">
                 <option value="-1">비허용</option>
                 <option value="0">허용</option>
                 <option value="1">회원(1)</option>
                 <option value="2">회원(2)</option>
                 <option value="3">회원(3)</option>
                 <option value="99">관리자</option>
              </select></td>
           <td>
              <select name="regrade" id="regrade${list.id }">
                 <option value="-1">비허용</option>
                 <option value="0">허용</option>
                 <option value="1">회원(1)</option>
                 <option value="2">회원(2)</option>
                 <option value="3">회원(3)</option>
                 <option value="99">관리자</option>
              </select>
           </td>
           <td>
              <select name="comgrade" id="comgrade${list.id }">
                 <option value="-1">비허용</option>
                 <option value="0">허용</option>
                 <option value="1">회원(1)</option>
                 <option value="2">회원(2)</option>
                 <option value="3">회원(3)</option>
                 <option value="99">관리자</option>
              </select>
           </td>    
           <td>
             <button type="button" id="edit${list.id }" class="btn btn-warning btn-sm mr-2">수정</button>
             <button type="button" id="delete${list.id }" class="btn btn-danger btn-sm mr-2">삭제</button>
           </td>       
        </tr>
     </c:forEach>
  </tbody>
</table>
