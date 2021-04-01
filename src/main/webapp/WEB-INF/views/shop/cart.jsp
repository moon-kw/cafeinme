<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

  <!--common -->
  <%@ include file="/WEB-INF/views/include/common.jsp" %>

    <title>장바구니 | Cafe In Me</title>
<!-- nav -->
<link rel="stylesheet" href="${contextPath }/css/include/uppermost.css" />
<script defer src="${contextPath }/js/include/uppermost.js"></script>
<!-- footer -->
<link rel="stylesheet" href="${contextPath }/css/include/footer.css" />

<!-- CSS JS -->
    <link rel="stylesheet" href="${contextPath }/css/shop/cart.css" />
    <script defer src="${contextPath }/js/common/ajax.js"></script>
    <script defer src="${contextPath }/js/shop/cart.js"></script>
    </head>

    <body>
      <!-- nav -->
      <%@ include file="/WEB-INF/views/include/uppermost.jsp" %>
        <section class="cartSection">
          <div class="container">
            <c:choose>
              <c:when test="${map eq null }">
                <input type="hidden" name="isEmpty" id="isEmpty" value="1">
                <div class="cartEmpty">장바구니가 비었습니다</div>
              </c:when>
              <c:when test="${map ne null}">
                <form action="" class="cartForm">
                  <input type="hidden" name="isEmpty" id="isEmpty" value="0">
                  <div class="itemBox" id="itemBox">
                    <span id="cafe_name"><a
                        href="${contextPath }/shop/itemList/${map.cafeVO.cafe_no}">${map.cafeVO.cafe_name}</a></span>
                    <h3 class="title_cart">장바구니</h3>
                    <input type="hidden" name="MEMBER_ID" id="MEMBER_ID" value="${sessionScope.members.member_id }">
                    <div class="itemList" id="itemList">
                      <c:forEach var="rec" items="${map.cartList}">
                        <div class="item">
                          <input type="hidden" class="MENU_NO" value="${rec.MENU_NO}">
                          <input type="text" name="menu_name" class="input_name" value="${rec.MENU_NAME}">
                          <input type="text" name="menu_price" class="input_price" value="">
                          <input type="hidden" class="MENU_PRICE" value="${rec.MENU_PRICE}">
                          <div class="item_count input_count">
                            <button type="button" class="subBtn"><i class="fas fa-minus"></i></button>
                            <input type="number" class="MENU_COUNT" min="1" value="${rec.MENU_COUNT}" readonly>
                            <button type="button" class="addBtn"><i class="fas fa-plus"></i></button>
                          </div>
                          <button type="button" class="delBtn">X</button>
                        </div>
                      </c:forEach>
                    </div>
    
                  </div>
    
                  <div class="account">
                    <h3 class="title_cart">주문 내역</h3>
                    <div class="amount_box">
                      <label for="" class="amount_label">소계</label>
                      <input type="text" id="ORDER_PRICE" class="amount" readonly="readonly" value="총 금액">
                      <span>원</span>
                    </div>
                    <div class="quantity_box">
                      <label for="" class="quantity_label">수량</label>
                      <input type="text" id="ORDER_COUNT" class="quantity" readonly="readonly" value="총 수량">
                      <span>개</span>
                    </div>
                    <button type="submit" class="submitBtn" id="orderBtn">
                      <img src="${contextPath }/img/lock.png" alt="">주문하기
                    </button>
                  </div>
                </form>
              </c:when>
            </c:choose>
          </div>
        </section>
<!-- footer -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>
    </body>

    </html>