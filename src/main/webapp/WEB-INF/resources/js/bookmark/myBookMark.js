'use strict';

const url = "http://localhost:9080/cafeinme/cafeinfo/bookmark";
function del(event){
  if(event.target.tagName=="I"){
    let jsonObj = event.target.parentElement.dataset.cafe_no;
    let parent = event.target.parentElement.parentElement.parentElement.parentElement.parentElement;
    fetch(url, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
      },
      body: jsonObj
    }).catch(error => console.error(error));
    parent.removeChild(event.target.parentElement.parentElement.parentElement.parentElement);
  }
  
  
}
function copy(event){
  if(event.target.tagName=="I"){
    const $urlbox = document.createElement("input");
    document.body.appendChild($urlbox);
    $urlbox.value = `http://localhost:9080${event.target.parentElement.dataset.cafe_href}`;
    $urlbox.select();
    document.execCommand("copy");
    document.body.removeChild($urlbox);
    alert("선택하신 카페의 상세페이지가 복사되었습니다.")
  }
  
}

const $delbts = document.querySelectorAll(".delBtn");
const $copybts = document.querySelectorAll(".copyBtn");

$delbts.forEach(ele=>ele.addEventListener("click",del));
$copybts.forEach(ele=>ele.addEventListener("click",copy));