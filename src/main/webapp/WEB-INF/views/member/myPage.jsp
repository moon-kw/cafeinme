<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--common -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>

<title>마이페이지 | Cafe In Me</title>
<!-- nav -->
<link rel="stylesheet" href="${contextPath }/css/include/uppermost.css" />
<script defer src="${contextPath }/js/include/uppermost.js"></script>
<!-- footer -->
<link rel="stylesheet" href="${contextPath }/css/include/footer.css" />
<!-- CSS -->
<link rel="stylesheet" href="${contextPath }/css/member/myPage.css" />
</head>
<body>
<!-- nav -->
<%@ include file="/WEB-INF/views/include/uppermost.jsp"%>


  <section id="myPage">
  
  	<div class="upper-title"> 
  	
  	<div class="hr"></div>
  	
  	<div class="logo_img"><a href="/cafeinme"><img src="${contextPath }/img/logo_final1.png"/></a></div>
  	
  	<div class="hr"></div>
  	
  	</div>
  
    <div class="inner-container">
    
    
    <!-- 내 계정 -->
    
      <div class="myPage-box">
       
    
        
        <div class="box_title">
        	
          <div class="link">
          	 <a href="${contextPath }/member/memberModifyForm" class="btnBox">
         	 <img src="${contextPath }/img/mypage_icons/myaccount.png" />
        	</a>
          </div>
          
           <div class="hr_short"></div>
          
          <h4>내 계정</h4>
          
           
          
        </div>
      </div>
      
      
      <!-- 북마크 -->
      
      <div class="myPage-box">
      
       
   
      <div class="box_title">
        
        <div class="link">
        <a href="${contextPath }/member/mybookmark" class="btnBox">
         <img src="${contextPath }/img/mypage_icons/bookmark.png" />
        </a>
        </div>
        
        <div class="hr_short"></div>
        
        <h4>북마크</h4>
        
          
        
        
      </div>
      </div>
   
	
		<!-- 내 구매내역 -->
   
      <div class="myPage-box">
      
   
      
      <div class="box_title">
        
        
        <div class="link">
        <a href="${contextPath }/shop/orderList" class="btnBox">
          <img src="${contextPath }/img/mypage_icons/orderlist.png" />
        </a>
        </div>
        
         <div class="hr_short"></div>
        
        <h4>내 구매내역</h4>
        
         
        
        </div>
      </div>
      
      <!-- 내가 쓴 리뷰 -->
      
      <div class="myPage-box">
      
    
      
      <div class="box_title">
        
        <div class="link">
        <a href="${contextPath }/reviews/myreview" class="btnBox">
          <img src="${contextPath }/img/mypage_icons/myreview.png" />
        </a>
        </div>
        
        <div class="hr_short"></div>
        
        <h4>내가 쓴 리뷰</h4>
        
        </div>
      </div>
    

    <!-- 내 카페 -->
      <div class="myPage-box">
      
     
      
      <div class="box_title">
    
        
        <div class="link">
        <a href="${contextPath }/cafe/myCafe" class="btnBox">
           <img src="${contextPath }/img/mypage_icons/mycafe.png" />
        </a>
        </div>
        
          <div class="hr_short"></div>
        
           <h4>내 카페</h4>
        
        </div>
      </div>
      
    
	</div>
	</section>
		
	      <!-- footer -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>