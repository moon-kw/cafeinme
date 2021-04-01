'use strict';

const $loginBtn = document.querySelector('#loginBtn');

const loginHandler = evt => {
	evt.preventDefault();
	
	console.log('로그인클릭');
	
	//아이디 입력유무
	const $member_id = document.getElementById('member_id');
	if($member_id.value.trim().length == 0){
		document.getElementById('errmsg_member_id').textContent = '아이디를 입력해주세요.';
		$member_id.focus();
		return;
	}
	
	//비밀번호 체크
	const $member_pw = document.getElementById('member_pw');
	if($member_pw.value.trim().length == 0){
		document.getElementById('errmsg_member_id').textContent = '';
		document.getElementById('errmsg_member_pw').textContent = '비밀번호를 입력해주세요.';
		$member_pw.focus();
		return;
	}
	
	document.getElementById('loginForm').submit();
}

$loginBtn.addEventListener('click', loginHandler);