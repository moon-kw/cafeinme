<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--common -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>

<title>검색하기 | Cafe In Me</title>
<!-- nav -->
<link rel="stylesheet" href="${contextPath }/css/include/uppermost.css" />
<script defer src="${contextPath }/js/include/uppermost.js"></script>
<!-- footer -->
<link rel="stylesheet" href="${contextPath }/css/include/footer.css" />
	
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f1eb5057df435ee4b95efc6c353545e2&libraries=services"></script>
	<script defer src="${contextPath }/js/search/search.js"></script>
	<link rel="stylesheet" href="${contextPath }/css/search/searchmain.css" />
</head>
<body>
<c:if test="${mainsearch!=null }">
<script>
let mainkeyword = ${mainsearch};
</script>
</c:if>
<div class="divmodal">
</div>


<!-- nav -->
<%@ include file="/WEB-INF/views/include/uppermost.jsp"%>
<div class="container">
	<div class="body">
        <h3>SEARCH</h3>

        <div class="body_content">
    
        <div class="map">
            <div id="map" style="width:100%;height:600px;"> 지도영역</div>
        </div>

        <div class="vr"></div>

        
        <div class="right_content">

            <div class="tab">
                <button class="tablinks" id="curlocation">현재위치검색</button>
                <button class="tablinks" onclick="openTab(event, 'address')" id="defaultOpen">도로명</button>
                <button class="tablinks" onclick="openTab(event, 'keyword')">
                    <div class="tabname">
                        <p class="tab_title">키워드</p>
                    </div>
                <button class="tablinks" onclick="openTab(event, 'tag')">
                    <div class="tabname">
                        <p class="tab_title">태그</p>
                    </div>
                </button>
                </button>
            </div>

            <div id="address" class="tabcontent">
                <div class="searchbar">
                    <input type="text" class="search_text" id="address_search" name="address_search" placeholder="도로명 주소를 입력하세요.">
                    <button class="searchBtn"id="address_searchBtn">Search</button>
                </div>
            </div>

            <div id="tag" class="tabcontent">
                <div class="tagbox">

                  <c:forEach var="rec" items="${tags }">
									 <div class="checks etrans">
                        <input type="checkbox" id="${rec.tag_name }" > 
                        <label for="${rec.tag_name }">${rec.tag_name }</label> 
                    </div>
									</c:forEach>

            </div>
            </div>
            
            <div id="keyword" class="tabcontent">
                <div class="searchbar">
                    <input type="text" class="search_text" id="keyword_search" name="keyword_search" placeholder="키워드를 입력하세요.">
                    <button class="searchBtn" id="keyword_searchBtn">Search</button>
                </div>
            </div>


            <div class="searchresult">
               
                <ul id="searchul">
                   
                </ul>

            </div>

        </div>

        </div>

	
        
</div>
   </div> 
   	<!-- footer -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>
   
     <script>
     function openTab(evt, tabName) {
		var i, tabcontent, tablinks;
		tabcontent = document.getElementsByClassName("tabcontent");
		for (i = 0; i < tabcontent.length; i++) {
			tabcontent[i].style.display = "none";
		}
		tablinks = document.getElementsByClassName("tablinks");
		for (i = 0; i < tablinks.length; i++) {
			tablinks[i].className = tablinks[i].className.replace(" active", "");
		}
		document.getElementById(tabName).style.display = "block";
		evt.currentTarget.className += " active";
		}

		// Get the element with id="defaultOpen" and click on it
		document.getElementById("defaultOpen").click();
    </script>
 

</body>
</html>