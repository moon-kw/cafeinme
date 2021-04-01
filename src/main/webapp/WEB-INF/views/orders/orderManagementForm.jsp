<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="/WEB-INF/views/include/common.jsp" %>
        <title>온라인 주문 접수 | Cafe In Me</title>

        <script defer src="${contextPath }/js/common/ajax.js"></script>
		<!-- nav -->
		<link rel="stylesheet" href="${contextPath }/css/include/uppermost.css" />
		<script defer src="${contextPath }/js/include/uppermost.js"></script>
		<!-- footer -->
		<link rel="stylesheet" href="${contextPath }/css/include/footer.css" />

        <!-- CSS JS -->
        <link rel="stylesheet" href="${contextPath }/css/orders/orderManagementForm.css">
        <script defer src="${contextPath }/js/orders/orderManagementForm.js"></script>

        <!-- 카페 네비게이션 -->
        <link rel="stylesheet" href="${contextPath }/css/mycafe/include/myCafeNav.css" />
        <script defer src="${contextPath }/js/mycafe/include/myCafeNav.js"></script>
        </head>

        <body>
            <!-- nav -->
            <%@ include file="/WEB-INF/views/include/uppermost.jsp" %>

                <!-- 현재위치 -->
                <div class="pageTitle">
                    <a href="${contextPath}/cafe/myCafe">내 카페 관리</a> > <span class="now">실시간 판매 관리</span>
                </div>

                <section class="main-container">

                    <!-- 카페 네비게이션 -->
                    <%@ include file="/WEB-INF/views/mycafe/include/myCafeNav.jsp" %>

                        <input type="hidden" id="CAFE_NO" name="CAFE_NO" value="${cafeVO.cafe_no}">
                        <article class="statusArticle">
                            <div class="container" id="orderManagementList">
                                <h3 class="title">실시간 판매 관리</h3>
                                <h4 class="description">실시간 주문을 버튼과 탭으로 쉽게 관리할 수 있습니다.</h4>
                                <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
                                    <li class="nav-item-b tab-item" role="presentation">
                                        <button class="nav-link active" id="pills-wait-tab" data-bs-toggle="pill"
                                            data-bs-target="#pills-wait" type="button" role="tab"
                                            aria-controls="pills-wait" aria-selected="true">주문접수</button>
                                    </li>
                                    <li class="nav-item-b tab-item" role="presentation">
                                        <button class="nav-link" id="pills-accept-tab" data-bs-toggle="pill"
                                            data-bs-target="#pills-accept" type="button" role="tab"
                                            aria-controls="pills-accept" aria-selected="false">준비 중</button>
                                    </li>
                                    <li class="nav-item-b tab-item" role="presentation">
                                        <button class="nav-link" id="pills-complete-tab" data-bs-toggle="pill"
                                            data-bs-target="#pills-complete" type="button" role="tab"
                                            aria-controls="pills-complete" aria-selected="false">완료</button>
                                    </li>
                                </ul>
                                <div class="tab-content" id="pills-tabContent">
                                    <div class="tab-pane active" id="pills-wait" role="tabpanel"
                                        aria-labelledby="pills-wait-tab">
                                        <!-- <div id="waitTab" class="tabContent">
                            <div class="waitTab_orderInfo">
                                <div class="orderTime">
                                    14시 23분
                                </div>
                                <div class="recieveType">
                                    배달
                                </div>
                            </div>
                            <div class="waitTab_orderMenu">
                                <div class="menu_count">
                                    5개
                                </div>
                                <div class="menu_name">
                                    아메리카노 2,
                                    초코쿠키 1,
                                    아이스라떼 2
                                </div>
                                <div class="orderAddress">
                                    울산광역시 어쩌구 저쩌동
                                </div>
                                <div class="paymentWay">
                                    바로결제
                                </div>
                            </div>
                            <div class="waitTab_buttons">
                                <button id="okBtn">접수</button>
                                <button id="noBtn">거부</button>
                            </div>
                        </div> -->
                                    </div>
                                    <div class="tab-pane" id="pills-accept" role="tabpanel"
                                        aria-labelledby="pills-accept-tab">
                                        <!-- <div id="acceptTab" class="tabContent">
                            <div class="acceptTab_orderInfo">
                                <div class="orderTime">
                                    14시 23분
                                </div>
                                <div class="recieveType">
                                    배달
                                </div>
                            </div>
                            <div class="acceptTab_orderMenu">
                                <div class="menu_count">
                                    2개
                                </div>
                                <div class="menu_name">
                                    아이스 아메리카노 1,
                                    아이스 라떼 1
                                </div>
                                <div class="orderAddress">
                                    울산광역시 어쩌구 저쩌동
                                </div>
                                <div class="paymentWay">
                                    바로결제
                                </div>
                            </div>
                            <div class="acceptTab_buttons">
                                <button id="billsBtn">주문표<br>보기</button>
                                <button id="completeBtn">완료<br>처리</button>
                            </div>
                        </div> -->
                                    </div>
                                    <div class="tab-pane" id="pills-complete" role="tabpanel"
                                        aria-labelledby="pills-complete-tab">
                                        <!-- <div id="completeTab" class="tabContent">
                            <div class="completeTab_orderInfo">
                                <div class="orderResult">
                                    거부
                                </div>
                                <div class="orderTime">
                                    14시 23분
                                </div>
                            </div>
                            <div class="completeTab_orderMenu">
                                <div class="menu_count">
                                    3개
                                </div>
                                <div class="menu_name">
                                    휘낭시에
                                    hot 아메리카노
                                    ice 아메리카노
                                </div>
                                <div class="orderAddress">
                                    울산광역시 어쩌구 저쩌동
                                </div>
                                <div class="paymentWay">
                                    바로결제
                                </div>
                            </div>
                            <div class="completeTab_buttons">
                                <button id="billsBtn">주문표<br>보기</button>
                            </div>
                        </div> -->
                                    </div>
                                </div>
                            </div>
                        </article>
                </section>
                
<!-- footer -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>
        </body>

        
        </html>