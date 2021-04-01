<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--common -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>

<title>내 카페 온라인 판매 | Cafe In Me</title>
<!-- nav -->
<link rel="stylesheet" href="${contextPath }/css/include/uppermost.css" />
<script defer src="${contextPath }/js/include/uppermost.js"></script>

<!-- 카페 네비게이션 -->
<link rel="stylesheet" href="${contextPath }/css/mycafe/include/myCafeNav.css" />
<script defer src="${contextPath }/js/mycafe/include/myCafeNav.js"></script>

<link rel="stylesheet" href="${contextPath }/css/mycafe/myshop/myShopJoinForm.css" />
<script defer src="${contextPath }/js/mycafe/myshop/myShopJoinForm.js"></script>
</head>
<body>
<!-- nav -->
<%@ include file="/WEB-INF/views/include/uppermost.jsp"%>
	<!-- 현재위치 -->
	<div class="pageTitle">
	   <a href="${contextPath}/cafe/myCafe">온라인 판매 관리</a> > <span class="now"> 내 카페 온라인 판매 등록 </span>
	</div>

	<section class="main-container">
		<%@ include file="/WEB-INF/views/mycafe/include/myCafeNav.jsp"%>
		
			  <article id="sjArticle">
			      <div class="container">
			        <h3 class="title">내 카페 온라인 판매 등록</h3>
			        <form action="${contextPath }/shop/myShopjoin" id="myShopJoinForm" method="post">
			          <div class="img-group">
			            <div class="sjimg img_1"></div>
			            <div class="sjimg img_2"></div>
			            <div class="sjimg img_3"></div>
			          </div>
			          <c:if test="${isonline }" >
			          <button class="joinBtn active">온라인 판매중</button>
			          </c:if>
			          <c:if test="${!isonline }">
			          <button class="joinBtn">온라인 판매 등록</button>
			          </c:if>
			        </form>
			      </div>
			  </article>
	</section>
</body>
</html>