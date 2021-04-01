<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--common -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<!-- nav -->
<%@ include file="/WEB-INF/views/include/uppermost.jsp"%>
<link rel="stylesheet" href="${contextPath }/css/include/uppermost.css" />
<script defer src="${contextPath }/js/uppermost.js"></script>

  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Document</title>
</head>
<body>
  <script>
    const getLocation = () => {
  if (navigator.geolocation) {
    // GPS를 지원하면
    navigator.geolocation.getCurrentPosition(
      (position) => {
        // position 객체 내부에 timestamp(현재 시간)와 coords 객체
        const time = new Date(position.timestamp);
        console.log(position);
        console.log(`현재시간 : ${time}`);
        console.log(`latitude 위도 : ${position.coords.latitude}`);
        console.log(`longitude 경도 : ${position.coords.longitude}`);
        console.log(`altitude 고도 : ${position.coords.altitude}`);
      },
      (error) => {
        console.error(error);
      },
      {
        enableHighAccuracy: false,
        maximumAge: 0,
        timeout: Infinity,
      }
    );
  } else {
    alert("GPS를 지원하지 않습니다");
  }
};

getLocation();
  </script>
</body>
</html>