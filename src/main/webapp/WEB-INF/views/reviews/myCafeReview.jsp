<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!--common -->
<%@ include file="/WEB-INF/views/include/common.jsp" %>

<title>내 카페 메뉴 관리 | Cafe In Me</title>
<!-- nav -->
<link rel="stylesheet" href="${contextPath }/css/include/uppermost.css" />
<script defer src="${contextPath }/js/include/uppermost.js"></script>
<!-- footer -->
<link rel="stylesheet" href="${contextPath }/css/include/footer.css" />

<!-- 카페 네비게이션 -->
<link rel="stylesheet" href="${contextPath }/css/mycafe/include/myCafeNav.css" />
<script defer src="${contextPath }/js/mycafe/include/myCafeNav.js"></script>

<!-- CSS JS -->
<script defer src="${contextPath }/js/common/ajax.js"></script>
<link rel="stylesheet" href="${contextPath }/css/reviews/myCafeReview.css" />
<script defer src="${contextPath }/js/reviews/myCafeReview.js"></script>
</head>

<body>
  <!-- nav -->
  <%@ include file="/WEB-INF/views/include/uppermost.jsp" %>
    <!-- 현재위치 -->
    <div class="pageTitle">
      <a href="${contextPath}/cafe/myCafe">온라인 판매 관리</a> > <span class="now">내 카페 리뷰 보기</span>
    </div>

    <section class="main-container">

      <!-- 카페 네비게이션 -->
      <%@ include file="/WEB-INF/views/mycafe/include/myCafeNav.jsp" %>


        <article class="reviewArticle">
          <div class="container">
            <h3 class="title">내 카페 리뷰 보기</h3>
            <!--리뷰박스-->
            <c:forEach var="rec" items="${review }">
              <ul class="reviewBox">
                <input type="hidden" name="review_no" class="review_no" value="${rec.REVIEW_NO}">
                <i class="fas fa-comments"></i>
                <li class="write-info">
                  <span class="info-writer">${rec.REVIEW_NICKNAME }</span>
                  <span class="info-date"><fmt:formatDate value="${rec.REVIEW_DATE }" pattern="yyyy-MM-dd HH:mm" type="time"/></span>
                </li>
                <li class="star-widget">
                  <c:forEach begin="1" end="${rec.REVIEW_STAR }">
                    <input type="radio" name="rate" id="rate-1">
                    <label for="rate-1" class="fas fa-star"></label>
                  </c:forEach>


                </li>
                <li class="reviewText">
                  <textarea name="REVIEW_CONTENT" id="REVIEW_CONTENT" class="review_content" cols="30" rows="7"
                    readonly="true">${rec.REVIEW_CONTENT }</textarea>
                </li>

                <!--코멘트박스-->
                <c:if test="${rec.REVIEW_CMT==1 }">
                  <li class="commentBox active">
                    <input type="hidden" name="comment_no" class="comment_no" value="${rec.COMMENT_NO}">
                    <div class="comment" contenteditable="true">
                      ${rec.COMMENT_CONTENT }
                    </div>
                    <button class="commentBtn">댓글수정</button>
                  </li>
                </c:if>
                <c:if test="${rec.REVIEW_CMT==0 }">
                  <li class="commentBox">
                    <input type="hidden" name="comment_no" class="comment_no" value="nocmt">
                    <div class="comment" contenteditable="true">
                    </div>
                    <button class="commentBtn">댓글달기</button>
                  </li>
                </c:if>
              </ul>
            </c:forEach>
            <div class="pagingBox">
              <ul class="pagingItem">
                <!-- 보여줄 이전페이지가 있는 경우만 보이게  -->
                <c:if test="${pc.prev }">
                  <!-- 첫 페이지 -->
                  <li class="item">
                    <a class="item-link" href="${contextPath }/reviews/commentWriteForm/1">처음</a>
                  </li>
                  <li class="item">
                    <a class="item-link" href="${contextPath }/reviews/commentWriteForm/${pc.startPage-10}">이전</a>
                  </li>
                </c:if>

                <c:forEach var="pageNum" begin="${pc.startPage }" end="${pc.endPage }">
                  <li class="item active" aria-current="page">
                    <a class="item-link" href="${contextPath }/reviews/commentWriteForm/${pageNum}">${pageNum }</a>
                  </li>
                </c:forEach>

                <c:if test="${pc.next }">

                  <li class="item">
                    <a class="item-link" href="${contextPath }/reviews/commentWriteForm/${pc.endPage+1}">다음</a>
                  </li>
                  <li class="item">
                    <a class="item-link" href="${contextPath }/reviews/commentWriteForm/${pc.finalEndPage}">최종</a>
                  </li>
                </c:if>
              </ul>
            </div>

          </div>
        </article>
    </section>
    <!-- footer -->
    <%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>

</html>