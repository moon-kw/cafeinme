<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--common -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>

<title>회원탈퇴 | Cafe In Me</title>
<!-- nav -->
<link rel="stylesheet" href="${contextPath }/css/include/uppermost.css" />
<script defer src="${contextPath }/js/include/uppermost.js"></script>
<!-- footer -->
<link rel="stylesheet" href="${contextPath }/css/include/footer.css" />

<!-- myPage CSS JS -->
<link rel="stylesheet" href="${contextPath }/css/member/include/mypage_nav.css" />
<script defer src="${contextPath }/js/member/include/myPage_nav.js"></script>

<!-- CSS JS -->
<link rel="stylesheet" href="${contextPath }/css/member/delMemberForm.css" />
<script defer src="${contextPath }/js/member/delMemberForm.js"></script>

</head>
<body>
<!-- nav -->
<%@ include file="/WEB-INF/views/include/uppermost.jsp"%>

<div class="body">

<%@ include file="/WEB-INF/views/member/include/mypage_nav.jsp" %>

  <section class="main-container">
  
  
   		 <div class="top">
	    <div class="title">회원 탈퇴</div>
	    <div class="subtitle">내 계정을 삭제합니다.</div>
	    </div>
	    <hr>
    <article class="delArticle">
      <div class="container">
      
      
        <form action="${contextPath }/member/delMember"
              method="post"
              id="delMemberForm">
          <ul>
            <li class="descriptionBox">
              저장된 북마크와 구매내역이 모두 사라집니다.<br>
              계속하시겠습니까?
            </li>
            <li class="passwordBox">
              <input type="hidden" name="member_id" value="${sessionScope.members.member_id}">
              <input type="password" class="password_input" name="member_pw" id="member_pw" placeholder="Password">
              <span class="svr_msg">${svr_msg }</span>
            </li>
            <li class="btnBox">
              <button id="submitBtn" disabled="true">탈퇴</button>
              <button id="cancelBtn">취소</button>
            </li>
          </ul>
        </form>
      </div>
    </article>
  </section>
  </div>
  <!-- footer -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>