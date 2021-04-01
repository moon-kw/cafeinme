<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--common -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<!-- nav -->
<%@ include file="/WEB-INF/views/include/uppermost.jsp"%>
<link rel="stylesheet" href="${contextPath }/css/include/uppermost.css" />
<script defer src="${contextPath }/js/uppermost.js"></script>

    <title>Document</title>
</head>
<body>
    <div><input type="text" id="MEMBER_ID" name="MEMBER_ID"></div>
    <table class="table">
        <thead>
            <tr>
                <th scope="col">아이디</th>
                <th scope="col">카페번호</th>
                <th scope="col">메뉴번호</th>
                <th scope="col">메뉴명</th>
                <th scope="col">수량</th>
                <th scope="col">가격</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="rec" items="${cartList}">
                <tr>
                    <td>${rec.MEMBER_ID}</td>
                    <td>${rec.CAFE_NO}</td>
                    <td>${rec.MENU_NO}</td>
                    <td>${rec.MENU_NAME}</td>
                    <td>${rec.MENU_COUNT}</td>
                    <td>${rec.MENU_PRICE}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>