'use strict';

const $btn_today = document.getElementById('btn_today');
const $btn_oneweak = document.getElementById('btn_oneweak');
const $btn_onemonth = document.getElementById('btn_onemonth');
const $btn_threemonth = document.getElementById('btn_threemonth');
const $input_startDt = document.getElementById('input_startDt');
const $input_endDt = document.getElementById('input_endDt');
const $btn_search = document.getElementById('btn_search');
const $select_orderStatus = document.getElementById('select_orderStatus');

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

//날짜세팅 버튼 이벤트
$btn_today.addEventListener("click", function(){getListDate(0)});
$btn_oneweak.addEventListener("click", function(){getListDate(1)});
$btn_onemonth.addEventListener("click", function(){getListDate(2)});
$btn_threemonth.addEventListener("click", function(){getListDate(3)});

$btn_search.addEventListener("click", function(){
    insertDate(1);
});

function insertDate(reqPage){
    const CAFE_Num = document.getElementById('CAFE_NO').value;
    //날짜값에 '-' 제거
    const StartDt = $input_startDt.value.replaceAll('-','');
    const EndDt = $input_endDt.value.replaceAll('-','');

    //주문상태
    const optionIdx = $select_orderStatus.options.selectedIndex;
    const optionValue = $select_orderStatus.options[optionIdx].value;

    search(CAFE_Num, StartDt, EndDt, optionValue, reqPage);
};

//판매목록조회
function search(CAFE_NO, StartDt, EndDt, optionValue, reqPage){
    const url = `http://localhost:9080/cafeinme/shop/salesManagement/${CAFE_NO}/${StartDt}/${EndDt}/${optionValue}/${reqPage}`;
    ajaxCall.get(url, handler);
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

function handler(jsonObj){
	// console.log(jsonObj);
    const headers = jsonObj.orderList;
    const items   = jsonObj.orderDetailList;
    const pc = jsonObj.pc;
    let listItem = "";
    // let cnt = 0;
    let settlementCnt = 0;
    let totalprice = 0;
    for(let rec of headers){
    // console.log(rec);
    // console.log(rec.member_ID);
        listItem += `<tr>`;
        listItem += ` <td>${rec.order_NO}</td>`;
        listItem += ` <td>${rec.member_ID}</td>`;

        listItem += ` <td>`;
        Array.from(items[rec.order_NO]).forEach(item=>{
           listItem += `${item.menu_NAME} ${item.menu_COUNT}개<br>`;
        });
        listItem += ` </td>`;

        listItem += ` <td>${rec.order_PRICE}원</td>`;
        listItem += ` <td>${rec.order_DATE}</td>`;
        if(rec.order_ADDRESS2 == null){
            listItem += ` <td>${rec.order_ADDRESS1}</td>`;
        }else{
            listItem += ` <td>${rec.order_ADDRESS1} ${rec.order_ADDRESS2}</td>`;
        }
        
        listItem += ` <td>${rec.order_PAYMENTTYPE}</td>`;
        listItem += ` <td>${rec.order_RECEIVETYPE}</td>`;
        listItem += ` <td>${rec.order_STATUS}</td>`;
        listItem += `</tr>`;

        if(rec.order_STATUS == "주문완료" || rec.order_STATUS == "배달완료"){
            totalprice += rec.order_PRICE;
            settlementCnt++;
        }
    }
    document.getElementById('List').innerHTML = listItem;
    document.getElementById('totalprice').textContent = totalprice + "원";
    document.getElementById('settlementCnt').textContent = settlementCnt + "건";

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