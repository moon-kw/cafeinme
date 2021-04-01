/**
 * 
 */
'use strict'
function openTab(evt, tabName) {
	var i, tabcontent, tablinks;
	tabcontent = document.getElementsByClassName("tabcontent");
	for (i = 0; i < tabcontent.length; i++) {
	tabcontent[i].style.display = "none";
	}
	tablinks = document.getElementsByClassName("tablinks");
	for (i = 0; i < tablinks.length; i++) {
	tablinks[i].className = tablinks[i].className.replace(" active", "");
	}
	document.getElementById(tabName).style.display = "block";
	evt.currentTarget.className += " active";
	}
	
	document.getElementById("defaultOpen").click();
const $imgbox = document.querySelector(".wrapper");
const $curimg = document.querySelectorAll(".imported_files>.curimg");
const $fake = document.querySelector(".fake");
const $real = document.querySelector(".real");
const $hidden = document.querySelector(".hiddenfile");
const $addimg = document.querySelector(".addimg");
const $cafe_address1 = document.querySelector("#address1");
const $cafe_address2 = document.querySelector("#address2");
const $roadbts =document.querySelector("#roadbts");
const $modifycafeinfo = document.querySelector("#modifycafeinfo");
const $cafe_name = document.getElementById("cafe_name");
const $cafe_tel = document.getElementById("tel");
const $open_time = document.getElementById("otime");
const $close_time = document.getElementById("ctime");
const $cafe_content = document.getElementById("ccontent");
let cnt = 0;
let filecontainer = new DataTransfer();
$curimg.forEach(ele=>ele.addEventListener("click",(e)=>{
  let input = document.createElement("input");
  input.setAttribute("type","hidden");
  input.setAttribute("name",`delfile[${cnt}].cfile_no`);
  input.value = parseInt(e.target.dataset.cfile_no);
  cnt++;
  $hidden.append(input);
  e.target.parentElement.parentElement.removeChild(e.target.parentElement);
}))
$addimg.addEventListener("click",()=>{
  $fake.click();
})
$fake.addEventListener("change",(e)=>{
  if(!chkfile(e)){
    window.alert("이미지파일은 9개를 초과할수없습니다.")
		$fake.value = "";
		return;
	}
	if(chkfiletype(e.target)){
    window.alert("이미지파일만 등록가능합니다.")
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
function delimg(event){
	for(let i=0; i<filecontainer.files.length; i++){

		if(event.target.dataset.name==filecontainer.files[i].name){
			filecontainer.items.remove(i);
		}
	}
	$imgbox.removeChild(event.target.parentElement);
	$real.files = filecontainer.files;
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

}
$roadbts.addEventListener("click",(e)=>{
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
$modifycafeinfo.addEventListener("click",(e)=>{
e.preventDefault();
let telregexp = new RegExp(/^\d{3}-\d{3,4}-\d{4}$/);
let x = true;
let $select = document.querySelectorAll("[name='tag_no']:checked");
if($cafe_name.value.trim().length==0){
  window.alert("이름을 입력해주십시오");
x=false;
return;
}
if($cafe_address1.value.trim().length==0){
  window.alert("주소를 입력해주십시오");
x=false;
  return;
}
if($cafe_address2.value.trim().length==0){
  window.alert("세부주소를 입력해주십시오");
x=false;
  return;
}
if($cafe_tel.value.trim().length==0){
  window.alert("전화번호를 입력해주십시오");
  x=false;
  return;
}
if(!telregexp.test($cafe_tel.value)){
  window.alert("전화번호 양식은 000-0000-0000 혹은 000-000-0000으로 입력해주십시오");
  x=false;
  return;
}
if($open_time.value.trim().length==0){
  window.alert("opentime을 입력해주십시오");
x=false;
  return;
}
if($close_time.value.trim().length==0){
  window.alert("closetime를 입력해주십시오");
x=false;
  return;
}
if($cafe_content.value.trim().length==0){
  window.alert("카페 상세설명을 입력해주십시오");
x=false;
  return;
}
if($select.length<3){
  window.alert("태그는 3개이상 선택해주십시오");
  x=false;
  return;
}
if(x){

document.getElementById("modifyform").submit();
}
})
