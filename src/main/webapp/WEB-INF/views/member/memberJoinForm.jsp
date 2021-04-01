<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--common -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>

<title>회원가입 | Cafe In Me</title>
<!-- nav -->
<link rel="stylesheet" href="${contextPath }/css/include/uppermost.css" />
<script defer src="${contextPath }/js/include/uppermost.js"></script>
<!-- footer -->
<link rel="stylesheet" href="${contextPath }/css/include/footer.css" />
<!-- CSS JS -->
<link rel="stylesheet" href="${contextPath }/css/member/memberJoinForm.css" />
<script defer src="${contextPath }/js/member/memberJoinForm.js"></script>
</head>
<body>
<!-- nav -->


  <section id="joinSection">
  
 	
  
		<div class="container">
		
		<div class="top_arrow">
			
			<a href="${contextPath }/loginForm"><img src="${contextPath }/img/arrow.png"> </a>
			
		</div>
		
		 <a href="/cafeinme" class="imgBox"><img src="${contextPath }/img/logo_final1.png" alt="회원가입 이미지"></a>
		 
			
			<form:form id="joinForm"
								 modelAttribute="memberVO" 
								 action="${contextPath }/member/memberJoin" 
								 method="post">
				<ul class="memberJoinList">
					<li><h3 class="title">회원 가입</h3></li>

					<li class="form-group">
						<form:label path="member_id">아이디</form:label>
						<form:input type="text" path="member_id" class="form-group-input" placeholder="aaa@bbb.com"/>
						<form:errors path="member_id" class="svr_msg" />
						<span id="errmsg_member_id" class="svr_msg"></span>
						<button class="dupBtn">중복 확인</button>
					</li>

					<li class="form-group">
						<form:label path="member_pw">비밀번호</form:label>
						<form:input type="password" path="member_pw"  class="form-group-input"/>
						<form:errors path="member_pw" class="svr_msg"/>
						<span id="errmsg_member_pw" class="svr_msg"></span>
					</li>
					
					<li class="form-group">
						<label for="pwchk">비밀번호 확인</label>
						<input type="password" id="pwchk" class="form-group-input">
						<span id="errmsg_pwchk" class="svr_msg"></span>
					</li>

					<li class="form-group">
						<form:label path="member_name">이름</form:label>
						<form:input type="text" path="member_name"  class="form-group-input"/>
						<form:errors path="member_name" class="svr_msg" />
						<span id="errmsg_member_name" class="svr_msg"></span>
					</li>

					<li class="form-group">
						<form:label path="member_birth">생년월일</form:label>
						<form:input type="date" path="member_birth"  class="form-group-input"/>
						<form:errors path="member_birth" class="svr_msg"  />
						<span id="errmsg_member_birth" class="svr_msg"></span>
					</li>

					<li class="form-group">
						<form:label path="member_nickname">닉네임</form:label>
						<form:input type="text" path="member_nickname"  class="form-group-input"/>
						<form:errors path="member_nickname" class="svr_msg"  />
						<span id="errmsg_member_nickname" class="svr_msg"></span>
					</li>

					<li class="form-group">
						<div class="address_bind">
						<form:label path="member_address1">주소</form:label>
						<button class="search_address">주소 검색</button>
						</div>
						<form:input type="text" path="member_address1"  class="form-group-input" readonly="true"/>
						<form:errors path="member_address1" class="svr_msg" />
						<span id="errmsg_member_address1" class="svr_msg"></span>
					</li>

					<li class="form-group">
						<form:label path="member_address2">상세주소</form:label>
						<form:input type="text" path="member_address2"  class="form-group-input" readonly="true"/>
						<form:errors path="member_address2" class="svr_msg" />
					</li>
					
				
					<li class="form-group">
						<form:label path="member_tel">전화번호</form:label>
						<form:input type="tel" path="member_tel" class="form-group-input" maxlength="13"/>
						<form:errors path="member_tel" class="svr_msg"  />
						<span id="errmsg_member_tel" class="svr_msg"></span>
					</li>
					<li><input type="submit" value="SIGN UP" id="joinBtn"></li>
				</ul>
			</form:form>
		</div>
	</section>
	
	      <!-- footer -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>
	
</body>
</html>