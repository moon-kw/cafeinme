'use strict';
/**
 * find Member PW Form.js
 * 메일로 임시 비밀번호 전송
 */

const $findPwBtn = document.getElementById('findPwBtn');
const $findedPw = document.querySelector('.findedPw');
const url = "/cafeinme/member/member_pw"
const $member_id = document.getElementById('member_id');
const $member_tel = document.getElementById('member_tel');

$findPwBtn.addEventListener('click', findPw);

function findPw(evt) {
  
  if(validChk()){
    //ajax 호출
    const jsonObj = {
      member_id : $member_id.value,
      member_tel : $member_tel.value
    }
    console.log(jsonObj);

    $findPwBtn.lastChild.textContent = '조회중...';
    $findPwBtn.disabled = true;
    $findPwBtn.querySelector('span').classList.remove('visually-hidden');

    ajaxCall.post(url,jsonObj,handler)
  }
}

function handler(jsonObj){
    setTimeout(()=>{
		if(jsonObj.rtcd === '00'){
			$findedPw.textContent = '임시 비밀번호는 ' + jsonObj.result + ' 입니다';
		}else{
			$findedPw.textContent = jsonObj.errmsg;
		}
		
		$findPwBtn.lastChild.textContent = '비밀번호찾기';
		$findPwBtn.disabled = false;  //버튼활성화
		$findPwBtn.querySelector('span').classList.add('visually-hidden');
	},500);

}

function validChk(){
  let result = 'true';

  if($member_id.value.trim().length == 0) {
    $findedPw.textContent = '아이디를 입력하세요';
    $member_id.focus();
    $member_id.select();
    return false;
  }

  if($member_tel.value.trim().length == 0) {
    $findedPw.textContent = '전화번호를 입력하세요';
    $member_tel.focus();
    $member_id.select();
    return false;
  }

  return result;
}