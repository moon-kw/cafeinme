<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<title>비밀번호 찾기 | Cafe In Me</title>
<script defer src="${contextPath }/js/common/ajax.js"></script>
<!-- nav -->
<link rel="stylesheet" href="${contextPath }/css/include/uppermost.css" />
<script defer src="${contextPath }/js/include/uppermost.js"></script>
<!-- footer -->
<link rel="stylesheet" href="${contextPath }/css/include/footer.css" />
<!-- CSS JS -->
<link rel="stylesheet" href="${contextPath }/css/member/findPWForm.css" />
<script defer type="module" src="${contextPath }/js/member/findPWForm.js"></script>
</head>
<body>
	<div class="container">
		<section id="section">
			
			<div class="section_border">
			
			 <a href="/cafeinme"><img src="${contextPath }/img/logo_final1.png" alt="회원가입 이미지"></a>
		
			<h3>비밀번호 찾기</h3>
			<ul class="findPw_list">
				<li>
					<div class="findedPw"></div>
				</li>
				<li class="findPw_list id"><input type="text" name="member_id"
					id="member_id" placeholder="아이디) aaa@bbb.com">
				</li>
				<li class="findPw_list tel"><input type="tel" name="member_tel"
					id="member_tel" placeholder="전화번호 ex)010-1234-5678">
				</li>
<!-- 				<li class="findPw_list submit">
					<button id="findPwBtn" name="findPwBtn">임시 비밀번호 발급</button>
				</li> -->

				<button id="findPwBtn" class="btn" type="button">
					<span class="visually-hidden spinner-border spinner-border-sm"
						role="status" aria-hidden="true"></span> 임시 비밀번호 발급
				</button>
				
				<li class="submenu"><a href="${contextPath }/loginForm">로그인</a> |
				<a href="${contextPath }/member/findIDForm">아이디 찾기</a> | 
					<a href="${contextPath }/member/memberJoinForm">회원가입</a>
				</li>
			</ul>
			</div>
		</section>
	</div>
	    <!-- footer -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>