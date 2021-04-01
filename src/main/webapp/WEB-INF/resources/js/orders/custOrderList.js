const $btn_today = document.getElementById('btn_today');
const $btn_oneweak = document.getElementById('btn_oneweak');
const $btn_onemonth = document.getElementById('btn_onemonth');
const $btn_threemonth = document.getElementById('btn_threemonth');

const $input_startDt = document.getElementById('input_startDt');
const $input_endDt = document.getElementById('input_endDt');

const $btn_search = document.querySelector('.btn_search');

// window.addEventListener("load", insertDate);

//현재날짜로 기본값 주기
$input_startDt.value = getFormatDate(new Date());
$input_endDt.value = getFormatDate(new Date());

//날짜 계산기
function dateCalculator(dateType, x){
    let date = new Date();
    if(dateType === "month"){
        date = date.setMonth(date.getMonth() - x);
        return getFormatDate(new Date(date));
    }
    if(dateType === "day"){
        date = date.setDate(date.getDate() - x);
        return getFormatDate(new Date(date));
    }
};
//날짜 포멧 함수
function getFormatDate(date){
    let year = date.getFullYear();              //yyyy
    let month = (1 + date.getMonth());          //M
    month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
    let day = date.getDate();                   //d
    day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
    return  year + '-' + month + '-' + day;       //'-' 추가하여 yyyy-mm-dd 형태 생성 가능
};

$btn_search.addEventListener("click", function(){
    insertDate(1);
});

//구매목록 함수 불러오기전 주소에 넣을 데이터 정의하는 함수
function insertDate(reqPage) {
    //날짜값에 '-' 제거
    const startDt = $input_startDt.value.replaceAll('-', '');
    const endDt = $input_endDt.value.replaceAll('-', '');
    search(startDt, endDt, reqPage);
};

//구매목록 함수
function search(startDt, endDt, reqPage) {
    const url = `http://localhost:9080/cafeinme/shop/getOrderList/${startDt}/${endDt}/${reqPage}`;
    ajaxCall.get(url, handler);
};

//구매목록 ajax핸들러
function handler(jsonObj) {
    const headers = jsonObj.orderList;
    const pc = jsonObj.pc;

    let list = "";
    for (let rec of headers) {
        list += `<tr> `;
        list += ` <input type="hidden" class="cafeNo" value="${rec.cafe_NO}">`;
        list += `    <td class="order_detail td_detail"> `;
        list += `    <button class="detailBtn" value="${rec.order_NO}">상세보기</button> `;
        list += `    </td> `;
        list += `    <td class="order_date td_date">${rec.order_DATE}</td> `;
        list += `    <td class="order_cafe td_cafe"> `;
        list += `    <a href="/cafeinme/shop/itemList/${rec.cafe_NO}">${rec.cafe_NAME}</a> `;
        list += `    </td> `;
        list += `    <td class="order_menu td_menu">${rec.order_ITEMS}</td> `;
        list += `    <td class="order_price td_price">${rec.order_PRICE}원</td> `;
        list += `    <td class="order_status td_status">${rec.order_STATUS}</td> `;
        list += `    <td class="order_receive td_receive">${rec.order_PAYMENTTYPE} / ${rec.order_RECEIVETYPE}</td> `;
        list += `    <td class="order_review td_review rbtnbox"> `;
        if(rec.order_STATUS != '주문거부'){
            if (rec.order_REVIEW_YN == 0) {
                list += `    <button class="writeReviewBtn" value="${rec.order_NO}">리뷰쓰기</button> `;
            } else {
                list += `    <button class="viewReviewBtn" value="${rec.order_NO}">리뷰보기</button> `;
            }
        }
        list += `    </td> `;
        list += `</tr> `;
    }
    document.getElementById('List').innerHTML = list;

    let page = "";
    if(pc.prev){
        // page += `<a class="page-item page-first" href="1">처음</a>`;
        // page += `<a class="page-item page-prev" href="${pc.startPage - 1}">이전</a>`;
        page += `<li class="page-item page-first" value="1">처음</li>`;
        page += `<li class="page-item page-prev" value="${pc.startPage - 1}">이전</li>`;
    }
    for(let i = pc.startPage; i<= pc.endPage; i++){
        // page += `<a class="page-item" href="${i}">${i}</a>`;
        if(i == pc.rc.reqPage) {
            page += `<li class="page-item active" value="${i}">${i}</li>`;
        }else{
            page += `<li class="page-item" value="${i}">${i}</li>`;
        }
        
    }
    if(pc.next){
        // page += `<a class="page-item page-next" href="${pc.endPage + 1}">다음</a>`;
        // page += `<a class="page-item page-end" href="${pc.finalEndPage}">끝</a>`;
        page += `<li class="page-item page-next" value="${pc.endPage + 1}">다음</li>`;
        page += `<li class="page-item page-end" value="${pc.finalEndPage}">끝</li>`;
    }
    document.querySelector('.pageBtn-ul').innerHTML = page;
};

