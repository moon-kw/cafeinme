<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--common -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>

<title>내가 쓴 리뷰 | Cafe In Me</title>
<!-- nav -->
<link rel="stylesheet" href="${contextPath }/css/include/uppermost.css" />
<script defer src="${contextPath }/js/include/uppermost.js"></script>
<!-- footer -->
<link rel="stylesheet" href="${contextPath }/css/include/footer.css" />

<!-- myPage CSS JS -->
<link rel="stylesheet" href="${contextPath }/css/member/include/mypage_nav.css" />
<script defer src="${contextPath }/js/member/include/myPage_nav.js"></script>

<!-- CSS JS -->
<link rel="stylesheet" href="${contextPath }/css/reviews/myReviewList.css" />
<script defer src="${contextPath }/js/reviews/myReviewList.js"></script>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
</head>
<body>
<!-- nav -->
<%@ include file="/WEB-INF/views/include/uppermost.jsp"%>
<div class="body">

<%@ include file="/WEB-INF/views/member/include/mypage_nav.jsp" %>

<div class="main">

<div class="top">
        <div class="title">내가 쓴 리뷰</div>
        <div class="subtitle">작성한 리뷰를 조회합니다.</div>
    </div>
      <hr>
      <br>

  <section class="reviewSection">
    <div class="container">

      <div class="filter-group">
        <input type="date" class="filter_startDt"> ~ 
        <input type="date" class="filter_endDt">
        <button type="button" class="filterBtn">조회</button>
      </div>
      <table class="table">
        <thead class="review_th">
          <tr>
            <th class="review_cafe">카페이름</th>
            <th class="review_wdate">작성일시</th>
            <th class="review_menu">주문내용</th>
            <th class="review_content">리뷰내용</th>
          </tr>
        </thead>
        <tbody class="review_tb">
        <c:forEach var="rec" items="${review }">
          <tr>
            <td class="review_cafe"><a href="${contextPath }/search/cafeinfo/${rec.CAFE_NO}">${rec.CAFE_NAME }</a></td>
            <td class="review_wdate"><fmt:formatDate value="${rec.REVIEW_DATE }" pattern="yyyy-MM-dd HH:mm" type="time"/></td>
            <td class="review_menu">${rec.REVIEW_ITEMS }</td>
            <td class="review_content">
              ${rec.REVIEW_CONTENT }
            </td>
          </tr>
        </c:forEach>
        
        </tbody>
      </table>
      <!-- 페이징 버튼 부분 -->
      <div class="pageBtn">
      <!-- 이전페이지 있을시에만 나오게 -->
        <c:if test="${pc.prev }">
          <a href="${contextPath }/reviews/myreview/1">처음</a>
          <a href="${contextPath }/reviews/myreview/${pc.startPage-1}">이전</a>
        </c:if>
          <!-- 숫자  -->
        <c:forEach var="rec" begin="${pc.startPage }" end="${pc.endPage }">
        <a href="${contextPath }/reviews/myreview/${rec}">${rec }</a>
        </c:forEach>
        <!-- 다음페이지 있을시에만 나오게 -->
        <c:if test="${pc.next }">
          <a href="${contextPath }/reviews/myreview/${pc.endPage + 1}">다음</a>
          <a href="${contextPath }/reviews/myreview/${pc.finalEndPage}">끝</a>
        </c:if>
      </div>
    </div>
  </section>
 </div>
 </div>
 <!-- footer -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>