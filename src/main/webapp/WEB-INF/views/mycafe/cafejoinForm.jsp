<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--common -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<title>내 카페 등록 | Cafe In Me</title>

<!-- nav -->
<link rel="stylesheet" href="${contextPath }/css/include/uppermost.css" />
<script defer src="${contextPath }/js/include/uppermost.js"></script>
<!-- footer -->
<link rel="stylesheet" href="${contextPath }/css/include/footer.css" />

<!-- myPage CSS JS -->
<link rel="stylesheet" href="${contextPath }/css/member/include/mypage_nav.css" />
<script defer src="${contextPath }/js/member/include/myPage_nav.js"></script>

<link rel="stylesheet" href="${contextPath }/css/mycafe/cafejoinForm.css" />
</head>


<body>
<!-- nav -->
<%@ include file="/WEB-INF/views/include/uppermost.jsp"%>
<script defer src="${contextPath }/js/mycafe/cafejoinform.js"></script>
<script defer src="${contextPath }/js/mycafe/addrlink.js"></script>
<script defer src="${contextPath }/js/mycafe/jquery-1.12.4.min.js"></script>




<div class="body">
<%@ include file="/WEB-INF/views/member/include/mypage_nav.jsp" %>
<div class="main">  
  	<div class="top">
  	  <div class="title">내 카페 등록</div>
  	  <div class="subtitle">내 카페를 등록합니다.</div>
  	  <hr>
      <form id="joinForm"
      action="/cafeinme/cafe/cafejoin"
      enctype="multipart/form-data"
      method="post"
      class="joinForm">
      <ul class="cafejoinform_ul">

      <div class="infotop">
        <div class="infobind">
        <li><label for="CAFE_NAME">카페 이름</label></li>
        <li><input type="text" id="CAFE_NAME" name="cafe_name"/></li>
        </div>

        <div class="infobind">
          <li><label for="BN">사업자 번호</label></li>
          <li><input type="text" id="BN" name="bn" placeholder="ex)000-00-00000" maxlength="12"/></li>
        </div>
      </div>

      <div class="infomiddle">

          <div class="infobind">
            <li><label for="CAFE_ADDRESS1">카페 주소</label></li>
            <li><input type="text" id="CAFE_ADDRESS1" name="cafe_address1" readonly="true"/>
            <button id="searchbts">도로명 주소 검색</button></li>
          </div>
      </div>

        
          <div class="infolow">
          	<div class="infobind">
          	<li><label for="CAFE_ADDRESS2">카페 상세주소</label></li>
          	<li><input type="text" id="CAFE_ADDRESS2" name="cafe_address2" readonly="true"/></li>
          	</div>
          	<div class="infobind">
          		<li><label for="CAFE_TEL">카페 전화번호</label></li>
          		<li><input type="text" id="CAFE_TEL" name="cafe_tel" placeholder="ex) 010-0000-0000" maxlength="13"/></li>
          	</div>
          </div>
        
      
    
      <div class="infobottom">

        <div class="infobind">
        	<li><label for="OPEN_TIME">카페 여는 시간</label></li>
        	<li><input type="time" id="OPEN_TIME" name="open_time"/></li>
        </div>

        <div class="infobind">
        	<li><label for="CLOSE_TIME">카페 닫는 시간</label></li>
        	<li><input type="time" id="CLOSE_TIME" name="close_time"/></li>
        </div>
      </div>

      <div class="infoend">
      <li><label for="CAFE_CONTENT">카페 소개</label></li>
      <li><textarea cols="50" rows="10" id="CAFE_CONTENT" placeholder="카페에 대한 간략한 설명을 입력해주세요." name="cafe_content"></textarea>
      <li>
      <div class="tags">                    
       <div id="Tag">
                <div class="tagbox">
				<c:forEach var="rec" items="${tags }">
				<div class="checks etrans">
                        <input type="checkbox" name="tag_no" id="${rec.tag_name }" value="${rec.tag_no }"> 
                        <label for="${rec.tag_name }">${rec.tag_name}</label> 
                    </div>
				</c:forEach>
            </div>
            </div>
      </div>
      </li>
      
   
      
      <li>
      <div class="imported_files">
      <div class="wrapper"></div>
       		<div class="box add"><img src="${contextPath }/img/add.png" class="addimg"/></div>     
      </div>
      </li>
      
      <li class="hiddenfile">
      <input type="file" id="fake" multiple/>
      <input type="file" id="real" name="files" multiple/>
      </li>
      <li></li>
      </div>
      <li><input id="submitbts" type="submit" value="카페 등록"></li>
      <li><p id="err_msg"></p></li>
      </ul>
      </form>
  	</div>
  </div>
  
</div>	
<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>