//날짜세팅 버튼 이벤트
$btn_today.addEventListener("click", function () { getListDate(0) });
$btn_oneweak.addEventListener("click", function () { getListDate(1) });
$btn_onemonth.addEventListener("click", function () { getListDate(2) });
$btn_threemonth.addEventListener("click", function () { getListDate(3) });

//날짜 세팅 함수
function getListDate(idx) {
    $input_endDt.value = getFormatDate(new Date());
    if (idx == 0) {
        $input_startDt.value = getFormatDate(new Date());
        $btn_search.click();
        return;
    }
    if (idx == 1) {
        $input_startDt.value = dateCalculator("day",6);
        $btn_search.click();
        return;
    }
    if (idx == 2) {
        $input_startDt.value = dateCalculator("month",1);
        $btn_search.click();
        return;
    }
    if (idx == 3) {
        $input_startDt.value = dateCalculator("month",3);
        $btn_search.click();
        return;
    }
};
//구매목록 이벤트
document.getElementById('List').addEventListener("click", listClickHandler);

//구매목록 버튼클릭 이벤트 함수
function listClickHandler(evt) {
    if (evt.target.classList.contains('detailBtn')) {
        const orderNo = evt.target.value;
        const url = `/cafeinme/shop/orderDetailList/${orderNo}`;
        ajaxCall.get(url, getOrderDetailList);
    };
    if (evt.target.classList.contains('writeReviewBtn')) {
        postReview(evt);
    };
    if (evt.target.classList.contains('viewReviewBtn')){
        const orderNo = evt.target.value;
        const url = `/cafeinme/review/getOneReview/${orderNo}`;
        ajaxCall.get(url, getReview);
    }
};

//페이지버튼 이벤트
document.getElementById('pageBtn').addEventListener("click", pageBtnClickHandler);
function pageBtnClickHandler(evt) {
    evt.preventDefault();
    if (evt.target.classList.contains('page-item')){
        const reqPage = evt.target.value;
        insertDate(reqPage);
    }
};

