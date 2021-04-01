<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--common -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>

<title>온라인 샵 메인 | Cafe In Me</title>
<!-- nav -->
<link rel="stylesheet" href="${contextPath }/css/include/uppermost.css" />
<script defer src="${contextPath }/js/include/uppermost.js"></script>
<!-- footer -->
<link rel="stylesheet" href="${contextPath }/css/include/footer.css" />

<link rel="stylesheet" href="${contextPath }/css/shop/shop.css" />
<script src="${contextPath }/js/shop/shop.js"></script>
</head>
<body>
<!-- nav -->
<%@ include file="/WEB-INF/views/include/uppermost.jsp"%>
  
    <section id="shopSection">
    <div class="container"> 
      <h1 class="page_title">Online Shop</h1>
      <div class="shop_list">

			<c:forEach items="${list }" var="list">
               <div class="shop_list_item">
	              <a href="${contextPath }/shop/itemList/${list.cafe_no }">
	                	<c:if test="${list.cfile_path!=null }">
	                    <img src="${contextPath }/img/${list.cfile_path}" onerror="this.src='${contextPath }/img/favicon.png'">
	                    </c:if>
	                     <c:if test="${list.cfile_path==null }">
	                     <img src="${contextPath }/img/favicon.png" alt="">
	                     </c:if>
	                <h4 class="shop_name">${list.cafe_name }</h4>
	                <span class="shop_address">
	                  ${list.cafe_address1 }
	                </span>
	              </a>
            	</div>
            </c:forEach>


      </div>
      
    </div>
  </section>
  <!-- footer -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>