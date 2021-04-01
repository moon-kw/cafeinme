<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--common -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>

<title>주문하기 | Cafe In Me</title>
<!-- nav -->
<link rel="stylesheet" href="${contextPath }/css/include/uppermost.css" />
<script defer src="${contextPath }/js/include/uppermost.js"></script>
<!-- footer -->
<link rel="stylesheet" href="${contextPath }/css/include/footer.css" />

<!-- CSS JS -->
<link rel="stylesheet" href="${contextPath }/css/orders/orderForm.css" />
<script defer src="${contextPath }/js/orders/orderForm.js"></script>
</head>
<body>
<!-- nav -->
<%@ include file="/WEB-INF/views/include/uppermost.jsp"%>
  <section class="main-container">
    <form class="orderArticle" id="orderArticle" action="${contextPath }/shop/orderPayment" method="POST" enctype="multipart/form-data">
      <div class="container">
        <h3 class="title">주문 / 결제</h3>
        <!-- input hidden -->
        <input type="hidden" name="MEMBER_ID" id="MEMBER_ID" value="${sessionScope.members.member_id }">
        <input type="hidden" name="CAFE_NO" id="CAFE_NO" value="${cafeVO.cafe_no}">
        <!--주문내용-->
        <div class="orderItem_item">
          <div class="itemCafe">
            <img src="${contextPath }/img/${cafeVO.cfile_path }" alt="주문카페 이미지">
            <span class="cafe_name">${cafeVO.cafe_name}</span>
          </div>
          <table class="itemTable">
            <thead>
              <tr>
                <th class="item_name">상품정보</th>
                <th class="item_count">수량</th>
                <th class="item_price">금액</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="rec" items="${cartList}">
                    <tr>
                        <td class="MENU_NAME">${rec.MENU_NAME}</td>
                        <td><input type="text" class="MENU_COUNT minput" value="${rec.MENU_COUNT}" readonly>개</td>
                        <td><input type="text" class="MENU_PRICE minput" value="${rec.MENU_COUNT * rec.MENU_PRICE}" readonly>원</td>
                    </tr>
                </c:forEach>
            </tbody>
          </table>
        </div>


        <!--주문 회원 정보-->
        <div class="orderItem_info">
          <div class="orderItem_info_member">
            <h4 class="order_h4">수령정보</h4>
            <input type="hidden" name="ORDER_RECEIVETYPE" id="ORDER_RECEIVETYPE">
            <input type="radio" class="delivery" name="receiveType" id="receiveType1" value="배달" checked>
            <label for="receiveType1">배달</label>
            <input type="radio" class="togo" name="receiveType" id="receiveType2" value="포장">
            <label for="receiveType2">포장</label>

            <!--배달-->
            <ul class="newAddressBox">
              <li>
                <label for="">수령인</label>
                <input type="text" class="order_name" value="${sessionScope.members.member_name }">
              </li>
              <li>
                <label for="">수령지</label>
                <div class="inputbox">
                  <button type="button" class="addressBtn">주소찾기</button>
                  <input type="text" class="order_address1" name="ORDER_ADDRESS1" id="roadAddr" readonly>
                  <input type="text" class="order_address2" name="ORDER_ADDRESS2" id="addrDetail">
                </div>
              </li>
              <li>
                <label for="">연락처</label>
                <input type="tel" class="order_tel" value="${sessionScope.members.member_tel}">
              </li>
            </ul>

            <!--포장-->
            <ul class="togoBox">
              <li>
                <label for="">수령인</label>
                <input type="text" class="order_name" value="${sessionScope.members.member_name }">
              </li>
              <li>
                <label for="">연락처</label>
                <input type="tel" class="togo_tel" value="${sessionScope.members.member_tel}">
              </li>
            </ul>

          </div>

          <!-- 총 결제 금액, 결제 수량-->
          <ul class="orderItem_info_price">
            <li>
              <h4 class="orderprice_info">결제상세</h4>
            </li>
            <li>
              <label for="">주문수량</label>
              <input type="text" id="ORDER_COUNT" name="ORDER_COUNT" class="oinput" readonly="true" value="##">
              <span>개</span>
            </li>
            <li>
              <label for="">주문금액</label>
              <input type="text" id="ORDER_PRICE" name="ORDER_PRICE" class="oinput" readonly="true" value="##">
              <span>원</span>
            </li>

            <li>
              <!--카드결제창-->
              <div class="paymentForm">
                <input type="hidden" name="ORDER_PAYMENTTYPE" id="ORDER_PAYMENTTYPE" value="카드결제">
                <ul class="payment_card">
                  <li>
                    <h4 class="payment_h4">결제수단</h4>
                  </li>
                  <li class="card_item cardSort">
                    <label for="card_visa">
                      <input type="radio" name="cardSort" id="" class="item_visa" value="visa">
                      <img src="${contextPath }/img/img_card/visa.png" alt="">
                    </label>
                    <label for="card_master">
                      <input type="radio" name="cardSort" id="" class="item_master" value="master">
                      <img src="${contextPath }/img/img_card/mastercard.png" alt="">
                    </label>
                    <label for="card_jcb">
                      <input type="radio" name="cardSort" id="" class="item_jcb" value="JCB">
                      <img src="${contextPath }/img/img_card/jcb.png" alt="">
                    </label>
                  </li>
                  <li class="card_item cardNum">
                    <label for="">카드번호</label>
                    <input type="text" class="card_item_number" placeholder="0000-0000-0000-0000" maxlength="19">
                  </li>
                  <li class="card_item cardDate">
                    <div class="card_item_date">
                      <label for="">유효기간</label>
                      <input type="text" class="card_item_exp" placeholder="MMYY" maxlength="4">
                    </div>
                    <div class="card_item_date">
                      <label for="">CVC</label>
                      <input type="password" class="card_item_password" maxlength="3" minlength="3" placeholder="000">
                    </div>
                  </li>
                  <li class="card_item cardApply">
                    <label for="">결제에 동의하시겠습니까?
                      <input type="checkbox" class="card_item_apply" name="" id="">
                    </label>
                  </li>
                </ul>
              </div>
              <!--카드 결제창 끝-->
              
              
            </li>
          </ul>
        </div>
        <button type="button" class="payBtn" disabled="true">결제</button>



      </div>
    </form>
  </section>
  <!-- footer -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>