<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--common -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>

<title>비밀번호 변경 | Cafe In Me</title>
<!-- nav -->
<link rel="stylesheet" href="${contextPath }/css/include/uppermost.css" />
<script defer src="${contextPath }/js/include/uppermost.js"></script>
<!-- footer -->
<link rel="stylesheet" href="${contextPath }/css/include/footer.css" />

<!-- myPage CSS JS -->
<link rel="stylesheet" href="${contextPath }/css/member/include/mypage_nav.css" />
<script defer src="${contextPath }/js/member/include/myPage_nav.js"></script>

<!-- CSS JS -->
<link rel="stylesheet" href="${contextPath }/css/member/changePWForm.css" />
<script defer src="${contextPath }/js/member/changePWForm.js"></script>

</head>
<body>
<!-- nav -->
<%@ include file="/WEB-INF/views/include/uppermost.jsp"%>

<div class="body">
		
<%@ include file="/WEB-INF/views/member/include/mypage_nav.jsp" %>

  <div class="main">

    <div class="top">
        <div class="title">비밀번호 변경</div>
        <div class="subtitle">비밀번호를 변경합니다.</div>
    </div>
      <hr>
      <br>
			  <form id="changePWForm" action="/cafeinme/member/changePW" method="post">
			  	<input type="hidden" name="member_id" value="${sessionScope.members.member_id }" />
					<ul class="pw_input">	
						
						<li class="current_pw" id="text">
						<label for="currentPw">현재 비밀번호</label>
						<input type="password" name="currentPw" id="currentPw" placeholder="현재 비밀번호" />
						</li>
						
						<li>
							<ul class="pwchk_bind">
							
								<li class="next_pw" id="text">
								<label for="nextPw">새 비밀번호</label>
								<input type="password" name="nextPw" id="nextPw" placeholder="새 비밀번호"/>
								</li>						
								
								<li class="next_pwchk" id="text">
								<label for="nextpwchk">새 비밀번호 확인</label>
								<input type="password" id="nextpwchk" placeholder="새 비밀번호 확인"/></li>
						
							</ul>
						</li>
						
						<li><span class="svr_msg">${svr_msg }</span></li>
						
						<li> 
							<ul class="button_bind">
								<li><button id="confirmBtn">확인</button></li>
								<li><button id="cancelBtn">취소</button></li>
							</ul>
						</li>
						
					</ul>
			  </form>  
  </div>
 </div>
 
 <!-- footer -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>
 
</body>
</html>