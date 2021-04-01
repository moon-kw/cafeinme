<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--common -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>

<title>내 카페 | Cafe In Me</title>
<!-- nav -->
<link rel="stylesheet" href="${contextPath }/css/include/uppermost.css" />
<script defer src="${contextPath }/js/include/uppermost.js"></script>
<!-- footer -->
<link rel="stylesheet" href="${contextPath }/css/include/footer.css" />

<link rel="stylesheet" href="${contextPath }/css/mycafe/myCafeMain.css" />
</head>
<body>

<!-- nav -->
<%@ include file="/WEB-INF/views/include/uppermost.jsp"%>


  <section id="myCafe">
  
  	<div class="upper-title"> 
  	
  	<div class="hr"></div>
  	
  	<div class="logo_img"><a href="/cafeinme"><img src="${contextPath }/img/logo_final1.png"/></a></div>
  	
  	<div class="hr"></div>
  	
  	</div>
  
    <div class="outer-container">
      <div class="mycafe-group cafe">
      
  
      
      
        
	<div class="inner-container">
        
        
   <!-- 내 카페  -->      
          <div class="cafeBox">
          
          
          <div class="topcolor">
            <h4>내 카페</h4>
          </div>
          
        
          
        <a href="${contextPath }/cafe/modifyForm" class="btnBox">
              
          <div class="box_content">
          
          	<img src="${contextPath }/img/cafe_main/coffee.png">
          	
          	<div class="box_content_detail"> 내 카페 정보를 조회합니다. </div>
           
           
            
	      </div>
	      
	
            </a>
	      
 <!-- 카페 메뉴 관리 -->
 
          </div>
          <div class="cafeBox">
          
          <div class="topcolor">
            <h4>카페 메뉴 관리</h4>
          </div>
          
           <a href="${contextPath }/cafe/menuForm" class="btnBox">
          
          <div class="box_content">
          
          <img src="${contextPath }/img/cafe_main/menu.png">
          
          	<div class="box_content_detail"> 내 카페 메뉴를 관리합니다. </div>
          
           
             
            
          </div>
          
          </a>
          </div>
        	</div>
      		</div>
      
      
      <div class="mycafe-group shop">
      
      
        
        <div class="inner-container">
          
  <!-- 온라인 판매 메뉴 관리 -->
          <div class="shopBox">
            <div class="topcolor">
            <h4>온라인 판매 메뉴 관리</h4>
            </div>
            
             <a href="${contextPath }/cafe/onlinemenuForm" class="btnBox">
            
            <div class="box_content">
             <img src="${contextPath }/img/cafe_main/web.png">
             	<div class="box_content_detail"> 온라인 판매 메뉴를 관리합니다. </div>
           
            </div>
             </a>
          </div>
        
  <!-- 실시간 온라인 판매 -->
          <div class="shopBox">
           <div class="topcolor">
            <h4>실시간 온라인 판매</h4>
           </div>
           
           
            <a href="${contextPath }/shop/orderManagement" class="btnBox">
           <div class="box_content">
           
           <img src="${contextPath }/img/cafe_main/online-shopping.png">
           
           	<div class="box_content_detail"> 실시간으로 주문을 관리합니다. </div>
           
            </div>
             </a>
          </div>
          
  <!-- 내 카페 리뷰 -->
          <div class="shopBox">
          	<div class="topcolor">
            <h4>내 카페 리뷰</h4>
             </div>
             
               <a href="${contextPath }/reviews/commentWriteForm" class="btnBox">
             <div class="box_content">
             <img src="${contextPath }/img/cafe_main/customer-review.png">
             <div class="box_content_detail"> 작성된 후기를 조회합니다. </div>
          
           
           
            </div>
             </a>
          </div>
          
  <!-- 내 카페 온라인 판매 내역 -->
          <div class="shopBox">
          <div class="topcolor">
            <h4>판매 내역</h4>
           </div>
           
             <a href="${contextPath }/shop/salesManagement" class="btnBox">
           <div class="box_content">
           <img src="${contextPath }/img/cafe_main/receipt.png">
           <div class="box_content_detail"> 온라인 판매 내역을 조회합니다. </div>
          
             
           
          </div>
           </a>
          </div>
        </div>
     </div>
      
      
    </div>
  </section>
  <!-- footer -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>