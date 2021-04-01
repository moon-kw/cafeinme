<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--common -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>

<title>온라인 판매 메뉴 관리 | Cafe In Me</title>
<!-- nav -->
<link rel="stylesheet" href="${contextPath }/css/include/uppermost.css" />
<script defer src="${contextPath }/js/include/uppermost.js"></script>
<!-- footer -->
<link rel="stylesheet" href="${contextPath }/css/include/footer.css" />


<!-- 카페 네비게이션 -->
<link rel="stylesheet" href="${contextPath }/css/mycafe/include/myCafeNav.css" />
<script defer src="${contextPath }/js/mycafe/include/myCafeNav.js"></script>

<!-- CSS JS -->
<script defer src="${contextPath }/js/mycafe/onlinemenuForm.js"></script>
<link rel="stylesheet" href="${contextPath }/css/mycafe/myshop/onlinemenuForm.css" />


</head>
<body>
<!-- nav -->
<%@ include file="/WEB-INF/views/include/uppermost.jsp"%>
<!-- 현재위치 -->
  <div class="pageTitle">
    <a href="${contextPath}/cafe/myCafe">온라인 판매 관리</a> > <span class="now">온라인 카페 메뉴 관리</span>
  </div>

  <section class="main-container">
  
  	<!-- 카페 네비게이션 -->
	<%@ include file="/WEB-INF/views/mycafe/include/myCafeNav.jsp"%>
	
    
    <article class="saleArticle">
      <div class="container">
	      <h3 class="title">온라인 카페 메뉴 관리</h3>
	      <div class="shopMenuJoinForm">
	        <h4 class="description">온라인 메뉴를 등록 할 수 있습니다.</h4>
	          <div class="table">
	            <table class="menuJoin_table">
	              <thead>
	                <tr class="menuJoin_head">
	                  <th>카테고리</th>
	                  <th>메뉴 이름</th>
	                  <th>메뉴 가격</th>
	                  <th>메뉴 설명</th>
	                  <th>판매상태</th>
	                  <th>메뉴이미지</th>
	                </tr>
	              </thead>
	              <tbody class="myTable">
	                <c:if test="${menuvo!=null}">
	              <c:forEach var="rec" items="${menuvo }" varStatus="status">
	              <tr>
	                  <td>
	                    <select class="form-select form-select-sm" aria-label=".form-select-sm example" name="menulist[${status.count-1}].menu_category">
	                      <option selected value="${rec.menu_category}">${rec.category_name}</option>
	                    </select>
	                  </td>
	                  <td>
	                  <input type="text" class="form-input" value="${rec.menu_name}" readonly></td>
	                  <td><input type="text" class="form-input" value="${rec.menu_price }" readonly></td>
	                  <td><input type="text" class="form-input" value="${rec.menu_content }" readonly></td>
	                  <c:if test="${rec.menu_onlinesale==0 }"><!-- 온라인판매 상태X -->
	                  <td><input type="button" class="form-input onlinebts" readonly value="판매대기" data-menu_no="${rec.menu_no }"></td>
	                  </c:if>
	                  <c:if test="${rec.menu_onlinesale==1 }"><!-- 온라인판매중인 상태O -->
	                  <td><input type="button" class="form-input onlinebts active" readonly value="판매중" data-menu_no="${rec.menu_no }"></td>
	                  </c:if>
	                  <td>
	                    <div class="form-pic">
	                    <c:if test="${rec.mfile_path!=null }">
	                    <img src="${contextPath }/img${rec.mfile_path}" onerror="this.src='${contextPath }/img/favicon.png'">
	                    </c:if>
	                     <c:if test="${rec.mfile_path==null }">
	                     <img src="${contextPath }/img/favicon.png" alt="">
	                     </c:if>
	                    </div>
	                  </td>
	                </tr>
	              </c:forEach>
	              </c:if>
	              
	              </tbody>
	            </table>
	          </div>
	         
	      </div>
	   </div>
    </article>
  </section>
  <!-- footer -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>