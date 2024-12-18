<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>   
<link rel="stylesheet" href="/comunity/res/css/summernote-bs5.css" />

<script src="/comunity/res/js/summernote-bs5.min.js"></script>
<script src="/comunity/res/js/lang/summernote-ko-KR.js"></script> 

<sec:authorize access="isAuthenticated()">
	<c:if test="${adminBbs.rgrade > member.grade}">
	  <script>
	   alert("권한이 없습니다.");
	   location.href="/comunity";
	  </script>  
	</c:if>
</sec:authorize>
<sec:authorize access="!isAuthenticated()">
   <c:if test="${adminBbs.rgrade  > 0}">
 	  <script>
	   alert("회원전용 입니다. 로그인 하세요.");
	   location.href="/comunity";
	  </script>    
   </c:if>
</sec:authorize>
<script>
   let totalFileSize = 0;
   const maxAllFileSize = 50000000;  //나중에 db에서 확인함

   $(function(){
	 let upload= ${adminBbs.fgrade >= 0 ? 'true' : 'false'};  
	 let insertbar = upload
	                    ? ['insert', ['link', 'picture', 'video']]
	                    : ['insert', ['link']];
	 
	 $("#content").summernote({
		 lang: 'ko-KR',
		 height: 350,
		 toolbar: [
			  ['style', ['style']],
			  ['font', ['bold', 'underline', 'clear']],
			  ['fontname', ['fontname']],
			  ['color', ['color']],
			  ['para', ['ul', 'ol', 'paragraph']],
			  ['table', ['table']],
			  insertbar,
			  ['view', ['fullscreen', 'codeview', 'help']],
			],
		 callbacks: {
			 onImageUpload: function(files){
				 uploadFile(files[0], true);
			 },
			 onMediaDelete: function(target){
				const fileUrl = target[0].src;
			    const relativePath = new URL(fileUrl).pathname; //상대경로로 추출
			    console.log(relativePath);
				$(".uploadinbox .upload-file a[href='"+relativePath+"']")
				.closest('.upload-file')
				.hide();
				
				console.log(relativePath);
			 }
		 }
	 });
	 
	 $("#upfile").change(function(){
		 const fileInput = $("#upfile")[0];	 
	     if(fileInput.files.length > 0) {
	    	 uploadFile(fileInput.files[0], true);
	     }
	 });
	 
	 
	 //파일 업로드 함수
	 function uploadFile(file, insertIntoEditor){
		
		 const formData = new FormData();
		 formData.append('file', file);
		 formData.append('bbsid', ${adminBbs.id});
		 
		 $.ajax({
				url: '/comunity/bbs/upload',
				type: 'POST',
				data: formData,
				enctype: 'multipart/form-data',
				processData: false,
				contentType: false,
				beforeSend: function(xhr) {
					xhr.setRequestHeader("${_csrf.headerName}","${_csrf.token}");
				},
				success: function(res){
					console.table(res);
				    if(res.success){
				    	totalFileSize += Number(res.fileSize); 
				    	$("#fileIdField")
				    	     .append('<input type="hidden" name="fileId[]" value="'+res.fileId+'">');

				    	const uploadHtml = '<div class="upload-file">'+ 
						                   '<a href="'+res.fileUrl+'" target="_blank">'+res.orFileName+'</a> '+
						                   '<a href="#" class="delete-file" data-file-id="'+res.fileId+'"><i class="ri-close-large-line"></i></a>'
						                   '</div>';	
						//업로드 박스에 링크와 파일이름 추가
				    	$(".uploadinbox").append(uploadHtml);
						
						//summernote에 이미지 또는 파일 추가
						if(insertIntoEditor){
							if(res.ext.toLowerCase() =='jpg' || res.ext.toLowerCase() == 'png' || res.ext.toLowerCase() == 'gif' || res.ext.toLowerCase()=='svg'){
								$('#content').summernote('insertImage', res.fileUrl);
							}else{
								$('#content').summernote('createLink', {
									text: res.orFileName,
									url: res.fileUrl,
									isNewWindow: true
								});
							}
						}
				    }else{
				    	alert("파일 업로드에 실패했습니다." + res.error);
				    }
									
				},
				error: function(){
					alert("문제가 발생했습니다.");
				}
			});
	    }
	 
	 
	 $(document).on('click', '.delete-file', function(e){
		e.preventDefault();
		//url 가져오기
		const fileUrl = $(this).siblings('a').attr('href');
		
		$("img[src='"+fileUrl+"']").hide();
		$(this).closest('.upload-file').hide();
	 });
	 
  });
</script>
<div class="p-5 my-3 bg-white shadow-sm rounded">

 <form class="row" action="/comunity/bbs/write" method="post">

  <c:if test="${adminBbs.category > 0}">
    <label class="col-2 text-right py-3 my-3">
       카테고리
    </label>
    <div class="col-10  py-2 my-2">
       <select name="category" class="form-control">
          <c:forEach items="${categories }" var="category">
             <option value="${category.categorytext }">${category.categorytext }</option>
          </c:forEach>
       </select>
    </div>
   </c:if>
    
    <!-- 비회원일때 -->
    <sec:authorize access="!isAuthenticated()">
        <label class="col-2 text-right  py-2 my-2">
           이름 
        </label>
        <div class="col-4  py-2 my-2">
          <input type="text" class="form-control" name="writer" id="writer" />
        </div>   
        <label class="col-2 text-right  py-2 my-2">
           비밀번호 
        </label>
        <div class="col-4  py-2 my-2">
          <input type="password" class="form-control" name="password" id="password" />
        </div>  
        <input type="hidden" name="userid" value="guest">
    </sec:authorize>
    <!-- 회원일때 -->
    <sec:authorize access="isAuthenticated()">
      <input type="hidden" name="writer" value="${member.username }">
      <input type="hidden" name="userid" value="${member.userid }">
      <input type="hidden" name="password" value="${member.userid }">
    </sec:authorize>
    <label class="col-2 text-right  py-2 my-2">
       제목
    </label>
    <div class="col-10  py-2 my-2">
       <input type="text" class="form-control" name="title" id="title" />
    </div>   
    <div class="col-12  py-2 my-2">
       <textarea name="content" id="content"></textarea>
    </div>
    
    <c:if test="${adminBbs.fgrade >= 0}">
    <div class="col-12 my-2 py-2">
       <div class="uploadbox">
          <div class="text-right">
             <label for="upfile" class="btn btn-success" id="fileupload">파일업로드</label>
             <input type="file" name="upfile" id="upfile" class="upfile" />
          </div>
          <div class="uploadinbox">
          
          </div>
       </div>
    </div>
    </c:if>
    <div class="col-12 text-right  py-2 my-2">
       <label>
          <input type="checkbox" name="sec" value="1"> 비밀글
       </label>
    </div>
    <div class="col-12 text-center  py-2 my-2">
     
       <input type="hidden" name="bbsAdminId" value="${adminBbs.id }" />
       <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
       <div id="fileIdField"></div>
       <button type="reset" class="btn btn-danger me-3"> 취 소 </button>
       <button type="submit" class="btn btn-primary ms-3"> 전 송 </button>
    </div>
</form>
</div>