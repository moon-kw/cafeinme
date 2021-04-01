'use strict';

const $addBtn = document.querySelector('#addBtn')
const $delBtn = document.querySelector('#delBtn');
const $submitBtn = document.querySelector('#submitBtn');

$addBtn.addEventListener('click',addHandler);
$delBtn.addEventListener('click',delHandler);
$submitBtn.addEventListener('click',submitHandler);

const $tbody = document.querySelector('.myTable');


/*메뉴 입력란 한개 추가*/ 
function addHandler(evt){
  evt.preventDefault();
  
  const row = $tbody.insertRow($tbody.rows.length);
  
  const cell1 = row.insertCell(0);
  const cell2 = row.insertCell(1);
  const cell3 = row.insertCell(2);
  const cell4 = row.insertCell(3);
  const cell5 = row.insertCell(4);
  const cell6 = row.insertCell(5);
  const cell7 = row.insertCell(6);

  const $checkBox = document.createElement('input');
  $checkBox.setAttribute('type', 'checkbox');
  $checkBox.setAttribute('class', 'td-checkBox');

  const $selectBox = document.createElement('select');
  $selectBox.setAttribute('class', 'form-select form-select-sm')
  $selectBox.setAttribute('aria-label', 'form-select-sm example');

  const $nameBox = document.createElement('input');
  $nameBox.setAttribute('type', 'text');
  $nameBox.setAttribute('name', `menulist[${mcnt}].menu_name`);
  $nameBox.setAttribute('id', 'menu_name');
  $nameBox.setAttribute('class', 'form-input mname');

  const $priceBox = document.createElement('input');
  $priceBox.setAttribute('type', 'text');
  $priceBox.setAttribute('name', `menulist[${mcnt}].menu_price`);
  $priceBox.setAttribute('id', 'menu_price');
  $priceBox.setAttribute('class', 'form-input mprice');

  const $contentBox = document.createElement('input');
  $contentBox.setAttribute('type','text');
  $contentBox.setAttribute('name',`menulist[${mcnt}].menu_content`);
  $contentBox.setAttribute('id', 'menu_content');
  $contentBox.setAttribute('class', 'form-input mcontent');
  const $fileBox = document.createElement('input');
  $fileBox.setAttribute('type', 'file');
  $fileBox.setAttribute('name', `menulist[${mcnt}].file`);
  $fileBox.setAttribute('class', 'form-input mfile');
  $fileBox.setAttribute('onchange', 'changeimg(event)');

  const $imgBox = document.createElement('div');
  const $img = document.createElement('img');
  $imgBox.setAttribute("class","form-pic");
  $img.src = "http://localhost:9080/cafeinme/img/favicon.png";
  $imgBox.append($img);
  cell1.appendChild($checkBox);
  cell2.innerHTML = `<select class='form-select form-select-sm' aria-label='.form-select-sm example' name='menulist[${mcnt}].menu_category'>
  <option selected value="1">커피</option>
  <option selected value="2">디저트</option>
  <option selected value="3">기타</option>
  </select>`;
  cell3.appendChild($nameBox);
  cell4.appendChild($priceBox);
  cell5.appendChild($contentBox);
  cell6.appendChild($fileBox);
  cell7.appendChild($imgBox);
  mcnt++;
}


/*메뉴 선택 삭제*/
function delHandler(evt){
  let flag = true;
  let confirm;
  evt.preventDefault();
  let x = document.querySelectorAll(".td-checkBox:checked")
  for(let i=0; i<x.length; i++){
    if(x[i].parentElement.childNodes.length!=1){
      if(flag){
        confirmcall("기존메뉴입니다.","정말로삭제하시겠습니까",handler,x)
        flag = false;
      }

    }
    else{
      x[i].parentElement.parentElement.parentElement.removeChild(x[i].parentElement.parentElement);
    }
    
    mcnt--;
  }
}
function handler(flag,evt){
  if(flag=='true'){
    for(let i=0; i<evt.length; i++){
      del(evt[i].parentElement.childNodes[1].value);
      evt[i].parentElement.parentElement.parentElement.removeChild(evt[i].parentElement.parentElement);
    }
  }
  else{

  }
}
function del(x){
  let url = "http://localhost:9080/cafeinme/menu/del"
  fetch(url, {
    method: 'DELETE',
    headers: {
      'Content-Type': 'application/json',       
    },
    body: JSON.stringify(x)        
  }).catch(error => console.error(error))
}

function submitHandler(evt){
  evt.preventDefault();
  if(validchk()){

    document.querySelector(".shopMenuJoinForm").submit();
  }
}


/*전체선택*/

const $checkAll = document.querySelector('.th-checkBox');

function selectAll() {
  const $checkBoxes = document.querySelectorAll('input[type="checkbox"]');
  for(let i=0; i<$checkBoxes.length; i++) {
    if($checkAll.checked == true) {
      $checkBoxes[i].checked = true;
    } else {
      $checkBoxes[i].checked = false;
    }
  }
}
function validchk(){
  let mname = document.querySelectorAll('.mname');
  let mprice = document.querySelectorAll('.mprice');
  let mcontent = document.querySelectorAll('.mcontent');
  let mfile = document.querySelectorAll('.mfile');
  let array = ["image/png","image/jpg","image/jpeg"]
  let mchk = true;

  for(let i=0; i<mname.length; i++){
    if(mname[i].value.trim().length==0){
      mchk = false;
      break;
    }
  }
  if(!mchk){
    window.alert("메뉴이름을 등록하여주십시오");
    return mchk;
  }
  for(let i=0; i<mprice.length; i++){
    if(isNaN(mprice[i].value)){
      mchk = false;
      break;
    }
    if(mprice[i].value.trim().length==0){
      mchk = false;
      break;
    }
  }
  if(!mchk){
    window.alert("가격은 숫자로만 등록하여주십시오");
    return mchk;
  }
  for(let i=0; i<mcontent.length; i++){
    if(mcontent[i].value.trim().length==0){
      mchk = false;
      break;
    }
  }
  if(!mchk){
    window.alert("메뉴상세설명을 적어주십시오(50자미만)");
    return mchk;
  }
  for(let i=0; i<mfile.length; i++){
    if(mfile[i].files.length!=0){
      let cnt = 0;
      for(let j=0; j<array.length; j++){
        if(mfile[i].files[0].type==array[j]){
          cnt++;
        }
      }
      if(cnt==0){
        mchk = false;
        break;
      }
    }
  }
  if(!mchk){
    window.alert("파일은 이미지파일만 등록가능합니다")
    return mchk;
  }
  return mchk;
}

$checkAll.addEventListener('click',selectAll);
function changeimg(event){
  let tmp;
  if(event.target.parentElement.nextSibling.childNodes.length<1){
    tmp = event.target.parentElement.nextSibling.nextSibling.childNodes[1].childNodes[1];
  }
  else{
    tmp = event.target.parentElement.nextSibling.childNodes[0].childNodes[0];
  }
  if(event.target.files.length>0){

    let url = URL.createObjectURL(event.target.files[0])
    tmp.src = url;
  }
  else{
    tmp.src = "http://localhost:9080/cafeinme/img/favicon.png";
  }
}