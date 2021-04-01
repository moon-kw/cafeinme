<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/common.jsp"%>  
<title>아이디 찾기 | Cafe In Me</title>

<script src="${contextPath }/js/common/ajax.js"></script>

<!-- nav -->
<link rel="stylesheet" href="${contextPath }/css/include/uppermost.css" />
<script defer src="${contextPath }/js/include/uppermost.js"></script>
<!-- footer -->
<link rel="stylesheet" href="${contextPath }/css/include/footer.css" />

<!-- CSS JS -->
<script defer src="${contextPath }/js/member/findIDForm.js"></script>
<link rel="stylesheet" href="${contextPath }/css/member/findIDForm.css" />
</head>
<body>
    <div class="container">
        <section id="section">
        
        <div class="section_border">
        
         <a href="/cafeinme"><img src="${contextPath }/img/logo_final1.png" alt="회원가입 이미지"></a>
        
            <h3>아이디 찾기</h3>
                <ul class="findId_list">
                	 <li>
                        <div class="findedId"></div>
                    </li>
                
                    <li class="findId_list name">
                        <input type="text" name="member_name" id="member_name" placeholder="이름">
                    </li>
                    <li class="findId_list tel">
                        <input type="tel" name="member_tel" id="member_tel" placeholder="전화번호">
                    </li>
                    <li class="findId_list submit">
                        <button id="findIDBtn" name="findIDBtn">아이디 찾기</button>
                    </li>
                    <li class="submenu">
                    	<a href="${contextPath }/loginForm">로그인</a> |
                        <a href="${contextPath }/member/findPWForm">비밀번호 찾기</a> |
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