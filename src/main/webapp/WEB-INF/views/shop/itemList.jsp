<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!--common -->
    <%@ include file="/WEB-INF/views/include/common.jsp" %>

        <title>카페 상품 목록보기 | Cafe In Me</title>
<!-- nav -->
<link rel="stylesheet" href="${contextPath }/css/include/uppermost.css" />
<script defer src="${contextPath }/js/include/uppermost.js"></script>
<!-- footer -->
<link rel="stylesheet" href="${contextPath }/css/include/footer.css" />


        <link rel="stylesheet" href="${contextPath }/css/shop/itemList.css" />
        <!-- <script defer src="${contextPath }/js/shop/itemList.js"></script> -->
        </head>

        <body>
            <!-- nav -->
            <%@ include file="/WEB-INF/views/include/uppermost.jsp" %>

                <div class="body">

                    <!-- 카페 이름 -->
                    <div class="cafe_title">
                        <p class="cafe_name">${cafeVO.cafe_name}</p>


                    </div>
                    <div class="hr"></div>


                    <!-- 상단 인포 - 카페 상세정보, 리뷰 -->
                    <div class="info_top">
                        <div class="cafe_info">
                            <div class="cafeinfo_top">

                                <div class="cafe_img">
                                    <img src="${contextPath }/img/${cafeVO.cfile_path }"
                                        class="cafe_main_img" alt="X">
                                </div>
                                <div class="vr"></div>
                                <div class="cafe_text">
                                    <div class="cafe_address">
                                        <p id="cafe_address1">${cafeVO.cafe_address1}</p>
                                        <p id="cafe_address2">${cafeVO.cafe_address2}</p>
                                    </div>
                                    <div class="cafe_tel">
                                        <p> &#9742; </p>
                                        <p id="cafe_tel"> ${cafeVO.cafe_tel}</p>
                                    </div>
                                    <div class="open_close">
                                        Open
                                        <p id="open_time">${cafeVO.open_time}</p>
                                        | Close
                                        <p id="close_time">${cafeVO.close_time}</p>
                                    </div>

                                    <div class="cafe_content">
                                        <p id="cafe_content">
                                            ${cafeVO.cafe_content}
                                        </p>
                                    </div>
                                </div>
                                <!-- 하단 인포 - 이동하기 버튼 -->
                            </div>
                        </div>
                    </div>

                    <!-- 중단 인포 - 카페 메뉴 -->
                    <div class="info_middle">
                        <div id="menu" class="tabcontent">
                            <div class="cafe_menu">
                                <br>
                                <div class="cafe_subtitle">MENU</div>
                                <c:forEach var="rec" items="${categoryList}">
                                    <div class="category" id="category_${rec.CATEGORY_NO}">
                                        <div class="category_title">
                                            <div class="category_title_top">
                                                <div class="category_name">${rec.CATEGORY_NAME}</div>
                                            </div>
                                            <div class="cateogry_hr"></div>
                                        </div>
                                        <div class="category_menu">
                                            <c:forEach var="rec2" items="${menuList}">
                                                <c:if test="${rec2.menu_category eq rec.CATEGORY_NO}">

                                                    <div class="menu menu_list">
                                                        <img src="${contextPath }/img/ ${rec2.mfile_path}"
                                                            alt="X">
                                                        <div class="category_vr"></div>
                                                        <div class="menu_detail">
                                                            <div class="menu_name">
                                                                <a href="http://localhost:9080/cafeinme/shop/itemDetail/${rec2.menu_no}">
                                                                    <p class="name">${rec2.menu_name}</p>
                                                                </a>
                                                            </div>
                                                            <div class="menu_price">
                                                                <p class="num">${rec2.menu_price}</p>
                                                                <p class="unit">￦</p>
                                                            </div>
                                                        </div>
                                                    </div>

                                                </c:if>

                                            </c:forEach>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>


                </div>
<!-- footer -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>

        </body>

        </html>