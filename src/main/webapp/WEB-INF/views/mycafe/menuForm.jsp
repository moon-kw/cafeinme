<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--common -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>

<title>내 카페 메뉴 관리| Cafe In Me</title>
<!-- nav -->
<link rel="stylesheet" href="${contextPath }/css/include/uppermost.css" />
<script defer src="${contextPath }/js/include/uppermost.js"></script>
<!-- footer -->
<link rel="stylesheet" href="${contextPath }/css/include/footer.css" />


<!-- 카페 네비게이션 -->
<link rel="stylesheet" href="${contextPath }/css/mycafe/include/myCafeNav.css" />
<script defer src="${contextPath }/js/mycafe/include/myCafeNav.js"></script>
<!-- CSS JS -->
<link rel="stylesheet" href="${contextPath }/css/mycafe/menuForm.css" />
<script defer src="${contextPath }/js/mycafe/menuForm.js"></script>
</head>
<body>
<!-- nav -->
<%@ include file="/WEB-INF/views/include/uppermost.jsp"%>
<!-- 현재위치 -->
  <div class="pageTitle">
    <a href="${contextPath}/cafe/myCafe">내 카페 관리</a> > <span class="now">카페 메뉴 관리</span>
  </div>

  <section class="main-container">
  
  	<!-- 카페 네비게이션 -->
	<%@ include file="/WEB-INF/views/mycafe/include/myCafeNav.jsp"%>
	
    
    <article class="saleArticle">
    	<div class="container">
	      <h3 class="title">카페 메뉴 관리</h3>
	      <form
	      class="menuJoinForm" 
				action="/cafeinme/cafe/menujoinForm"
				enctype="multipart/form-data" 
				method="post">
	        <h4 class="description">메뉴를 추가, 수정, 삭제 할 수 있습니다.</h4>
	          <div class="table">
	            <table class="menuJoin_table">
	              <thead>
	                <tr class="menuJoin_head">
	                  <th><input type="checkbox" name="allSelect" class="th-checkBox"></th>
	                  <th>카테고리</th>
	                  <th>메뉴 이름</th>
	                  <th>메뉴 가격</th>
	                  <th>메뉴 설명</th>
	                  <th>첨부파일</th>
	                  <th>미리보기</th>
	                </tr>
	              </thead>
	              <tbody class="myTable">
	                <c:if test="${menuvo!=null}">
	              <c:forEach var="rec" items="${menuvo }" varStatus="status">
	              <tr>
	                  <td>
	                  	<input type="hidden" name="menulist[${status.count-1}].menu_no" readonly value="${rec.menu_no}" class="hiddenmenuinfo"/>
	                    <input type="checkbox" class="td-checkBox">
	                  </td>
	                  <td>
	                    <select class="form-select form-select-sm" aria-label=".form-select-sm example" name="menulist[${status.count-1}].menu_category">                 
	                      <c:forEach var="category_item" items="${category}">
	                      <c:if test="${rec.category_name==category_item.CATEGORY_NAME  }">
	                      <option selected value="${category_item.CATEGORY_NO }">${category_item.CATEGORY_NAME }</option>        
	                      </c:if>
	                      <c:if test="${rec.category_name!=category_item.CATEGORY_NAME   }">
	                      <option value="${category_item.CATEGORY_NO }">${category_item.CATEGORY_NAME }</option>                </c:if>
	                       
	                      </c:forEach>
	                    </select>
	                  </td>
	                  <td>
	                  <input type="text" name="menulist[${status.count-1}].menu_name" class="form-input mname" value="${rec.menu_name}"></td>
	                  <td><input type="text" name="menulist[${status.count-1}].menu_price" class="form-input mprice" value="${rec.menu_price }"></td>
	                  <td><input type="text" name="menulist[${status.count-1}].menu_content" class="form-input" value="${rec.menu_content }"></td>
	                  <td><input type="file" name="menulist[${status.count-1}].file" class="form-input mfile" onchange="changeimg(event)"></td>
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
	                <c:if test="${status.last }">
	                <script>
									let mcnt = ${status.count};
	                </script></c:if>
	              </c:forEach>
	              </c:if>
	              <c:if test="${menuvo.isEmpty()}">
	              <script>
	            
									let mcnt = 0;
	                </script>
	              </c:if>
	              </tbody>
	            </table>
	          </div>
	          <div class="buttons">
	            <button class="addBtn" id="addBtn">추가</button>
	            <button class="delBtn" id="delBtn">삭제</button>
	            <button type="submit" class="submitBtn" id="submitBtn">수정</button>
	          </div>
	      </form>
	    </div>
    </article>
  </section>
  <!-- footer -->
  <c:forEach var="rec" items="${category }">
  <input type="hidden" class="categoryinfo" data-cno="${rec.CATEGORY_NO}" data-cna="${rec.CATEGORY_NAME }"/>
  </c:forEach>
<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>