//모달 전역 변수 정의
var modalWrap = null;
//리뷰 작성 함수(모달)
const postReview = (evt) => {
    
    const oColumn = evt.target.parentElement.parentElement;
    if (modalWrap !== null) {
        modalWrap.remove();
    }

    modalWrap = document.createElement('div');
    modalWrap.className = 'reviewForm';
    modalWrap.innerHTML = `
    <div class="modal fade" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
            <div class="modal-header bg-light">
                <h5 class="modal-title">리뷰 작성</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
                <div class="modal-body">
                    <div class="rmain">
                        <div class="rform">
                            <div class="rcontainer">
                                <div class="star-widget">
                                <input type="radio" name="rate" id="rate-5" class="rate-5">
                                <label for="rate-5" class="fas fa-star"></label>
                                <input type="radio" name="rate" id="rate-4" class="rate-4">
                                <label for="rate-4" class="fas fa-star"></label>
                                <input type="radio" name="rate" id="rate-3" class="rate-3">
                                <label for="rate-3" class="fas fa-star"></label>
                                <input type="radio" name="rate" id="rate-2" class="rate-2">
                                <label for="rate-2" class="fas fa-star"></label>
                                <input type="radio" name="rate" id="rate-1" class="rate-1">
                                <label for="rate-1" class="fas fa-star"></label>
                                <header></header>
                                <div class="textarea">
                                    <textarea name="REVIEW_CONTENT" class="REVIEW_CONTENT" cols="30" rows="10" placeholder="이용 후기를 남겨주세요"></textarea>
                                </div>
                                <div class="btn">
                                    <button id="btn_confirm" class="btn_confirm" data-bs-dismiss="modal">확인</button>
                                </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
`;

    // modalWrap.querySelector('.btn_confirm').onclick = postReview();
    document.body.append(modalWrap);

    
    var modal = new bootstrap.Modal(modalWrap.querySelector('.modal'));
    modal.show();

    modalWrap.querySelector('.btn_confirm').addEventListener("click", function(){

        const reviewContent = modalWrap.querySelector('.REVIEW_CONTENT').value;
        const orderItem = oColumn.querySelector('.order_menu').textContent;
        const cafeNo = oColumn.querySelector('.cafeNo').value;
        const orderNo = evt.target.value;
        let starRate = 0;

        if (modalWrap.querySelector('.rate-1').checked == true) {
            starRate = 1;
        }
        if (modalWrap.querySelector('.rate-2').checked == true) {
            starRate = 2;
        }
        if (modalWrap.querySelector('.rate-3').checked == true) {
            starRate = 3;
        }
        if (modalWrap.querySelector('.rate-4').checked == true) {
            starRate = 4;
        }
        if (modalWrap.querySelector('.rate-5').checked == true) {
            starRate = 5;
        }

        if(reviewContent.trim() != "" && starRate > 0){
            const url = `/cafeinme/review/writeReview`;
            const jsonObj = {
                review_content : reviewContent,
                review_star : starRate,
                review_items : orderItem,
                cafe_no : cafeNo,
                order_no : orderNo,
            }
            ajaxCall.post(url, jsonObj, handler1);
        }

        function handler1(jsonObj){
            if(jsonObj.rtcd === '00'){
                oColumn.querySelector('.rbtnbox').innerHTML = `<button class="viewReviewBtn" value="${orderNo}">리뷰보기</button>`;
                alert(`${jsonObj.msg}`);
            }
            if(jsonObj.rtcd === '01'){
                alert(`${jsonObj.msg}`);
            }
        }

    });
};
//리뷰 보기 함수(모달)
const getReview = (jsonObj) => {

    const result = jsonObj.result;

    if (modalWrap !== null) {
        modalWrap.remove();
    }

    modalWrap = document.createElement('div');
    modalWrap.className = 'reviewForm';
    modalWrap.innerHTML = `
    <div class="modal fade" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
            <div class="modal-header bg-light">
                <h5 class="modal-title">리뷰 보기 및 수정</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
                <div class="modal-body">
                    <div class="rmain">
                        <div class="rform">
                            <div class="rcontainer">
                                <input type="hidden" class="rOrder_NO" value="${result.order_NO}">
                                <div class="star-widget">
                                <input type="radio" name="rate" id="rate-5" class="rate-5">
                                <label for="rate-5" class="fas fa-star"></label>
                                <input type="radio" name="rate" id="rate-4" class="rate-4">
                                <label for="rate-4" class="fas fa-star"></label>
                                <input type="radio" name="rate" id="rate-3" class="rate-3">
                                <label for="rate-3" class="fas fa-star"></label>
                                <input type="radio" name="rate" id="rate-2" class="rate-2">
                                <label for="rate-2" class="fas fa-star"></label>
                                <input type="radio" name="rate" id="rate-1" class="rate-1">
                                <label for="rate-1" class="fas fa-star"></label>
                                <header></header>
                                <div class="textarea">
                                    <textarea name="REVIEW_CONTENT" class="REVIEW_CONTENT" cols="30" rows="10">${result.review_CONTENT}</textarea>
                                </div>
                                <div class="btn">
                                    <button id="btn_confirm" class="btn_confirm" data-bs-dismiss="modal">확인</button>
                                </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    `;

    document.body.append(modalWrap);

    var modal = new bootstrap.Modal(modalWrap.querySelector('.modal'));
    modal.show();

    for(let i = 1; i <= 5; i++){
        if(result.review_STAR == i){
            modalWrap.querySelector(`.rate-${i}`).checked = true;
        }
    };

    modalWrap.querySelector('.btn_confirm').addEventListener("click", function(){

        const reviewContent = modalWrap.querySelector('.REVIEW_CONTENT').value;
        const orderNo = modalWrap.querySelector('.rOrder_NO').value;
        let starRate = 0;

        if (modalWrap.querySelector('.rate-1').checked == true) {
            starRate = 1;
        }
        if (modalWrap.querySelector('.rate-2').checked == true) {
            starRate = 2;
        }
        if (modalWrap.querySelector('.rate-3').checked == true) {
            starRate = 3;
        }
        if (modalWrap.querySelector('.rate-4').checked == true) {
            starRate = 4;
        }
        if (modalWrap.querySelector('.rate-5').checked == true) {
            starRate = 5;
        }

        if(reviewContent.trim() != "" && starRate > 0){
            const url = `/cafeinme/review/modifyReview`;
            const jsonObj = {
                review_content : reviewContent,
                review_star : starRate,
                order_no : orderNo,
            }
            ajaxCall.post(url, jsonObj, handler2);
        }

        function handler2(jsonObj){
            if(jsonObj.rtcd === '00'){
                alert(`${jsonObj.msg}`);
            }
            if(jsonObj.rtcd === '01'){
                alert(`${jsonObj.msg}`);
            }
        }

    });
};
//주문상세 함수(모달)
function getOrderDetailList(jsonObj){

    const items = jsonObj.items;

    if (modalWrap !== null) {
        modalWrap.remove();
    }

    let modalItem = "";

    modalItem += `<div class="modal fade" tabindex="-1"> `;
    modalItem += `  <div class="modal-dialog"> `;
    modalItem += `    <div class="modal-content"> `;
    modalItem += `      <div class="modal-header bg-light"> `;
    modalItem += `        <h5 class="modal-title">주문 상세</h5> `;
    modalItem += `        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button> `;
    modalItem += `      </div> `;
    modalItem += `      <div class="modal-body"> `;
    modalItem += `        <table class="table"> `;
    modalItem += `            <thead> `;
    modalItem += `                <tr> `;
    modalItem += `                    <th scope="col">메뉴이름</th> `;
    modalItem += `                    <th scope="col">메뉴 수</th> `;
    modalItem += `                    <th scope="col">메뉴 가격</th> `;
    modalItem += `                </tr> `;
    modalItem += `            </thead> `;
    modalItem += `            <tbody> `;
    for(let rec of items){
        modalItem += `                <tr> `;
        modalItem += `                    <td>${rec.menu_NAME}</td> `;
        modalItem += `                    <td>${rec.menu_COUNT}개</td> `;
        modalItem += `                    <td>${rec.menu_COUNT * rec.menu_PRICE}원</td> `;
        modalItem += `                </tr> `;
    }
    modalItem += `           </tbody> `;
    modalItem += `        </table> `;
    modalItem += `      </div> `;
    modalItem += `      <div class="modal-footer bg-light"> `;
    modalItem += `        <button type="button" class="modal-btn" data-bs-dismiss="modal">확인</button> `;
    modalItem += `      </div> `;
    modalItem += `    </div> `;
    modalItem += `  </div> `;
    modalItem += `</div>  `;

    modalWrap = document.createElement('div');
    modalWrap.className = 'detailForm';
    modalWrap.innerHTML = modalItem;
    document.body.append(modalWrap);

    var modal = new bootstrap.Modal(modalWrap.querySelector('.modal'));
    modal.show();
}