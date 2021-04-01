<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--common -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>

<title>상품상세 | Cafe In Me</title>
<!-- nav -->
<link rel="stylesheet" href="${contextPath }/css/include/uppermost.css" />
<script defer src="${contextPath }/js/include/uppermost.js"></script>
<script defer src="${contextPath }/js/shop/itemDetail.js"></script>
<script defer src="${contextPath }/js/common/ajax.js"></script>
<link rel="stylesheet" href="${contextPath }/css/shop/itemDetail.css" />
<link rel="stylesheet" href="${contextPath }/css/reset/reset.css" />

</head>
<body>
<!-- nav -->
<%@ include file="/WEB-INF/views/include/uppermost.jsp"%>
  
  <section class="itemSection">
    <form action="" class="toCartForm">
      <div class="item">
          <input type="hidden" name="menu_no" id="menu_no" value="${menuVO.menu_no}">
          <input type="hidden" name="cafe_no" id="cafe_no" value="${menuVO.cafe_no}">
            <div  class="item_img" rowspan="5">
              <img src="${contextPath }/img/${menuVO.mfile_path}" alt="">
            </div>


            <div class="item_detail">

              <div class="exit_icon">
                <input type="button" class="exit_img">
              </div>

              <div class="right_info">

            	<div class="item_name" valign="middle" id="item_name">
            	 	 ${menuVO.menu_name}
            	</div>
  
            	<div class="item_content" valign="top">
            	  	${menuVO.menu_content}
            	</div>
            	<input type="hidden" name="menu_price" id="menu_price" value="${menuVO.menu_price}">   
            	<div class="item_price" valign="top">
					${menuVO.menu_price} 원
				</div>
            	        
            	<div class="item_count" valign="bottom">
            	  <label for="">수량</label>
            	  <input type="number" value="1" min="1" max="99" class="item_count_input">
            	</div>
            	        
            	<div class="item_cartBtn" valign="middle">
            	  <button type="button" class="submitBtn">
            	    <i class="fas fa-shopping-basket"></i>
            	  </button>
            	</div>
              </div>
            </div>
            
      </div>
    </form>


  </section>
</body>
</html>