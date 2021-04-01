    <title>Document</title>
    <script defer src="${contextPath }/js/common/ajax.js"></script>
</head>

<body>
    <button id="btn">버튼</button>
    <div id="msgStack"></div>



    <script>
        const $msgStack = document.getElementById('msgStack');
        document.getElementById('btn').addEventListener("click", onMessage);
        function onMessage(evt) {
            var data = evt.data;
            // toast
            let toast = "<div class='toast' role='alert' aria-live='assertive' aria-atomic='true'>";
            toast += "<div class='toast-header'><i class='fas fa-bell mr-2'></i><strong class='mr-auto'>알림</strong>";
            toast += "<small class='text-muted'>just now</small><button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>";
            toast += "<span aria-hidden='true'>&times;</span></button>";
            toast += "</div> <div class='toast-body'> 주문이 들어왔습니다 </div></div>";
            $msgStack.append(toast);   // msgStack div에 생성한 toast 추가
            document.querySelector('.toast').toast({ "animation": true, "autohide": false });
            document.querySelector('.toast').toast('show');
        };
    </script>
</body>

</html>