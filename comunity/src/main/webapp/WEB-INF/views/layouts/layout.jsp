<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <head>
    <title><tiles:getAsString name="title"/></title>
    <link rel="stylesheet" href="/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/css/style.css" />
    
    <!-- script -->
    <script src="/js/jquery.min.js"></script>
    <script src="/js/popper.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/regex.js"></script>
    <script src="/js/script.js"></script>
    
    <!-- fierbase -->
    <script src="https://www.gstatic.com/firebasejs/8.10.0/firebase-app.js"></script>
    <script src="https://www.gstatic.com/firebasejs/8.10.0/firebase-auth.js"></script>
    <script src="https://www.gstatic.com/firebasejs/8.10.0/firebase-firestore.js"></script>
    <script src="/js/firebase.js"></script>
    <sec:authorize access="!isAuthenticated()">
       <script>
          function sendMessage(){
            alert("로그인 하세요.");
          }
          
       </script>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
       <script>
          const userid = '${member.userid}';
          const username = '${member.username}';

          function sendMessage(){
            const recipientId = document.getElementById('recipient-id').value; 
            const recipientName = document.getElementById('recipient-name').value;
            const messageContent = document.getElementById('message').value;
            if(messageContent.trim() !== '' && recipientId.trim()!=='' && recipientName.trim()!==''){
            	//받는 사람 아이디 유효성 확인
            	$.ajax({
            		url: '/comunity/finduser',
            		type: 'POST',
            		data: {userid: recipientId},
            		success: function(res){
            			if(res.ext){
            				//전송 실행
            				db.collection('messages').add({
            	                  content: messageContent,
            	                  senderId: userid,
            	                  senderName: username,
            	                  recipientId: recipientId,
            	                  recipientName : recipientName,
            	                  timestamp: firebase.firestore.FieldValue.serverTimestamp()
            	                }).then(()=>{
            	                   //입력 창 초기화
            	                   document.getElementById('recipient-id').value = '';
            	                   document.getElementById('recipient-name').value = '';
            	                   document.getElementById('message').value = '';
            	                   alert('메시지가 성공적으로 전송되었습니다.');
            	                }).catch((error)=>{
            	                   console.error('메시지 전송 중 오류가 발생했습니다.', error);
            	                });
            			}else{
            				alert("받는 사람아이디가 유효하지 않습니다.");
            			}
            		},
            		eroor: function(error){
            			alert("받는사람이 아이디를 확인하는 중 오류가 발생했습니다.",);
            	
            		}
            	})
            	
            	
               
            	
            }else{
               alert("모든 필드를 작성해 주세요.");
            }
          }
          
          
          
          
          
          //받은 메시지 목록 업데이트
          db.collection('messages').where('recipientId', '==', userid).onSnapshot((snapshot)=>{
               const messageCount = snapshot.size;
               document.getElementById("message-count").textContent 
                    = messageCount > 0 ? messageCount : '';
            });
          
          //받은 메시지 실시간 조회
          db.collection('messages').where('recipientId', '==', userid).orderBy('timestamp', 'desc')
          	.onSnapshot((snapshot)=>{
          		const messageList = document.getElementById("message-list");
          		if(!messageList) return; // 받은 메시지가 없으면 종료
          		messageList.innerHTML = ''; //기존 메시지 목록 초기화
          		
          		if(snapshot.size === 0){
          			messageList.innerHTML = "<li class='list-group-item'>받은메시지가 없습니다.</li>";
          		}else{
          			let messageHTML = '';
          			snapshot.forEach((doc) =>{
          				const mymessage = doc.data();
          		        messageHTML += "<li class='list-group-item d-flex justify-content-between'>"
          		        			   + "<span style='font-size:14px'>"
          		        	           + mymessage.senderName 
          		        	           + " || "
          		        	           + mymessage.content
          		        	           + "</span>"
          		        	           + "<button class='btn btn-danger' onclick=\"delMessage('"+doc.id+"')\">"
          		        	           + "삭제"
          		        	           + "</button>"
          		        	           + "</li>";
          				//console.log(mymessage);
          				
          			});
          			messageList.innerHTML = messageHTML;
          		}
          	});
          
          
          //보낸메세지 조회
          db.collection('messages').where('senderId', '==', userid).orderBy('timestamp', 'desc')
          	.onSnapshot((snapshot)=>{
          		const messageList = document.getElementById("sent-message-list");
          		if(!messageList) return; // 보낸 메시지가 없으면 종료
          		messageList.innerHTML = ''; //기존 메시지 목록 초기화
          		
          		if(snapshot.size === 0){
          			messageList.innerHTML = "<li class='list-group-item'>보낸메시지가 없습니다.</li>";
          		}else{
          			let messageHTML = '';
          			snapshot.forEach((doc) =>{
          				const mymessage = doc.data();
          		        messageHTML += "<li class='list-group-item d-flex justify-content-between'>"
          		        			   + "<span style='font-size:14px'>"
          		        	           + mymessage.recipientName 
          		        	           + " || "
          		        	           + mymessage.content
          		        	           + "</span>"
          		        	           + "</li>";
          				//console.log(mymessage);
          				
          			});
          			messageList.innerHTML = messageHTML;
          		}
          	});
          
          
          function delMessage(id){
        	  if(confirm("정말로 삭제하시겠습니까?")){
        	  	db.collection('messages').doc(id).delete().then(()=>{
        	  		alert("메시지가 삭제되었습니다.");
        	  	}).catch((error)=>{
        	  		alert("메시지 삭제에 실패했습니다.",error);
        	  	})
        	  }
          }
          
       </script>
    </sec:authorize>
    
  </head>
  <body>
     <div class="container"> 
        <div class="bg-white rounded p-3  shadow-sm">      
           <tiles:insertAttribute name="header" />
        </div>   
        <div class="row align-items-stretch">
           <div class="col-md-3 col-12 my-3">
              <div class="aside bg-white rounded p-3 shadow-sm">
                  <tiles:insertAttribute name="aside" />
              </div>
           </div>
           <div class="col-md-9 col-12">
                <tiles:insertAttribute name="body" />
           </div>
        </div> 
        <div class="bg-white rounded p-3 mt-3  shadow-sm">  
          <tiles:insertAttribute name="footer" />
        </div>  
     </div>  
  </body>
</html>