<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--common -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>

<title>내 계정 | Cafe In Me</title>
<!-- nav -->
<link rel="stylesheet" href="${contextPath }/css/include/uppermost.css" />
<script defer src="${contextPath }/js/include/uppermost.js"></script>
<!-- footer -->
<link rel="stylesheet" href="${contextPath }/css/include/footer.css" />

<!-- myPage CSS JS -->
<link rel="stylesheet" href="${contextPath }/css/member/include/mypage_nav.css" />
<script defer src="${contextPath }/js/member/include/myPage_nav.js"></script>

<!-- CSS JS -->
<link rel="stylesheet" href="${contextPath }/css/member/memberModifyForm.css" />
<script defer src="${contextPath }/js/member/memberModifyForm.js"></script>

</head>
<body>
<!-- nav -->
<%@ include file="/WEB-INF/views/include/uppermost.jsp"%>

<div class="body">
		
<%@ include file="/WEB-INF/views/member/include/mypage_nav.jsp" %>

  <div class="main">

    <div class="top">
        <div class="title">내 계정</div>
        <div class="subtitle">개인 정보를 아래에서 추가 및 편집할 수 있습니다.</div>
    </div>
      <hr>
      <br>
			<form:form id="memberModifyForm"
								 modelAttribute="memberVO" 
								 action="${contextPath }/member/memberModify" 
								 method="post">

              <div class="logininfo">
                <form:input type="text" path="member_id" readonly="true" class="member_id"/>
                <br>
        
                <form:input type="text" class="member_name" path="member_name" readonly="true" />
                <form:input type="text" class="member_birth" path="member_birth" readonly="true" />
              </div>

              <ul>
                <li class="infotoptop">
    
                    <div id="text">
                        <form:label path="member_pw" for="member_pw">비밀번호</form:label> <br>
                        <form:input path="member_pw" type="password" placeholder="현재 비밀번호를 입력 해주세요." /> <br>
                        <form:errors path="member_pw" cssClass="errmsg"/>
                        <span class="errmsg" id="errmsg_member_pw"></span>
                    </div>
                
                </li>
    
                <li class="infotop">
                    
                    <div id="text">
                        <form:label path="member_nickname" for="member_nickname">닉네임</form:label> <br>
                        <form:input type="text" path="member_nickname" id="member_nickname" placeholder="닉네임" />
                        <form:errors path="member_nickname" cssClass="errmsg"/>
                        <span class="errmsg" id="errmsg_member_nickname"></span>
                    </div>
    
                </li>
    
                <li class="infomiddle">
                    
                    <div id="text">
                        <form:label path="member_tel" for="member_tel">전화번호</form:label> <br>
                        <form:input type="tel" path="member_tel" placeholder="ex) 010-0000-0000" />
                        <form:errors path="member_tel" cssClass="errmsg"/>
                        <span class="errmsg" id="errmsg_member_tel"></span>
                    </div>
                </li>
    
                <li class="infobottom">
                      
                    <div id="text">
                    
                   		<div class="address_title">
                        <form:label path="member_address1" for="member_address1">주소</form:label> <br>
                         <div class="search"><button class="search_address">주소 검색</button></div>
                         </div>
                        <div class="address_input">
                        <form:input type="text" path="member_address1" class="address" placeholder="주소" readonly="true"/>
                        <form:input type="text" path="member_address2" class="city" placeholder="시/군/구" readonly="true"/>
                        <form:errors path="member_address1" cssClass="errmsg"/>
                        <span class="errmsg" id="errmsg_member_address1"></span>
                        </div>
                        
                       
                    </div>
          
                    <div><span class="svrmsg">${svr_msg }</span></div>
                </li>
   
              </ul>
   
              <br>

          <input type="submit" value="회원수정" id="modifyBtn" />

   
         
   
   
      </form:form>
  </div>
 </div>
 <!-- footer -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>