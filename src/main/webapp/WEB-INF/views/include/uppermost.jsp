<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!--네비게이션-->
    <nav id="nav">
      <ul class="nav-item">
        <li class="nav-item-toplogo">
          <a href="/cafeinme" class="logo-light"><img src="${contextPath }/img/index_img/logo_final1.png" alt=""></a>
          <a href="/cafeinme" class="logo-dark"><img src="${contextPath }/img/index_img/logo_배투.png" alt=""></a>
        </li>
        <li class="nav-item-menu">
          <a href="${contextPath }/search">Search</a>
          <a href="${contextPath }/member/mybookmark">Bookmark</a>
          <a href="${contextPath }/shop">Online Shop</a>
        </li>
        <li class="nav-item-cart">
          <a href="${contextPath }/shop/cart" class="cart-light"><img src="${contextPath }/img/index_img/shopping-cart.png" alt=""></a>
          <a href="${contextPath }/shop/cart" class="cart-dark"><img src="${contextPath }/img/index_img/shopping-cart-dark.png" alt=""></a>
        </li>
      <!-- 로그인 전 -->
      <c:if test="${empty sessionScope.members }">
        <li class="nav-item-mem">
                <a href="${contextPath }/loginForm">LOG IN</a>
                |
                <a href="${contextPath }/member/memberJoinForm">SIGN UP</a>
        </li>
        </c:if>
        
        <!-- 로그인 후 -->
        <c:if test="${!empty sessionScope.members }">
        <li class="nav-item-mem">
                <a href="${contextPath }/member/myPage">${sessionScope.members.member_nickname}</a>
                |
                <a href="${contextPath }/logout">SIGN OUT</a>
        </li>
      </c:if>
        <li class="nav-item-btn">
          <button class="main_toggleBtn"><i class="fas fa-bars"></i></button>
        </li>
      </ul>
    </nav>
    <!--내비게이션 끝-->

    <script>
      var socket = null;
      connect();
      function connect() {
          const url = 'ws://localhost:9080/cafeinme/web-socket';
    
          const ws = new WebSocket(url);
          socket = ws;
          ws.addEventListener("open",() => {
              console.log('연결 성공!!');
          });
      }
    </script>