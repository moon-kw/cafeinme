'use strict'

joinBtn.addEventListener('click',joinHandler);
let dubtnswitch = false;
let nicknameswitch = false;
function joinHandler(evt){
	evt.preventDefault(); // submit 기본 이벤트 취소
	console.log('회원가입클릭됨')
	
	 if(validationChk()){
		joinForm.submit();
	 }
}

function validationChk(){
	
  if(!dubtnswitch){
		errmsg_member_id.textContent = '아이디 중복확인을 해주십시오';
		return false;
	}
	else if(!isEmail(member_id.value)){
		errmsg_member_id.textContent = '아이디의 형식은 이메일입니다. ex)abcd@Email.com';
		return false;
	}
	else{
		errmsg_member_id.textContent = '';
	}
	//비밀번호 유효성 체크
	if(!isPassword(member_pw.value)){
		errmsg_member_pw.textContent = '4~10자리 영문자 숫자만 가능합니다.';
		member_pw.focus(); 
		member_pw.select();
		return false;
	}else{
		errmsg_member_pw.textContent = '';
	}

	//비밀번호 , 비밀번호 확인 일치여부 체크
	if(member_pw.value !== pwchk.value){
		errmsg_pwchk.textContent = '비밀번호 확인이 일치하지 않습니다.';
		pwchk.focus(); 
		pwchk.select();		
		return false;
	}else {
		errmsg_pwchk.textContent = '';
	}

		//생년월일 체크
		if(member_birth.value === ''){
			errmsg_member_birth.textContent = '필수 입력정보입니다.';
			member_birth.focus();
			return false;
		}else{
			errmsg_member_birth.textContent = '';
		}
	if(member_nickname.value.trim().length==0){
		errmsg_member_nickname.textContent = '닉네임을 입력해주십시오'
		return false;
	}
	else{
		errmsg_member_nickname.textContent = '';
	}
	if(!nicknameswitch){
		errmsg_member_nickname.textContent = '닉네임이 중복되었습니다.';
		return false;
	}
	else{
		errmsg_member_nickname.textContent = '';
	}
	if(member_address1.value.trim().length==0){
		errmsg_member_address1.textContent = '주소를 입력해주십시오';
		return false;
	}
	else{
		errmsg_member_address1.textContent = '';
	}

  //전화번호 체크
  if(!isTel(member_tel.value)){
    errmsg_member_tel.textContent = '전화번호 형식에 맞지 않습니다. ex)010-123-456';
    member_tel.focus(); 
		member_tel.select();
    return false;
  }else {
    errmsg_member_tel.textContent = '';
  }
	
	return true;
}
function chknickname(nickname){
	let url = "http://localhost:9080/cafeinme/member/nicknameCheck";
	fetch(url, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',  //전송데이터 타입
			'Accept': 'application/json'         //수신데이터 타입
		},
		body: nickname          //js => json포맷 문자열로변환
	})
		.then(response => response.json())
		.then(json => {isNicknameduplicate(json);})
		.catch(error => console.error(error));
}
member_nickname.addEventListener("blur",(e)=>{
	nicknameswitch = false;
	if(e.target.value.trim().length!=0){

		chknickname(e.target.value);
	}
	else{
		errmsg_member_nickname.textContent = '';
	}
})
function isNicknameduplicate(json){
	if(json.rtcd=='00'){
		errmsg_member_nickname.textContent = '';
		nicknameswitch = true;
	}
	else{
		errmsg_member_nickname.textContent = '닉네임이 중복되었습니다.';
	}
}

//닉네임 유효성체크
//한글 알파벳 대소문자 또는 숫자로 시작하고 끝나며 4 ~10자리인지 검사한다.
function isNickname(nickname){
  const nicknamePattern = /^[가-힣ㄱ-ㅎㅏ-ㅣA-Za-z0-9]{2,10}$/;
  return nicknamePattern.test(nickname); 
}

//이름 유효성체크
//한글 또는 영문 2자리 이상
function isName(name){
  const namePattern = /^[가-힣ㄱ-ㅎㅏ-ㅣA-Za-z]{2,10}$/;
  return namePattern.test(name); 
}

//아이디 체크
function isEmail(email){
  const emailPattern = /^[\w]([-_\.]?[\w])*@[\w]([-_\.]?[\w])*\.[a-zA-Z]{2,3}$/;
  return emailPattern.test(email);
}

//전화번호체크
function isTel(tel){
  const telPattern = /^\d{3}-\d{3,4}-\d{4}$/;
  return telPattern.test(tel); 
}

//숫자인지 체크
function isNum(num){
  const numPattern = /^[\d]*$/;
  return numPattern.test(num);
}

//4~10자리수의 숫자인지 체크
function isNum2(num){
  const numPattern = /^[\d]{4,10}$/;  ///^[0-9]{4,10}$/
  return numPattern.test(num);
}

//비밀번호 체크
//대소문자 또는 숫자로 시작하고 끝나며 4 ~10자리인지 검사한다.
function isPassword(password){
  const passwdPattern = /^[\w]{4,10}$/; // /^[A-Za-z0-9]{4,10}$/
  return passwdPattern.test(password); 
}

const search_address = document.querySelector(".search_address");
const member_address1 = document.getElementById("member_address1");
const member_address2 = document.getElementById("member_address2");
const dupBtn = document.querySelector(".dupBtn");
const member_id = document.getElementById("member_id");
search_address.addEventListener('click',e=>{
  e.preventDefault();
	goPopup();
})
function goPopup(){

	window.name="jusopopup";
	window.open("/cafeinme/cafe/roadapi","pop","width=570,height=620, scrollbars=yes, resizable=yes"); 
}


function jusoCallBack(roadAddrPart1,addrDetail){
  member_address1.value = roadAddrPart1;
  member_address2.value = addrDetail;
}
member_id.addEventListener("change",()=>{
	if(dubtnswitch){
		dubtnswitch = false;
	}
})
dupBtn.addEventListener("click",e=>{
	e.preventDefault();
	get(member_id.value);
})

function handler(json){
	if(json){
		window.alert("아이디가 중복입니다.");
	}
	else{
		window.alert("사용 가능한 아이디입니다.");
		dubtnswitch = true;
	}
}

function get(e){
	let x = JSON.stringify(e);
	let url = `http://localhost:9080/cafeinme/member/duplicate?member_id=${e}`;
	if(e.trim().length!=0){
		fetch(url, {
      method: 'GET',
      headers: { 'Accept': 'application/json' }
    })
      .then(response => response.json())
      .then(json => {console.log(json),handler(json);})
      .catch(error => console.error(error));
	}
}


member_tel.addEventListener("keyup", function(){
	let tel = autoHypenTel(member_tel.value);
	member_tel.value = tel;
});

function autoHypenTel(str) {
	str = str.replace(/[^0-9]/g, '');
	let tmp = '';
  
	if (str.length < 4) {
		return str;
	} else if (str.length < 8) {
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3);
		return tmp;
	} else {
		tmp += str.substr(0, 3);
		tmp += '-';
		tmp += str.substr(3, 4);
		tmp += '-';
		tmp += str.substr(7, 4);
		return tmp;
	}
};