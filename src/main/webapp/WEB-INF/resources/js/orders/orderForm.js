'use strict';
window.addEventListener("load", calculation);

const $form = document.querySelector('.paymentForm');
const $cnum = document.querySelector('.card_item_number');
const $cexp = document.querySelector('.card_item_exp')
const $chkBox = document.querySelector('.card_item_apply');
const $payBtn = document.querySelector('.payBtn');

$form.addEventListener('change', () => {
  const $inputs = document.querySelectorAll('.paymentForm > input[type=text]');
  const $filterdInputs = Array.from($inputs).filter(ele => ele.value.trim().length != 0);


  const beforeCount = $inputs.length;
  const afterCount = $filterdInputs.length;
  console.log(beforeCount, afterCount);

  if (beforeCount == afterCount && $chkBox.checked == true) {
    $payBtn.removeAttribute('disabled');
  } else {
    $payBtn.setAttribute('disabled', '');
  }

})


$payBtn.addEventListener('click', (evt) => {

  //유효성 체크
  if (!validationChk(evt)) return;

  insertData();
  
  //유효성 통과
  orderArticle.submit();
})


/*유효성체크*/
function validationChk() {

  //카드번호 체크
  if (!isCardNum($cnum.value)) {
    alert("카드 번호를 확인해주세요.");
    $cnum.focus();
    return false;
  }

  if (!isExp($cexp.value)) {
    alert("유효기간을 확인해주세요.");
    $cexp.focus();
    return false;
  }

  if(document.getElementById('receiveType1').checked && $roadAddr.value.trim().length == 0){
    alert("주소를 입력해주세요");
    return false;
  }

  return true;
}

const $card_item_number = document.querySelector('.card_item_number');
$card_item_number.addEventListener("keyup", function (evt) {
  let card_val = evt.target.value;
  let tmp = ""; 
  if( card_val.length < 4){ 
    tmp += card_val; 
  }else if(card_val.length < 8){ 
    tmp += card_val.substr(0, 4); 
    tmp += '-'; 
    tmp += card_val.substr(4); 
  }else if(card_val.length < 12){ 
    tmp += card_val.substr(0, 4); 
    tmp += '-'; tmp += card_val.substr(4, 4); 
    tmp += '-'; tmp += card_val.substr(8); 
  }else{ tmp += card_val.substr(0, 4); 
    tmp += '-'; tmp += card_val.substr(4, 4); 
    tmp += '-'; tmp += card_val.substr(8, 4); 
    tmp += '-'; tmp += card_val.substr(12); 
  }

});

//카드 번호 체크
function isCardNum($cnum) {
  const numPattern = /^\d{4}-\d{4}-\d{4}-\d{4}$/;
  return numPattern.test($cnum);
}
//유효기간 체크
function isExp($cexp) {
  const expPattern = /^[0-9]{4}$/
  return expPattern.test($cexp);
}

//수령지 요소
const $roadAddr = document.getElementById('roadAddr');
const $addrDetail = document.getElementById('addrDetail');
const $addressBtn = document.querySelector('.addressBtn');

//주소API 검색창 열기
$addressBtn.addEventListener("click", function () {
  window.open("/cafeinme/shop/juso", "pop", "width=570,height=420, scrollbars=yes, resizable=yes");
});

//주소API에서 주소값 받아오기
// document.domain = "http://localhost:9080/";
function jusoCallBack(roadFullAddr, roadAddrPart1, addrDetail, roadAddrPart2, engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn
  , detBdNmList, bdNm, bdKdcd, siNm, sggNm, emdNm, liNm, rn, udrtYn, buldMnnm, buldSlno, mtYn, lnbrMnnm, lnbrSlno, emdNo) {
  // 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
  $roadAddr.value = roadAddrPart1;
  $addrDetail.value = addrDetail;
}

//주문수량
function calculation() {

  //주문수량
  let num = 0;
  document.querySelectorAll('.MENU_COUNT').forEach(function (ele) {
    num += Number(ele.value);
  });
  document.getElementById('ORDER_COUNT').value = num;

  //주문금액
  let price = 0;
  document.querySelectorAll('.MENU_PRICE').forEach(function (ele) {
    price += Number(ele.value);
  });
  document.getElementById('ORDER_PRICE').value = price;
};

function insertData(){

  //수령방식 값 넣기
  const radioBtn = document.getElementsByName('receiveType');
  Array.from(radioBtn).forEach(function(ele){
    if(ele.checked){
      document.getElementById('ORDER_RECEIVETYPE').value = ele.value;
    }
  });
};

//카드번호 오토하이픈
$cnum.addEventListener("keyup", function(evt){
  let cardNumber = autoHypenCard(evt.target.value);
  $cnum.value = cardNumber;
});

function autoHypenCard(str) {
	str = str.replace(/[^0-9]/g, '');
	let tmp = '';
  
	if (str.length < 5) {
		return str;
	} else if (str.length < 9) {
		tmp += str.substr(0, 4);
		tmp += '-';
		tmp += str.substr(4);
		return tmp;
	} else if (str.length < 13) {
		tmp += str.substr(0, 4);
		tmp += '-';
		tmp += str.substr(4, 4);
    tmp += '-';
		tmp += str.substr(8);
		return tmp;
	}else {
		tmp += str.substr(0, 4);
		tmp += '-';
		tmp += str.substr(4, 4);
		tmp += '-';
		tmp += str.substr(8, 4);
    tmp += '-';
		tmp += str.substr(12, 4);
		return tmp;
	}
};