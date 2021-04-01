<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<!--common -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>

<!-- nav -->
<link rel="stylesheet" href="${contextPath }/css/include/uppermost.css" />
<script defer src="${contextPath }/js/include/uppermost.js"></script>
<!-- footer -->
<link rel="stylesheet" href="${contextPath }/css/include/footer.css" />

<script defer src="${contextPath }/js/search/cafeinfo.js"></script>
<link rel="stylesheet" href="/cafeinme/css/search/cafeinfo.css">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
</head>
<body>
<!-- nav -->
<%@ include file="/WEB-INF/views/include/uppermost.jsp"%>

  <div class="body">
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
        <button type="button" id="indicators" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
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
            <img src="/cafeinme/img/${rec.cfile_path}" class="d-block w-100" alt="...">
              </div>
            </c:if>
            <c:if test="${!vs.first}">
            <div class="carousel-item">
            <img src="/cafeinme/img/${rec.cfile_path}" class="d-block w-100" alt="...">
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
    
  </div>

  <!-- 카페 이름 -->
  <div class="cafe_title">
      <p class="cafe_name">${cafevo.cafe_name}</p>
      <c:if test="${login}">
      <c:if test="${cafevo.bookmark}">
      <input type="button" id="bookmarkbts" class="active" />
      </c:if>
      <c:if test="${!cafevo.bookmark}">
      <input type="button" id="bookmarkbts">
      </c:if>
      </c:if>
    </div>
  <div class="hr"></div>
  <!-- 상단 인포 - 카페 상세정보, 리뷰 -->
  <div class="info_top">
    <div class="cafe_info">
            <div class="cafeinfo_top">
                
                    <div class="cafe_address">
                        <p id="cafe_address1">${cafevo.cafe_address1 }</p>
                        <p id="cafe_address2">${cafevo.cafe_address2 }</p>
                    </div>
                    <div class="cafe_tel">
                        <p> &#9742; </p>
                        <p id="cafe_tel">${cafevo.cafe_tel }</p>
                    </div>
                    <div class="open_close">
                        Open
                        <p id="open_time">${cafevo.open_time}</p>
                       | Close 
                        <p id="close_time">${cafevo.close_time}</p>
                    </div>

                    <div class="cafe_content">
                        <p id="cafe_content">
                            ${cafevo.cafe_content}</p>
                    </div>
    
                    <div class="tags">
                         <c:forEach var="rec" items="${cafevo.tag_name}">
                          <div class="tag"> 
                                <div class="check"></div> 
                                ${rec}
                            </div>
                        </c:forEach>
                    </div>
                    
                    <!-- 하단 인포 - 이동하기 버튼 -->
            

            </div>

        
         
    </div>


  </div>

  <!-- 중단 인포 - 카페 메뉴 -->
 <div class="info_middle">

    <div class="tab">
        <button class="tablinks" onclick="openTab(event, 'menu')" id="defaultOpen">Menu</button>
        <button class="tablinks" onclick="openTab(event, 'review')">Review</button>
    </div>
 <c:forEach var="rec" items="${cafevo.menuvo }">
    <c:if test="${rec.category_name=='커피' }">
    <c:set var="excoffee" value="true" scope="page"></c:set>
    </c:if>
    <c:if test="${rec.category_name=='디저트' }">
    <c:set var="exdessert" value="true" scope="page"></c:set>
    </c:if>
    <c:if test="${rec.category_name=='기타' }">
    <c:set var="exetc" value="true" scope="page"></c:set>
    </c:if>
    </c:forEach>
    <div id="menu" class="tabcontent">
        <div class="cafe_menu">
            <br>
            <div class="cafe_subtitle">MENU</div>
    
       <c:if test="${excoffee }">
            <div class="category">
    
                <!-- 카테고리 이름, 가로선 -->
                <div class="category_title">
                    <div class="category_title_top">
                        <div class="category_name"> COFFEE</div>
                    
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
    </c:if>
   
            <!-- 카테고리 - 디저트 -->
    <c:if test="${exdessert }">
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
            </c:if>
            <c:if test="${exetc }">
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
    
</c:if>
    
        </div>
    </div>
    <div id="review" class="tabcontent">
        <div class="cafe_review">
            <div class="cafe_subtitle">REVIEWS</div>


            <table class="review_board">

                <th class="th_name">닉네임</th>
                <th class="th_ordered">주문 내역</th>
                <th class="th_content">내용</th>
                <th class="th_rating">평점</th>
                <th class="th_date">날짜</th>
							<tbody class="review_body">
							<c:forEach var="rec" items="${review }">
							 <tr class="content">
                    <td class="c_name">${rec.REVIEW_NICKNAME }</td>
                    <td class="c_ordered">${rec.REVIEW_ITEMS }</td>
                    <td class="c_content">${rec.REVIEW_CONTENT }</td>
                    <td class="c_rating">
                       <c:forEach var="re" begin="1" end="${rec.REVIEW_STAR }">
                       ★
                       </c:forEach>
                    </td>
                    <td class="c_date"><fmt:formatDate value="${rec.REVIEW_DATE }" pattern="yyyy-MM-dd HH:mm" type="time"/></td>
                </tr>
                <c:if test="${rec.REVIEW_CMT==1}">
                 <tr class="content reply">                  
                    <td class="no_content">
                        <div class="indent_border"></div>
                    </td>
                    <td class="no_content"></td>
                    <td class="c_content">${rec.commentVO.COMMENT_CONTENT }</td>
                    
                    <td class="no_content"></td>
                    <td class="c_date"><fmt:formatDate value="${rec.commentVO.COMMENT_DATE }" pattern="yyyy-MM-dd HH:mm" type="time"/></td>
                </tr>
                </c:if>
							</c:forEach>
               </tbody>

                

            </table>

            <div class="pagination">
            <c:if test="${pc.prev }">
            
                <a href="#">&laquo;</a>
            </c:if>
            <c:forEach var="rec" begin="${pc.startPage }" end="${pc.endPage }">
            <c:if test="${rec eq pc.rc.reqPage }">
            
       			<a href="#" class="active">${rec }</a>
            </c:if>
            <c:if test="${rec ne pc.rc.reqPage }">
            <a href="#" >${rec }</a>
            </c:if>
       	    </c:forEach>
            <c:if test="${pc.next }">
                <a href="#">&raquo;</a>
            </c:if>
              </div>
         
        </div>
    </div>
  <c:if test="${cafevo.cafe_onlinesale==1}">
        <button class="order"><a href="http://localhost:9080/cafeinme/shop/itemList/${cafevo.cafe_no }">주문하러 가기 ></a></button>
     </c:if>
 </div>


</div>
<!-- footer -->
<%@ include file="/WEB-INF/views/include/footer.jsp"%>
<script>
const jsonobj = ${cafevo.cafe_no};
let endpage = ${pc.endPage };
let startpage = ${pc.startPage };
</script>



</body>
</html>