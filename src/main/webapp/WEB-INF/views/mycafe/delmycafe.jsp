<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--common -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>

<title>내 카페 삭제 | Cafe In Me</title>
<!-- nav -->
<link rel="stylesheet" href="${contextPath }/css/include/uppermost.css" />
<script defer src="${contextPath }/js/include/uppermost.js"></script>
<!-- footer -->
<link rel="stylesheet" href="${contextPath }/css/include/footer.css" />


<!-- 카페 네비게이션 -->
<link rel="stylesheet" href="${contextPath }/css/mycafe/include/myCafeNav.css" />
<script defer src="${contextPath }/js/mycafe/include/myCafeNav.js"></script>

<!-- CSS JS -->
<link rel="stylesheet" href="${contextPath }/css/mycafe/delMyCafe.css" />
<script defer src="${contextPath }/js/mycafe/delMyCafe.js"></script>

</head>
<body>
<!-- nav -->
<%@ include file="/WEB-INF/views/include/uppermost.jsp"%>
<!-- 현재위치 -->
  <div class="pageTitle">
    <a href="${contextPath}/cafe/myCafe">내 카페 관리</a> > <span class="now">내 카페 삭제</span>
  </div>

  <section class="main-container">
  
    
  	<!-- 카페 네비게이션 -->
	<%@ include file="/WEB-INF/views/mycafe/include/myCafeNav.jsp"%>
  
    <article class="delCafeArticle">
      <div class="container">
        <h3 class="title">내 카페 삭제</h3>
        <h4 class="description">등록된 카페 정보가 모두 삭제됩니다.</h4>
        <form action="${contextPath }/cafe/delete"
        			method="post"
              class="delForm">
          <input type="text" class="del_bn" name="bn" placeholder="사업자번호 ex)000-00-00000">
          <span class="err_msg_bn"></span>
          <input type="password" class="del_password" name="pw" placeholder="비밀번호">
          <span class="err_msg_password"></span>

          <button class="delBtn" disabled="true">삭제</button>
        </form>
      </div>
    </article>
  </section>
  <!-- footer -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>
  <script>
 console.log(${rctd});
  </script>
</body>
</html>