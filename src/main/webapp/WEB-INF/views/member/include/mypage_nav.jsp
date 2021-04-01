<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

  <div class="nav_body">
    <div class="mypage" id="mypage">
      <span class="myPage_title">마이페이지</span>
        <div class="topmenu">
             <div class="profileline">
              
 
                 <div class="profile_photo">
                     <img src="${contextPath }/img/favicon.png" alt="X.">
                 </div>
                 <div class="profile_name">
                    ${sessionScope.members.member_nickname}
                 </div>
 
        </div>
 
         <div class="hr"></div>
 
            <div class="menuline">
                 <div class="menu menu1">
                    <a href="${contextPath }/member/memberModifyForm" tabindex="1">내 계정</a>
                 </div>
                 <div class="menu menu2">
                    <a href="${contextPath }/member/changePWForm" tabindex="1">비밀번호 변경</a>
                 </div>
                 <div class="menu menu3">
                    <a href="${contextPath }/member/mybookmark" tabindex="2">북마크</a>
                 </div>
                 <div class="menu menu4">
                     <a href="${contextPath }/shop/orderList" tabindex="3">내 구매 내역</a>
                 </div>
                 <div class="menu menu5">
                     <a href="${contextPath }/reviews/myreview" tabindex="4">내가 쓴 리뷰</a>
                 </div>
                 <div class="menu menu6">
                     <a href="${contextPath }/cafe/myCafe" tabindex="5">내 카페</a>
                 </div>
                 <div class="menu menu7">
                     <a href="${contextPath }/member/delMemberForm" tabindex="6">회원 탈퇴</a>
                 </div>
 
            </div>
 
        </div>
    </div>
    <button id="myPage_toggleBtn"><i class="fas fa-angle-down"></i></button>
  </div>