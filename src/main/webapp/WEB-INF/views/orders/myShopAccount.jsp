<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/views/include/common.jsp" %>

    <title>판매내역 | Cafe In Me</title>
    
    <script defer src="${contextPath }/js/common/ajax.js"></script>
    
<!-- nav -->
<link rel="stylesheet" href="${contextPath }/css/include/uppermost.css" />
<script defer src="${contextPath }/js/include/uppermost.js"></script>
<!-- footer -->
<link rel="stylesheet" href="${contextPath }/css/include/footer.css" />

<!-- 카페 네비게이션 -->
<link rel="stylesheet" href="${contextPath }/css/mycafe/include/myCafeNav.css" />
<script defer src="${contextPath }/js/mycafe/include/myCafeNav.js"></script>
   
<!-- CSS JS -->
<script defer src="${contextPath }/js/orders/salesManagementForm.js"></script>
<link rel="stylesheet" href="${contextPath }/css/mycafe/myshop/myShopAccount.css" />
</head>
<body>

<!-- nav -->
<%@ include file="/WEB-INF/views/include/uppermost.jsp"%>
<!-- 현재위치 -->
  <div class="pageTitle">
    <a href="${contextPath}/cafe/myCafe">온라인 판매 관리</a> > <span class="now">판매내역</span>
  </div>
  
	<section class="main-container">
	    	<!-- 카페 네비게이션 -->
	  <%@ include file="/WEB-INF/views/mycafe/include/myCafeNav.jsp"%>
	  
	  <article class="accountArticle">
	    <div class="container">
	      <h3 class="title">판매내역</h3>
	      <input type="hidden" id="CAFE_NO" name="CAFE_NO" value="${cafeVO.cafe_no}">
	      <div class="search-group">
	        <div class="btn-group">
	            <button id="btn_today" class="btn_today">오늘</button>
	            <button id="btn_oneweak" class="btn_oneweek">1주일</button>
	            <button id="btn_onemonth" class="btn_onemonth">1달</button>
	            <button id="btn_threemonth" class="btn_threemonth">3달</button>
	        </div>
	        <div class="filter-group">
	            <input type="date" name="input_startDt" id="input_startDt">
	            <input type="date" name="input_endDt" id="input_endDt">
	            <button id="btn_search">조회</button>
	            <select name="select_orderStatus" id="select_orderStatus">
	                <option value="status00" selected>주문상태 전체</option>
	                <option value="status01">접수대기</option>
	                <option value="status02">주문접수</option>
	                <option value="status03">주문완료</option>
	                <option value="status04">주문거부</option>
	                <!-- <option value="status05">배달완료</option> -->
	            </select>
	        </div>
	      </div>
	      <div class="account-box">
	          <h4 class="account_title">
	            <i class="fas fa-calculator"></i>
	            <br>
	            정산내역
	          </h4>
	          <span id="totalprice" class="account_total"></span>
	          <span id="settlementCnt" class="account_settlementCnt"></span>
	      </div>
	      <table class="table accountTable">
	          <thead class="account_ths">
	              <tr>
	                  <th scope="col" class="order_num">주문번호</th>
	                  <th scope="col" class="order_cust">주문고객</th>
	                  <th scope="col" class="order_content">주문내용</th>
	                  <!-- <th scope="col">카페번호</th> -->
	                  <th scope="col" class="order_price">판매금액</th>
	                  <th scope="col" class="order_date">판매날짜</th>
	                  <th scope="col" class="order_address">판매주소</th>

	                  <th scope="col" class="order_pay">결제방법</th>
	                  <th scope="col" class="order_receive">수령방법</th>
	                  <th scope="col" class="order_status">판매상태</th>
	              </tr>
	          </thead>
	          <tbody id="List" class="account_td">
 				<c:forEach var="rec" items="${map.orderList}">
 				  <tr>
					  	<td>${rec.ORDER_NO}</td>
					  	<td>${rec.MEMBER_ID}</td>
						<td>
							<c:forEach var="item" items="${map.orderDetailList[rec.ORDER_NO]}">
								${item.MENU_NAME} ${item.MENU_COUNT}개<br>
							</c:forEach>
						</td>
						<td>${rec.ORDER_PRICE}원</td>
						<td>
							<fmt:formatDate pattern="yyyy-mm-dd HH:mm" value="${rec.ORDER_DATE}"/>
						</td>
						<c:if test="${rec.ORDER_ADDRESS2 eq null }">
							<td>${rec.ORDER_ADDRESS1}</td>
						</c:if>
						<c:if test="${rec.ORDER_ADDRESS2 ne null }">
							<td>${rec.ORDER_ADDRESS1} ${rec.ORDER_ADDRESS2}</td>
						</c:if>
						<td>${rec.ORDER_PAYMENTTYPE}</td>
						<td>${rec.ORDER_RECEIVETYPE}</td>
						<td>${rec.ORDER_STATUS}</td>
					</tr>
				</c:forEach>
	          </tbody>
	      </table>
			<div class="pageBtn" id="pageBtn">
				<!-- 페이징 -->
				<ul class="pageBtn-ul">
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
	    </div>
	  </article>
	</section>
<!-- footer -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>