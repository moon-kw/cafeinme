'use strict';
window.addEventListener("load", itemprice());
const $MEMBER_ID = document.getElementById("MEMBER_ID");
const $address = document.getElementById('address');
const $paymentBtn = document.getElementById('paymentBtn');
const $roadAddr = document.getElementById('roadAddr');
const $addrDetail = document.getElementById('addrDetail');
const $err_msg = document.getElementById('err_msg');

//상품 갯수 가격 계산 함수
function itemprice() {
    const $MENU_NO = document.querySelectorAll(".MENU_NO");
    Array.from($MENU_NO).forEach(function (ele) {
        parent = ele.parentElement;
        let $MENU_COUNT = parent.querySelector(".MENU_COUNT").textContent;
        let $MENU_PRICE = parent.querySelector(".MENU_PRICE").value;
        let $price = parent.querySelector(".price");

        $price.textContent = Number($MENU_COUNT) * Number($MENU_PRICE);
    });
    totalprice();
};

//상품 갯수 가격 총 합계 계산 함수
function totalprice() {
    const $ORDER_PRICE = document.getElementById("ORDER_PRICE");
    const $price = document.querySelectorAll(".price");
    let ORDER_PRICE = 0;
    Array.from($price).forEach(function (ele) {
        // console.log(ele);
        // console.log(ele.value);
        // console.log(ele.textContent);
        // console.log(ele.textContent.value);
        ORDER_PRICE += Number(ele.textContent);
    });
    // console.log(ORDER_PRICE);
    $ORDER_PRICE.value = ORDER_PRICE;
};

//주문버튼 이벤트
$paymentBtn.addEventListener("click", orderPayment);

//
function orderPayment(evt) {
    evt.preventDefault();
    console.log($roadAddr.value.trim().length);
    console.log($addrDetail.value.trim().length);
    if(vaildChk()){
        orderForm.submit();
    }
};

//주소API 검색창 열기
$address.addEventListener("click", function(){
    window.open("/cafeinme/shop/juso","pop","width=570,height=420, scrollbars=yes, resizable=yes");
});

//주소API에서 주소값 받아오기
// document.domain = "http://localhost:9080/";
function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn
    , detBdNmList, bdNm, bdKdcd, siNm, sggNm, emdNm, liNm, rn, udrtYn, buldMnnm, buldSlno, mtYn, lnbrMnnm, lnbrSlno, emdNo){
// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
    $roadAddr.value = roadAddrPart1;
    $addrDetail.value = addrDetail;
}

function vaildChk(){
    let result = true;
    if($roadAddr.value.trim().length == 0){
        $err_msg.textContent = "주소를 입력하세요";
        return false;
    }
    if($addrDetail.value.trim().length == 0){
        $err_msg.textContent = "상세주소를 입력하세요";
        return false;
    }
    return result;
}