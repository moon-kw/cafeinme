<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <!--common -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>

  <title>내 카페 조회 | Cafe In Me</title>
<!-- nav -->
<link rel="stylesheet" href="${contextPath }/css/include/uppermost.css" />
<script defer src="${contextPath }/js/include/uppermost.js"></script>
<!-- footer -->
<link rel="stylesheet" href="${contextPath }/css/include/footer.css" />

<!-- 카페 네비게이션 -->
<link rel="stylesheet" href="${contextPath }/css/mycafe/include/myCafeNav.css" />
<script defer src="${contextPath }/js/mycafe/include/myCafeNav.js"></script>

<!-- CSS JS -->
    <link rel="stylesheet" href="/cafeinme/css/mycafe/mycafeinfomodifyForm.css">
    <script defer src="/cafeinme/js/mycafe/mycafeinfomodifyForm.js"></script>

  
</head>

<body>

<!-- nav -->
<%@ include file="/WEB-INF/views/include/uppermost.jsp"%>
<!-- 현재위치 -->
  <div class="pageTitle">
    <a href="${contextPath}/cafe/myCafe">내 카페 관리</a> > <span class="now">내 카페 보기</span>
  </div>

	<section class="main-container">
		<!-- 카페 네비게이션 -->
	<%@ include file="/WEB-INF/views/mycafe/include/myCafeNav.jsp"%>
	  <div class="body">
	
	    <div class="top">
	        <div class="title">내 카페 정보</div>
	        <div class="description">내 카페 정보를 조회합니다.</div>
	        <hr>
	    </div>

    <!-- 캐러셀 -->
  <div class="carousel">
      <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-indicators">
        <c:if test="${cafevo.cfilevo.get(0).cfile_path==null }">
          <button type="button" id="indicators" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
          <button type="button" id="indicators" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
          <button type="button" id="indicators" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
        </c:if>
        <c:forEach var="i" begin="1" end="${cafevo.cfilevo.size() }" >
        <c:if test="${i==1 }">
        <button type="button" id="indicators" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1" ></button>
        </c:if>
        <c:if test="${i!=1 }">
        <button type="button" id="indicators" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="${i-1 }" aria-label="Slide ${i }"></button>
        </c:if>
        </c:forEach>
        </div>
        <div class="carousel-inner">
          <c:if test="${cafevo.cfilevo.get(0).cfile_path==null}">
            <div class="carousel-item active">
                <img src="/cafeinme/img/img_mypage/cafeimg/kris-atomic-3b2tADGAWnU-unsplash.jpg" class="d-block w-100" alt="...">
              </div>
              <div class="carousel-item">
                <img src="/cafeinme/img/img_mypage/cafeimg/daan-evers-tKN1WXrzQ3s-unsplash.jpg" class="d-block w-100" alt="...">
              </div>
              <div class="carousel-item">
                <img src="/cafeinme/img/img_mypage/cafeimg/andy-falconer-dwQRixazu9I-unsplash.jpg" class="d-block w-100" alt="...">
              </div>
            </c:if>
            <c:forEach var="rec" varStatus="vs" items="${cafevo.cfilevo}">
            <c:if test="${vs.first}">
            <div class="carousel-item active">
            <img src="/cafeinme/img/${rec.cfile_path}" class="d-block w-100" alt="..." onerror="this.src='${contextPath }/img/favicon.png'">
              </div>
            </c:if>
            <c:if test="${!vs.first}">
            <div class="carousel-item">
            <img src="/cafeinme/img/${rec.cfile_path}" class="d-block w-100" alt="..." onerror="this.src='${contextPath }/img/favicon.png'">
              </div>
            </c:if>
              </c:forEach>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators"  data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators"  data-bs-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
      </div>
      
       <div class="imported_files">
       <c:forEach var="rec" items="${cafevo.cfilevo }">
       <div class="box curimg"><img src="${contextPath }/img/${rec.cfile_path}" data-cfile_no="${rec.cfile_no }"/></div>
       </c:forEach>
       <div class="wrapper">
       
       </div>
       		<div class="box"><img src="${contextPath }/img/add.png" class="addimg"/></div>      
      </div>

  </div>
  <form action="${contextPath }/cafe/modify" method="post" enctype="multipart/form-data" id="modifyform">
  <!-- 카페 이름 -->
  <div class="cafe_title"><input type="text"  name="cafe_name" value="${cafevo.cafe_name }" id="cafe_name"/></div>
  <div class="hr"></div>


  <!-- 상단 인포 - 카페 상세정보, 리뷰 -->
  <div class="info_top">
    <div class="cafe_info">
            <div class="cafeinfo_top">
                
                    <div class="cafe_address">
                        <p id="cafe_address1"><input type="text" name="cafe_address1" value="${cafevo.cafe_address1 }" readonly="readonly" id="address1"/></p>
                        <p id="cafe_address2"><input type="text" name="cafe_address2" value="${cafevo.cafe_address2 }" readonly="readonly" id="address2"/></p>
                        <button id="roadbts">도로명주소검색</button>
                    </div>
                    <div class="cafe_tel">
                        <p> &#9742; </p>
                        <p id="cafe_tel"> <input type="text" name="cafe_tel" value="${cafevo.cafe_tel }" id="tel"/></p>
                    </div>
                    <div class="open_close">
                        Open
                        <p id="open_time"><input type="time" name="open_time" value="${cafevo.open_time }" id="otime"/></p>
                       | Close 
                        <p id="close_time"><input type="time" name="close_time" value="${cafevo.close_time }" id="ctime"/></p>
                    </div>


                    <div class="cafe_content">
                        <p id="cafe_content">
                           <textarea name="cafe_content"  cols="30" rows="10" id="ccontent">${cafevo.cafe_content }</textarea></p>
                    </div>
    
                    <div class="tags">
                        <div class="tagbox">
				<c:forEach var="rec" items="${tags }">
				<div class="checks etrans">
                        <input type="checkbox" name="tag_no" id="${rec.tag_name }" value="${rec.tag_no }" ${(rec.tag_name==cafevo.tag_name) ? "checked='true'":"" }> 
                        <label for="${rec.tag_name }">${rec.tag_name}</label> 
                    </div>				
				</c:forEach>
            </div>
                        
                    </div>
                    <!-- 하단 인포 - 이동하기 버튼 -->		
            </div>

        	
         
    </div>
    <div class="hiddenfile">
    <input type="file" multiple class="fake"/>
    <input type="file" multiple name="files" class="real" value=""/>
    </div>
    
   
