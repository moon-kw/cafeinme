<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
 
</head>
<body>
<body>
	<form:form id="joinForm"
								 modelAttribute="cafeVO" 
								 action="/cafeinme/test/t2"
								 enctype="multipart/form-data" 
								 method="post">
				<ul>
					<li><h3 class="title">카페 등록</h3></li>
					<li><form:label path="cafe_name">카페이름</form:label></li>
					<li><form:input type="text" path="cafe_name"/></li>
					<li><form:label path="bn">사업자번호</form:label></li>
					<li><form:input type="text" path="bn" /></li>
					<li><form:label path="cafe_address1">카페주소</form:label></li>
					<li><form:input type="text" path="cafe_address1" readonly="true"/><button id="searchbts">도로명주소검색</button></li>
					<li><form:label path="cafe_address2">카페상세주소</form:label></li>
					<li><form:input type="text" path="cafe_address2" readonly="true"/></li>
					<li><form:label path="cafe_tel">카페전화번호</form:label></li>
					<li><form:input type="text" path="cafe_tel" /></li>
					<li><form:label path="open_time">카페여는시간</form:label></li>
					<li><form:input type="text" path="open_time" /></li>
					<li><form:label path="close_time">카페닫는시간</form:label></li>
					<li><form:input type="text" path="close_time" /></li>
					<li><form:label path="cafe_content">카페소개</form:label></li>
					<li><form:textarea path="cafe_content" cols="30" rows="10"></form:textarea>
					<li><form:input type="file" path="files" multiple="multiple" accept="image/*" value=""/></li>
					<li><div id="PicArea">
							
						</div></li>
					<li><input id="submitbts" type="submit" value="카페등록"></li>
					<li><p id="err_msg"></p></li>
				</ul>
			</form:form>
			<img src="/cafeinme/img/jdh/994D85495A5978580A.png" alt="" />
</body>
</html>