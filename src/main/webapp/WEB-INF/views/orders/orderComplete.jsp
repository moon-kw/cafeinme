<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--common -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>

<title>주문완료 | Cafe In Me</title>
<!-- nav -->
<link rel="stylesheet" href="${contextPath }/css/include/uppermost.css" />
<script defer src="${contextPath }/js/include/uppermost.js"></script>
<!-- footer -->
<link rel="stylesheet" href="${contextPath }/css/include/footer.css" />


<!-- CSS JS -->
<link rel="stylesheet" href="${contextPath }/css/orders/orderComplete.css" />
</head>
<body>
<!-- nav -->
<%@ include file="/WEB-INF/views/include/uppermost.jsp"%>

    <div class="body">

        <div class="image">
            <img src="${contextPath }/img/orderComplete1.png" alt="">
        </div>

        <div class="top">
            <p class="title">We've got your order!</p>
            <p class="subtitle">구매 완료! 이용해 주셔서 감사합니다.</p>
        </div>

        <div class="hr"></div>

       

        <div class="content">
        
            
            
        <div class="orderstatus">
		  <Article class="OrderDetailSeciton">
		    <div class="container">
		      <h3 class="detail-title">상세 주문 내역</h3>
		      <div class="inner-container">
		        <ul class="order_info">
		          <li class="order_cafe">
		            <img src="${contextPath }/img/${orderVO.cafe_img }" alt="">
		            <a href="${contextPath }/shop/itemList/${orderVO.cafe_no}">${orderVO.cafe_name}</a>
		          </li>
		
		          <!--주문 상세 내용-->
		          <li class="order_menu">
		            <ul class="order_detail">
		              <!--주문메뉴-->
		              <li>
		                <ul class="order_menu_detail">
                      <c:forEach var="rec" items="${orderDetailList}">
                        <li>
                          <span class="menu_name">${rec.MENU_NAME}</span>
                          <span class="menu_count">${rec.MENU_COUNT}개</span>
                          <span class="menu_price">${rec.MENU_COUNT * rec.MENU_PRICE}원</span>
                        </li>
                      </c:forEach>
		                </ul>
		              </li>
		              <!--주문메뉴 끝-->
		
		            </ul>
		          </li>
		          <!--주문 상세 내용 끝-->
		
		        </ul>
            <ul class="oc_info">
              <li>
                <label for="">총 결제 금액</label>
                <input type="text" readonly="true" value="${orderVO.order_price}원">
              </li>
              <li>
                <label for="">수령 방식</label>
                <input type="text" readonly="true" value="${orderVO.order_receivetype}">
              </li>
              <li>
                <label for="">결제 방식</label>
                <input type="text" readonly="true" value="${orderVO.order_paymenttype}">
              </li>
            </ul>
            <a href="${contextPath }">            	
	            <button type="button" class="reviewBtn">메인으로 가기</button>
            </a>
		      </div>
		    </div>
		  </Article>
        </div>
        </div>
        
        
    </div>

<!-- footer -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>