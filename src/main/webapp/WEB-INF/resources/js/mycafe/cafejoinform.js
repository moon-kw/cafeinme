'use strict';
const $profile = document.getElementById('profile');
const $searchbts = document.getElementById("searchbts");
const $submit = document.getElementById("submitbts");
const $cafe_name = document.getElementById("CAFE_NAME");
const $bn = document.getElementById("BN");
const $cafe_address1 = document.getElementById("CAFE_ADDRESS1");
const $cafe_address2 = document.getElementById("CAFE_ADDRESS2");
const $cafe_tel = document.getElementById("CAFE_TEL");
const $open_time = document.getElementById("OPEN_TIME");
const $close_time = document.getElementById("CLOSE_TIME");
const $cafe_content = document.getElementById("CAFE_CONTENT");
const $err_msg = document.getElementById("err_msg");
const $tag_no = document.getElementById("tag_no");
const $joinForm = document.getElementById("joinForm");
const $addimg = document.querySelector(".addimg");
const $hidden = document.querySelector(".hiddenfile");
const $imgbox = document.querySelector(".imported_files>.wrapper");
const $fake = document.querySelector("#fake");
const $real = document.querySelector("#real");
let filecontainer = new DataTransfer();
$addimg.parentElement.addEventListener("click",()=>{
	$fake.click();
})
$fake.addEventListener("change",(e)=>{
	if(!chkfile(e)){
		$err_msg.textContent = "파일은 9개를 초과할수없습니다.";
		$fake.value = "";
		return;
	}
	if(chkfiletype(e.target)){
		$err_msg.textContent = "이미지 파일만 등록할 수 있습니다."
		$fake.value = "";
		return;
	}
	if($real.files.length<9){
		let carrior = new DataTransfer();
    for(let i=0; i<$fake.files.length; i++){
      carrior.items.add($fake.files[i]);
    }
    $real.files = carrior.files;
    for(let i=0; i<carrior.files.length; i++){

      filecontainer.items.add(carrior.files[i])

    }
    
    $real.files = filecontainer.files;
    setimg();
	}
	else{
		$err_msg.textContent = "파일은 9개를 초과할수없습니다.";
	}
    $fake.value = "";
})
function chkfile(e){
	let result = true;
	if(e.target.files.length>9){
		result = false;
	}
	return result;
}
function setimg(){
	$imgbox.textContent = "";
	for(let i=0; i<$real.files.length; i++){
		let div = document.createElement("div");
		div.addEventListener("click",delimg);
		div.setAttribute("class","box");
		let img = document.createElement("img");
		img.dataset.name = $real.files[i].name;
		img.src = URL.createObjectURL($real.files[i]);
		div.append(img);
		$imgbox.prepend(div);
	}
}
function delimg(event){
	for(let i=0; i<filecontainer.files.length; i++){

		if(event.target.dataset.name==filecontainer.files[i].name){
			filecontainer.items.remove(i);
		}
	}
	$imgbox.removeChild(event.target.parentElement);
	$real.files = filecontainer.files;
}
$searchbts.addEventListener("click",(e)=>{
	 e.preventDefault();
	goPopup();
})
function goPopup(){
	// IE에서 opener관련 오류가 발생하는 경우, window에 이름을 명시해줍니다.
	window.name="jusopopup";
	
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	window.open("/cafeinme/cafe/roadapi","pop","width=570,height=620, scrollbars=yes, resizable=yes"); 
}


function jusoCallBack(roadAddrPart1,addrDetail){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		$cafe_address1.value = roadAddrPart1;
		$cafe_address2.value = addrDetail;
}



$submit.addEventListener("click",(e)=>{
  e.preventDefault();
	let bnregexp = new RegExp(/^\d{3}-\d{2}-\d{5}$/);
	let telregexp = new RegExp(/^\d{3}-\d{3,4}-\d{4}$/);
	let x = true;
	let $select = document.querySelectorAll("[name='tag_no']:checked");
  if($cafe_name.value.trim().length==0){
    $err_msg.textContent = "카페이름을 입력해주십시오";
	x=false;
  return;
  }
  if($bn.value.trim().length==0){
    $err_msg.textContent = "사업자번호를 입력해주십시오";
		x=false;
    return;
  }
	if(!bnregexp.test($bn.value)){
		$err_msg.textContent = "사업자번호는 000-00-00000 형식으로 입력해주십시오";
		x=false;
			return;
	}
  if($cafe_address1.value.trim().length==0){
    $err_msg.textContent = "주소를 입력해주십시오";
	x=false;
    return;
  }
  if($cafe_address2.value.trim().length==0){
    $err_msg.textContent = "세부주소를 입력해주십시오";
	x=false;
    return;
  }
	if($cafe_tel.value.trim().length==0){
		$err_msg.textContent = "전화번호를 입력해주십시오";
		x=false;
    return;
	}
	if(!telregexp.test($cafe_tel.value)){
		$err_msg.textContent = "전화번호는 000-0000-0000 혹은 000-000-0000 으로 입력해주십시오";
		x=false;
    return;
	}
  if($open_time.value.trim().length==0){
    $err_msg.textContent = "open time을 입력해주십시오";
	x=false;
    return;
  }
  if($close_time.value.trim().length==0){
    $err_msg.textContent = "close time을 입력해 주십시오";
	x=false;
    return;
  }
  if($cafe_content.value.trim().length==0){
    $err_msg.textContent = "카페소개를 입력해주십시오"
	x=false;
    return;
  }
	if($select.length<3){
		$err_msg.textContent = "태그는 3개이상 선택해주십시오"
		x=false;
		return;
	}
if(x){
	
  $joinForm.submit();
}
})

function chkfiletype(val){
	let x = false;
	
	let array = ["image/png","image/jpg","image/jpeg"]
	for(let i=0; i<val.files.length; i++){
		let cnt = 0;
		for(let j=0; j<array.length; j++){
			if(val.files[i].type==array[j]){
				cnt++;
			}
		
		}
		if(cnt==0){
			x = true;
			break;
		}
	}
	return x;

};

//사업자번호 오토하이픈
$bn.addEventListener("keyup", function(){
	let businessNumber = autoHypen("bn", $bn.value);
	$bn.value = businessNumber;
});
$cafe_tel.addEventListener("keyup", function(){
	let telNumber = autoHypen("tel", $cafe_tel.value);
	$cafe_tel.value = telNumber;
});


function autoHypen(type, str) {
	str = str.replace(/[^0-9]/g, '');
	let tmp = '';
  
	if(type === 'bn'){
		if (str.length < 4) {
			return str;
		} else if (str.length < 6) {
			tmp += str.substr(0, 3);
			tmp += '-';
			tmp += str.substr(3);
			return tmp;
		} else {
			tmp += str.substr(0, 3);
			tmp += '-';
			tmp += str.substr(3, 2);
			tmp += '-';
			tmp += str.substr(5, 5);
			return tmp;
		}
	}
	if(type === 'tel'){
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
	}
};