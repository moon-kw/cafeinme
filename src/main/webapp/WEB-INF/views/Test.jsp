<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script defer src="/cafeinme/js/websocket/websocket.js"></script>
<style>
@-webkit-keyframes aram{
0%{
height:0px;
}
20%{
height:20px;}
40%{
height:40px;}
60%{
height:60px;}
80%{
height:80px;}
100%{
height:100px;}
}
</style>
</head>
<body>
<div class="mbody"></div>
<button id="btn" data-cafeno="1">주문하기</button>
</body>
</html>