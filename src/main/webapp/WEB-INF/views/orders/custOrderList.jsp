<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/common.jsp"%>
    <title>내 구매 내역 | Cafe In Me</title>
    <script defer src="${contextPath }/js/common/ajax.js"></script>
<!-- nav -->
<link rel="stylesheet" href="${contextPath }/css/include/uppermost.css" />
<script defer src="${contextPath }/js/include/uppermost.js"></script>
<!-- footer -->
<link rel="stylesheet" href="${contextPath }/css/include/footer.css" />

<!-- myPage CSS JS -->
<link rel="stylesheet" href="${contextPath }/css/member/include/mypage_nav.css" />
<script defer src="${contextPath }/js/member/include/myPage_nav.js"></script>
<!-- CSS JS -->
<link rel="stylesheet" href="${contextPath }/css/orders/custOrderList.css" />
<script defer src="${contextPath }/js/orders/custOrderList.js"></script>
<link rel="stylesheet" href="${contextPath }/css/reviews/writeReviewForm.css" />
</head>
<body>
<!-- nav -->
<%@ include file="/WEB-INF/views/include/uppermost.jsp"%>

<div class="body">

<%@ include file="/WEB-INF/views/member/include/mypage_nav.jsp" %>

<div class="main">

  <section class="myOrderListSection">
    <div class="container">
      <div class="top">
        <div class="title">내 구매 내역</div>
        <div class="subtitle">구매 내역을 조회합니다.</div>
   	 </div>
      <hr>
      <br>
      <div><input type="hidden" id="MEMBER_ID" name="MEMBER_ID" value="${sessionScope.member.MEMBER_ID }"></div>
      <div class="search-group">
        <div class="btn-group">
            <button id="btn_today">오늘</button>
            <button id="btn_oneweak">1주일</button>
            <button id="btn_onemonth">1달</button>
            <button id="btn_threemonth">3달</button>
        </div>
        <div class="date-group">
            <input type="date" name="input_startDt" id="input_startDt">
            <input type="date" name="input_endDt" id="input_endDt">
            <button class="btn_search">조회하기</button>
        </div>
      </div>
      <table class="table myOrderTable">
          <thead>
            <tr>
              <th scope="col" class="order_detail th_detail">주문상세</th>
              <th scope="col" class="order_date th_date">주문 날짜</th>
              <th scope="col" class="order_cafe th_cafe">주문 카페</th>
              <th scope="col" class="order_menu th_menu">주문 내용</th>
              <th scope="col" class="order_price th_price">결제 금액</th>
              <th scope="col" class="order_status th_status">주문상태</th>
              <th scope="col" class="order_receive th_receive">수령방법</th>
              <th scope="col" class="order_review th_review">리뷰</th>
            </tr>
          </thead>
          <tbody id="List">
           <c:forEach var="rec" items="${map.orderList }">
            <tr>
              <input type="hidden" class="cafeNo" value="${rec.CAFE_NO}">
              <td class="order_detail td_detail">
                <button class="detailBtn" value="${rec.ORDER_NO}">상세보기</button>
              </td>
              <td class="order_date td_date">
                <fmt:formatDate pattern="yyyy-mm-dd HH:mm" value="${rec.ORDER_DATE}"/>
              </td>
              <td class="order_cafe td_cafe">
                <a href="/cafeinme/shop/itemList/${rec.CAFE_NO}">${rec.CAFE_NAME}</a>
              </td>
              <td class="order_menu td_menu">${rec.ORDER_ITEMS}</td>
              <td class="order_price td_price">${rec.ORDER_PRICE}원</td>
              <td class="order_status td_status">${rec.ORDER_STATUS}</td>
              <td class="order_receive td_receive">${rec.ORDER_PAYMENTTYPE} / ${rec.ORDER_RECEIVETYPE}</td>
              <td class="order_review td_review rbtnbox">
                <c:if test="${rec.ORDER_STATUS ne '주문거부'}">
                  <c:if test="${rec.ORDER_REVIEW_YN eq 0}">
                    <button class="writeReviewBtn" value="${rec.ORDER_NO}">리뷰쓰기</button>
                  </c:if>
                  <c:if test="${rec.ORDER_REVIEW_YN eq 1}">
                    <button class="viewReviewBtn" value="${rec.ORDER_NO}">리뷰보기</button>
                  </c:if>
                </c:if>
              </td>
            </tr>
           </c:forEach>
          </tbody>
      </table>
      <div class="pageBtn" id="pageBtn">
        <ul class="pageBtn-ul">
          <!-- 페이징 -->
            <!-- 보여줄 이전페이지가 있는 경우만 보이게  -->
            <c:if test="${map.pc.prev }">
              <!-- 첫 페이지 -->
              <!-- <a class="page-item page-first" href="1">처음</a> -->
              <li class="page-item page-first" value="1">처음</li>
              <!-- 이전 페이지 -->		
              <!-- <a class="page-item page-prev" href="${map.pc.startPage - 1}">이전</a> -->
              <li class="page-item page-prev" value="${map.pc.startPage - 1}">이전</li>
            </c:if>
          
            <c:forEach var="pageNum" begin="${map.pc.startPage }" end="${map.pc.endPage }">
              <!-- 현재페이지와 요청페이지가 같은경우 -->
              <c:if test="${pageNum eq map.pc.rc.reqPage}">
                <!-- <a class="page-item" href="${pageNum}">${pageNum}</a> -->
                <li class="page-item active" value="${pageNum}">${pageNum}</li>
              </c:if>
              <c:if test="${pageNum ne map.pc.rc.reqPage}">
                <!-- <a class="page-item" href="${pageNum}">${pageNum}</a> -->
                <li class="page-item" value="${pageNum}">${pageNum}</li>
              </c:if>   
            </c:forEach>
            <!-- 보여줄 다음페이지가 있는 경우만 보이게 -->
            <c:if test="${map.pc.next }">
              <!-- <a class="page-item page-next" href="${map.pc.endPage + 1}">다음</a>
              <a class="page-item page-end" href="${map.pc.finalEndPage}">끝</a> -->
              <li class="page-item page-next" value="${map.pc.endPage + 1}">다음</li>
              <li class="page-item page-end" value="${map.pc.finalEndPage}">끝</li>
            </c:if>
        </ul>
					<!-- // 페이징 -->
    </div>
  </section>
 </div>
 </div>
 </div>
 <!-- footer -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>
