'use strict';
/**
 * find Member ID Form.js
 */

const $findIDBtn = document.getElementById('findIDBtn');
const $findedId = document.querySelector('.findedId');
const $name = document.getElementById('member_name');
const $tel = document.getElementById('member_tel');
const url = "/cafeinme/member/member_id";

$findIDBtn.addEventListener('click', findId);


function findId(evt) {
  evt.preventDefault();	
  const requestUrl = `${url}?member_name=${$name.value}&member_tel=${$tel.value}`;
  console.log(requestUrl);

  //유효성 체크
  if(validChk()) {
    ajaxCall.get(requestUrl,handler);
  }

}


function handler(jsonObj){
	if(jsonObj.rtcd === '00'){
		$findedId.textContent = '고객님의 아이디는 ' + jsonObj.result + '입니다.';
	}else{
		$findedId.textContent = jsonObj.errmsg;
	}
}

function validChk(){
  let result = true;

  if($name.value.trim().length == 0){
    $findedId.textContent = '이름을 입력하세요';
    $findedId.style.color = '#ff0000'
    $name.focus();
    $name.select();
    return false;
  }

  if($tel.value.trim().length == 0){
    $findedId.textContent = '전화번호를 입력하세요.';
    $findedId.style.color = '#ff0000'
    $tel.focus();
    $tel.select();
    return false;
  }

  return result;
}