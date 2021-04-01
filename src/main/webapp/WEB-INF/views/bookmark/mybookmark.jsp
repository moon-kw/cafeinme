<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--common -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>

<title>북마크 | Cafe In Me</title>
<!-- nav -->
<link rel="stylesheet" href="${contextPath }/css/include/uppermost.css" />
<script defer src="${contextPath }/js/include/uppermost.js"></script>
<!-- footer -->
<link rel="stylesheet" href="${contextPath }/css/include/footer.css" />

<!-- myPage CSS JS -->
<link rel="stylesheet" href="${contextPath }/css/member/include/mypage_nav.css" />
<script defer src="${contextPath }/js/member/include/myPage_nav.js"></script>

<!-- bookmark -->
<link rel="stylesheet" href="${contextPath }/css/bookmark/myBookMark.css" />
<script defer src="${contextPath }/js/bookmark/myBookMark.js"></script>
</head>
<body>

<!-- nav -->
<%@ include file="/WEB-INF/views/include/uppermost.jsp"%>

 <div class="body">
  
  <%@ include file="/WEB-INF/views/member/include/mypage_nav.jsp" %>
  
<div class="main">

	 <div class="top">
    <div class="title">북마크</div>
    <div class="subtitle">추가한 북마크를 조회합니다.</div>
    </div>
    <hr>
    <br>

  <section class="bookmarkSection">
    <div class="container">
     <!--북마크 한 카페-->
      <c:forEach var="rec" items="${bookmarks }">
      <div class="bookmark_box">
      
      
      
      <c:if test="${rec.cfile_path!=null }">
      <div class="bookmark_box_img">
      	<div class="bookmark_box_btn">
        	  <button type="button" class="copyBtn" data-cafe_href="${contextPath }/search/cafeinfo/${rec.cafe_no}" title="링크 복사"><i class="fas fa-copy"></i></button>
          	<button type="button" class="delBtn" data-cafe_no="${rec.cafe_no }"><i class="fas fa-star" title="북마크 삭제"></i></button>
          	
          </div>

          <img src="${contextPath }/img/${rec.cfile_path}" alt="" onerror="this.src=${contextPath }/img/favicon.png" class="imghover">
      </div>
      </c:if>
        <c:if test="${rec.cfile_path==null }">
        <div class="bookmark_box_img">
         
    
          <div class="bookmark_box_btn">
        	  <button type="button" class="copyBtn" data-cafe_href="${contextPath }/search/cafeinfo/${rec.cafe_no}" title="링크 복사"><i class="fas fa-copy"></i></button>
          	<button type="button" class="delBtn" data-cafe_no="${rec.cafe_no }"><i class="fas fa-star" title="북마크 삭제"></i></button>
	
          </div>
          <img src="${contextPath }/img/img_mypage/cafeimg/andy-falconer-dwQRixazu9I-unsplash.jpg" alt="" class="imghover">
        </div>
        </c:if>
        <ul class="bookmark_box_cafe">
          <li class="cafe_name">
            <a href="${contextPath }/search/cafeinfo/${rec.cafe_no}" class="cafe_url">${rec.cafe_name }</a>
          </li>
        </ul>
      </div>
      </c:forEach>
    </div>
  </section>
  </div>
 </div>
 
<!-- footer -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>