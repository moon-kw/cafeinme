<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--common -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>

<title>내 카페 | Cafe In Me</title>
<!-- nav -->
<link rel="stylesheet" href="${contextPath }/css/include/uppermost.css" />
<script defer src="${contextPath }/js/include/uppermost.js"></script>
<!-- footer -->
<link rel="stylesheet" href="${contextPath }/css/include/footer.css" />

<!-- 카페 네비게이션 -->
<link rel="stylesheet" href="${contextPath }/css/mycafe/include/myCafeNav.css" />
<script defer src="${contextPath }/js/mycafe/include/myCafeNav.js"></script>

<link rel="stylesheet" href="/cafeinme/css/mycafe/mycafemodifyform.css" />
<script defer src="/cafeinme/js/mycafe/mycafemodifyform.js"></script>
</head>
<body>
<!-- nav -->
<%@ include file="/WEB-INF/views/include/uppermost.jsp"%>
<!-- 현재위치 -->
  <div class="pageTitle">
    <a href="${contextPath}/cafe/myCafe">내 카페 관리</a> > <span class="now">내 카페 보기</span>
  </div>
  
	<ul>
		<form action="">
			<li><input type="text" id="cafe_name"
				value="${cafevo.cafe_name}" readonly />
			<li><input type="text" id="cafe_address1"
				value="${cafevo.cafe_address1}" readonly />
			<li><input type="text" id="cafe_address2"
				value="${cafevo.cafe_address2}" readonly />


				<div class="container">
					<c:if test="${!empty cafevo.cfilevo}">
						<c:forEach var="rec" items="${cafevo.cfilevo }">
							<div class="cafeimg">
								<img name=${rec.cfile_no }
									src="/cafeinme/img/${rec.cfile_path }" alt="" width=150
									height=150 />
								<button class="imgbts">X</button>
							</div>
						</c:forEach>
					</c:if>
				</div> <input type="file" id="hiddenfile" />
	</ul>
	</form>
	<form action="http://localhost:9080/cafeinme/cafe/delmycafe"
		method="post">

		<button id="cafedelbts">삭제</button>
	</form>
	<!-- footer -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>