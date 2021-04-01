<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<title>로그인 | Cafe In Me</title>
<!-- footer -->
<link rel="stylesheet" href="${contextPath }/css/include/footer.css" />
<!-- CSS JS -->
<link rel="stylesheet" href="${contextPath }/css/member/loginForm.css" />
<script defer src="${contextPath }/js/member/loginForm.js"></script>
</head>
<body>

  <div class="container">
    
    
    
    <div class="contents">
      <div class="contents_border">
 	  <a href="/cafeinme"><img src="${contextPath }/img/logo_final1.png" alt="회원가입 이미지"></a>
 
      <form action="${contextPath }/login" id="loginForm" method="post">
      
      <div class="body">
      
      	
      
      	<div class="login">
      	
      	<div class="text">
        <div class="item">
          <!-- <label for="member_id">ID </label> -->
          <input type="text" name="member_id" id="member_id" placeholder="ID"/>
        </div>
   
          

        </div>
        
        
       <div class="text">
       
        <div class="item">
          <!-- <label for="member_pw">PW </label> -->
          <input type="password" name="member_pw" id="member_pw" placeholder="Password" />
        </div>
       
          
       
        </div>
        
         
    
        
        </div>
      
         
           
        <div class="item loginBtn">
          <button id="loginBtn">LOGIN</button>
        </div>
       
        
      </div>
      
       <span class="errmsg" id="errmsg_member_id"></span>
        <span class="errmsg" id="errmsg_member_pw"></span>
        <span class="svr_msg" id="svr_smg">${svr_msg }</span>
      
      
        <div class="item find_info">
          <a href="${contextPath }/member/findIDForm" id="findID">아이디 찾기</a> <span>|</span>
          <a href="${contextPath }/member/findPWForm" id="findPW">비밀번호 찾기</a> <span>|</span>
          <a href="${contextPath }/member/memberJoinForm" id="Join">회원 가입</a>
        </div>

     
      
      </form>
    </div>
    
    </div>
  </div>
      <!-- footer -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>