<button class="modifyBtn" id="modifycafeinfo"><img src="/cafeinme/img/img_mypage/edit.png" alt="X"></button>
  </form>

  </div>

  <!-- 중단 인포 - 카페 메뉴 -->
 <div class="info_middle">

    <div class="tab">
        <button class="tablinks" onclick="openTab(event, 'menu')" id="defaultOpen">Menu</button>
    </div>

    <div id="menu" class="tabcontent">
        <div class="cafe_menu">
            <br>
            <div class="cafe_subtitle">MENU</div>
    
            <div class="category">
    
                <!-- 카테고리 이름, 가로선 -->
                <div class="category_title">
                    <div class="category_title_top">
                        <div class="category_name"> COFFEE</div>
                        <div class="category_modify">
                            <button class="modifyBtn" id="menumodifybts"><a href="${contextPath }/cafe/menuForm">
                                <img src="/cafeinme/img/img_mypage/edit.png" alt="X">
                            </a>
                            </button>
                        </div>
                    </div>
                    <div class="cateogry_hr"></div>
                </div>
    
                <!-- 카테고리 - 커피 -->
    
                <div class="category_menu">
                    <c:forEach var="rec" items="${cafevo.menuvo }">
                <c:if test="${rec.category_name=='커피'}">
                <div class="menu"> <!--메뉴 박스-->
                <c:if test="${rec.mfile_path==null}">
                <img src="/cafeinme/img/favicon.png" alt="X">
                </c:if>
                <c:if test="${rec.mfile_path!=null}">
                <img src="/cafeinme/img/${rec.mfile_path}" alt="X" onerror="this.src='${contextPath }/img/favicon.png'">
                </c:if>
                        <div class="category_vr"></div>

                        <div class="menu_detail">
                            <div class="menu_name">
                                <p class="name">${rec.menu_name}</p>
                            </div>
                            <div class="menu_price">
                                <p class="num">${rec.menu_price}</p>
                                <p class="unit">￦</p>
                            </div>
                        </div>
                    </div>
                </c:if>    
                </c:forEach>
                    
                </div>
            </div>
    
            <!-- 카테고리 - 디저트 -->
    
            <div class="category">
                <div class="category_title">
                    <div class="category_title_top">
                        <div class="category_name"> DESSERT</div>
                    </div>
                    <div class="cateogry_hr"></div>
                </div>
    
                <div class="category_menu">
                    <c:forEach var="rec" items="${cafevo.menuvo }">
                <c:if test="${rec.category_name=='디저트'}">
                <div class="menu"> <!--메뉴 박스-->
                <c:if test="${rec.mfile_path==null}">
                <img src="/cafeinme/img/favicon.png" alt="X">
                </c:if>
                <c:if test="${rec.mfile_path!=null}">
                <img src="/cafeinme/img/${rec.mfile_path}" alt="X" onerror="this.src='${contextPath }/img/favicon.png'">
                </c:if>
                      <div class="category_vr"></div>
                        <div class="menu_detail">
                            <div class="menu_name">
                                <p class="name">${rec.menu_name}</p>
                            </div>
                            <div class="menu_price">
                                <p class="num">${rec.menu_price}</p>
                                <p class="unit">￦</p>
                            </div>
                        </div>
                    </div>
                </c:if>    
                </c:forEach>
                </div>
    
    
            </div>
       <div class="category">
                <div class="category_title">
                    <div class="category_title_top">
                        <div class="category_name"> ETC</div>
                    </div>
                    <div class="cateogry_hr"></div>
                </div>
    
                <div class="category_menu">
                    <c:forEach var="rec" items="${cafevo.menuvo }">
                <c:if test="${rec.category_name=='기타'}">
                <div class="menu"> <!--메뉴 박스-->
                <c:if test="${rec.mfile_path==null}">
                <img src="/cafeinme/img/favicon.png" alt="X">
                </c:if>
                <c:if test="${rec.mfile_path!=null}">
                <img src="/cafeinme/img/${rec.mfile_path}" alt="X" onerror="this.src='${contextPath }/img/favicon.png'">
                </c:if>
                      <div class="category_vr"></div>
                        <div class="menu_detail">
                            <div class="menu_name">
                                <p class="name">${rec.menu_name}</p>
                            </div>
                            <div class="menu_price">
                                <p class="num">${rec.menu_price}</p>
                                <p class="unit">￦</p>
                            </div>
                        </div>
                    </div>
                </c:if>    
                </c:forEach>
                </div>
    
    
            </div>
    
    
        </div>
    </div>

  
	 <div class="cafedel">
	
	    
	    <button class="delBtn"><a href="${contextPath }/cafe/del"> 카페 삭제 </a></button>
	 
	 </div>
 
 
 
 </div>


 


	</section>
	<!-- footer -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>
	<script>
let tmp = `${cafevo.tag_name}`.replace('[',"").replace(']',"").split(',');
const checkboxes = document.querySelectorAll(`[type="checkbox"]`);
for(let i=0; i<checkboxes.length; i++){
	for(let j=0; j<tmp.length; j++){
		if(checkboxes[i].id==tmp[j].trim()){
			checkboxes[i].checked = true;
			}
		}
}
</script>
</body>
</html>