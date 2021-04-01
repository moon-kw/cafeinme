/**
 * 
 */
'use strict'
const search_address = document.querySelector(".search_address");
const member_address1 = document.getElementById("member_address1");
const member_address2 = document.getElementById("member_address2");
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