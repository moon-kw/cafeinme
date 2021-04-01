<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<!--common -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>


<script defer src="${contextPath }/js/include/uppermost.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<link rel="stylesheet" href="/cafeinme/css/reset/reset.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="${contextPath }/css/reviews/writeReviewForm.css" />
<script defer src="${contextPath }/js/reviews/writeReviewForm.js"></script>
</head>
<body>
    <button id="btn1">버튼</button>
    <script>
        // showModal(title, description, yesBtnLabel = 'Yes', noBtnLabel = 'Cancel', callbackAfterClickingYesBtn);
        document.getElementById('btn1').onclick = () => showModal( () => {
            console.log('File deleted successfully');
        });
    </script>

    <!-- <div class="body">
        <div class="main">
            <form action="${contextPath }/shop/writeReview" method="POST">
                <div class="container">
                    <div class="star-widget">
                    <input type="radio" name="rate" id="rate-5">
                    <label for="rate-5" class="fas fa-star"></label>
                    <input type="radio" name="rate" id="rate-4">
                    <label for="rate-4" class="fas fa-star"></label>
                    <input type="radio" name="rate" id="rate-3">
                    <label for="rate-3" class="fas fa-star"></label>
                    <input type="radio" name="rate" id="rate-2">
                    <label for="rate-2" class="fas fa-star"></label>
                    <input type="radio" name="rate" id="rate-1">
                    <label for="rate-1" class="fas fa-star"></label>
                    <header></header>
                    <div class="textarea">
                        <textarea name="REVIEW_CONTENT" id="REVIEW_CONTENT" cols="30" rows="10" placeholder="이용 후기를 남겨주세요"></textarea>
                    </div>
                    <div class="btn">
                        <button id="btn_confirm">확인</button>
                    </div>
                    </div>
                </div>
            </form>
        </div>
    </div> -->
</body>
</html>