<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--common -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>

<title>내 카페 삭제 | Cafe In Me</title>
<!-- nav -->
<link rel="stylesheet" href="${contextPath }/css/include/uppermost.css" />
<script defer src="${contextPath }/js/include/uppermost.js"></script>

<!-- 카페 네비게이션 -->
<link rel="stylesheet" href="${contextPath }/css/mycafe/include/myCafeNav.css" />
<script defer src="${contextPath }/js/mycafe/include/myCafeNav.js"></script>

<!-- CSS JS -->
<link rel="stylesheet" href="${contextPath }/css/mycafe/myshop/delMyShop.css" />
<script defer src="${contextPath }/js/mycafe/myshop/delMyShop.js"></script>

</head>
<body>
<!-- nav -->
<%@ include file="/WEB-INF/views/include/uppermost.jsp"%>
<!-- 현재위치 -->
  <div class="pageTitle">
    <a href="${contextPath}/cafe/myCafe">온라인 판매 관리</a> > <span class="now">온라인 판매 철회</span>
  </div>

  <section class="main-container">
  
    
  	<!-- 카페 네비게이션 -->
	<%@ include file="/WEB-INF/views/mycafe/include/myCafeNav.jsp"%>
  
    <article class="delShopArticle">
      <div class="container">
        <h3 class="title">온라인 판매 철회</h3>
        <h4 class="description">등록된 온라인 판매 정보가 모두 삭제됩니다.</h4>
        <form action="/cafeinme/shop/delete"
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
</body>
</html>