<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

  <div class="navContainer">
    <nav id="cafeNav">
      <h4 class="mycafe-gnb cafe-gnb">내 카페 관리</h4>
      <ul class="mycafe-group-cafe">
        <li class="mycafe-lnb"><a href="${contextPath }/cafe/modifyForm">내 카페 보기</a></li>
        <li class="mycafe-lnb"><a href="${contextPath }/cafe/menuForm">카페 메뉴 관리</a></li>
        <li class="mycafe-lnb"><a href="${contextPath }/cafe/del">내 카페 삭제</a></li>
      </ul>
      <h4 class="mycafe-gnb shop-gnb">온라인 판매 관리</h4>
      <ul class="mycafe-group-shop">
        <li class="mycafe-lnb"><a href="${contextPath }/shop/myShopJoinForm">내 카페 온라인 판매 등록</a></li>
        <li class="mycafe-lnb"><a href="${contextPath }/cafe/onlinemenuForm">온라인 판매 메뉴 관리</a></li>
        <li class="mycafe-lnb"><a href="${contextPath }/shop/orderManagement">실시간 판매 관리</a></li>
        <li class="mycafe-lnb"><a href="${contextPath }/reviews/commentWriteForm">내 카페 리뷰</a></li>
        <li class="mycafe-lnb"><a href="${contextPath }/shop/salesManagement">판매 내역</a></li>
        <li class="mycafe-lnb"><a href="${contextPath }/shop/del">온라인 판매 철회</a></li>
      </ul>
      <button id="cafeNav_toggleBtn"><i class="fas fa-angle-down"></i></button>
    </nav>
  </div>