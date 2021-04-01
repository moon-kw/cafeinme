const $submitBtn = document.getElementById('submitBtn');
const $member_pw = document.getElementById('member_pw');
const $form = document.getElementById('delMemberForm')

$member_pw.addEventListener('keyup', ()=>{
  if($member_pw.value.trim().length > 0) {
    $submitBtn.removeAttribute('disabled');
  }
})


const submitBtnHandler = evt => {
  evt.preventDefault();
  console.log('탈퇴버튼클릭');
  //1) 모든필드에 값을 채웠는지 여부	
  if($member_pw.value.trim().length == 0) {
    $member_pw.placeholder='비밀번호를 입력하세요!';
    $member_pw.classList.add('errmsg');
    $member_pw.focus();
    return;
  }
  //2) 탈퇴 확인
  if(!confirm("정말로 탈퇴하겠습니까?")) return;
  
  //3) form전송
  $form.submit();
};


const cancelBtnHandler = evt => {
  evt.preventDefault();
  console.log('취소버튼클릭');
  //모든 필드 clear
  $member_pw.reset();
  //현재비밀번호로 focus이동
  $member_pw.focus();
};

$submitBtn.addEventListener("click",submitBtnHandler);
$cancelBtn.addEventListener("click",cancelBtnHandler);