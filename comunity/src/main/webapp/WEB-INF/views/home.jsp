<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<script src="res/js/photo-gallery.js"></script>
<c:if test="${not empty error }">
  <script>
    alert("${error}");
    history.go(-1);
  </script>
</c:if>
<c:if test="${param.logout != null}">
	<script>
    	alert("로그아웃되었습니다.");
    </script>
</c:if>
<c:if test="${not empty memberok }">
  <script>
   alert("정상적으로 가입되었습니다. 로그인하세요.");
  </script> 
</c:if>
<div class="row">
          <div class="col-6">
            <div class="comu-box">
               <h2>Our Gallery</h2>
               <div class="photos-gallery clearfix">
                <!--loop -->
                   <div class="picture">
                      <a href="#"><img src="res/images/upload/001.jpg" alt="001">
                         <div class="txt-box">제목1입니다.제목4입니다.</div>
                      </a>
                   </div>
                   <div class="picture">
                      <a href="#"><img src="res/images/upload/002.jpg" alt="002">
                        <div class="txt-box">제목2입니다.제목4입니다.</div>
                      </a>
                    </div>
                   <div class="picture">
                      <a href="#"><img src="res/images/upload/003.jpg" alt="003">
                        <div class="txt-box">제목3입니다.제목4입니다.</div>
                      </a>
                   </div>
                   <div class="picture">
                      <a href="#"><img src="res/images/upload/004.jpg" alt="004">
                        <div class="txt-box">제목4입니다.제목4입니다.</div>
                      </a>
                   </div>
                 <!-- /loop -->  
               </div> 
            </div>
          </div>
          <div class="col-6">
            <div class="comu-box">
               <h2>Notice</h2>
   
               <ul class="noticeul">
                  <li>
                     <a href="#">
                        <span class="notice notice1">공지</span> 공지사항 게시판 입니다.
                     </a>
                     <span class="ndate">2024.07.31</span>
                  </li>     
                  <li>
                     <a href="#">
                        <span class="notice notice2">알림</span> 공지사항 게시판 입니다.
                     </a>
                     <span class="ndate">2024.07.31</span>
                  </li>  
                  <li>
                     <a href="#">
                        <span class="notice notice3">이벤트</span> 공지사항 게시판 입니다.
                     </a>
                     <span class="ndate">2024.07.31</span>
                  </li>  
                  <li>
                     <a href="#">
                        <span class="notice notice1">공지</span> 공지사항 게시판 입니다.
                     </a>
                     <span class="ndate">2024.07.31</span>
                  </li>  
                  <li>
                     <a href="#">
                        <span class="notice notice4">추천</span> 공지사항 게시판 입니다.
                     </a>
                     <span class="ndate">2024.07.31</span>
                  </li>  
                  <li>
                     <a href="#">
                        <span class="notice notice1">공지</span> 공지사항 게시판 입니다.
                     </a>
                     <span class="ndate">2024.07.31</span>
                  </li>  
                  <li>
                     <a href="#">
                        <span class="notice notice1">공지</span> 공지사항 게시판 입니다. 공지사항 게시판 입니다.
                        공지사항 게시판 입니다.
                        공지사항 게시판 입니다.
                     </a>
                     <span class="ndate">2024.07.31</span>
                  </li>  
                  <li>
                     <a href="#">
                        <span class="notice notice1">공지</span> 공지사항 게시판 입니다.
                     </a>
                     <span class="ndate">2024.07.31</span>
                  </li>               
               </ul>

            </div>
          </div>
          <div class="col-6">
            <div class="comu-box">
               <h2>Blog</h2>
               <ul class="blogul">
                  <li>
                     <a href="#" class="blogs">
                        <div class="thumb">
                           <img src="res/images/upload/006.webp" alt="006" />
                        </div>
                        <div class="blog-text">
                           <h3>기사의 제목을 넣음</h3>
                           <p>정당의 설립은 자유이며, 복수정당제는 보장된다. 법률안에 이의가 있을 때에는 대통령은 제1항의 기간내에 이의서를 붙여 국회로 환부하고, 그 재의를 요구할 수 있다. 국회의 폐회중에도 또한 같다.</p>
                           <p>재의의 요구가 있을 때에는 국회는 재의에 붙이고, 재적의원과반수의 출석과 출석의원 3분의 2 이상의 찬성으로 전과 같은 의결을 하면 그 법률안은 법률로서 확정된다.</p>
                        </div>
                     </a> 
                  </li>
                  <li>
                     <a href="#" class="blogs">
                        <div class="thumb">
                           <img src="res/images/upload/006.webp" alt="006" />
                        </div>
                        <div class="blog-text">
                           <h3>기사의 제목을 넣음</h3>
                           <p>정당의 설립은 자유이며, 복수정당제는 보장된다. 법률안에 이의가 있을 때에는 대통령은 제1항의 기간내에 이의서를 붙여 국회로 환부하고, 그 재의를 요구할 수 있다. 국회의 폐회중에도 또한 같다.</p>
                           <p>재의의 요구가 있을 때에는 국회는 재의에 붙이고, 재적의원과반수의 출석과 출석의원 3분의 2 이상의 찬성으로 전과 같은 의결을 하면 그 법률안은 법률로서 확정된다.</p>
                        </div>
                     </a> 
                  </li>
                  <li>
                     <a href="#" class="blogs">
                        <div class="thumb">
                           <img src="res/images/upload/006.webp" alt="006" />
                        </div>
                        <div class="blog-text">
                           <h3>기사의 제목을 넣음</h3>
                           <p>정당의 설립은 자유이며, 복수정당제는 보장된다. 법률안에 이의가 있을 때에는 대통령은 제1항의 기간내에 이의서를 붙여 국회로 환부하고, 그 재의를 요구할 수 있다. 국회의 폐회중에도 또한 같다.</p>
                           <p>재의의 요구가 있을 때에는 국회는 재의에 붙이고, 재적의원과반수의 출석과 출석의원 3분의 2 이상의 찬성으로 전과 같은 의결을 하면 그 법률안은 법률로서 확정된다.</p>
                        </div>
                     </a> 
                  </li>
               </ul>
            </div>
          </div>
          <div class="col-6">
            <div class="comu-box">
               <h2><a href="bbs.jsp">Community</a></h2>
               <ul class="noticeul">
               	<c:forEach var="list" items="${bbsList}">
                  <li>
                     <a href="bbs.jsp?mode=view&num=${list.num}">
                        ${list.title }
                     </a>
                     <span class="ndate">${list.viewDate}</span>
                  </li>     
               	</c:forEach>
                  
               </ul>
            </div>
        </div>
     </div>
