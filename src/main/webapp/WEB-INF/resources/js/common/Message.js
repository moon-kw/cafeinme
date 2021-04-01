'use strict'
const body = document.querySelector("body");
function returnboolean(event,handler,evt){
  body.removeChild(event.target.parentElement.parentElement.parentElement)
  let flag = event.target.value;
  handler(flag,evt);
}
function confirmcall(head,section,handler,evt){
  let div = document.createElement("div");
  let modal = document.createElement("div");
  let he = document.createElement("div");
  let sec = document.createElement("div");
  let foot = document.createElement("div");
  let yesbtn = document.createElement("button");
  let nobtn = document.createElement("button");
  modal.style.zIndex = "13";
  modal.style.backgroundColor = "rgba(0,0,0,0.1)";
  modal.style.position = "absolute"
  modal.style.top = "0"
  modal.style.width = "100%";
  modal.style.height = "100%";
  modal.style.display = "flex";
  modal.style.justifyContent = "center";
  modal.style.alignItems = "center";
  div.style.backgroundColor = "white";
  div.style.width = "500px";
  div.style.height = "300px";
  div.style.display = "flex";
  div.style.flexDirection = "column";
  div.style.outline ="4px solid lightblue";
  he.textContent = head;
  he.style.textAlign = "center";
  he.style.height = "10%";
  sec.textContent = section;
  sec.style.height = "80%";
  foot.style.height = "10%";
  yesbtn.textContent = "확인";
  yesbtn.style.border = "none";
  yesbtn.value = "true";
  yesbtn.style.cursor = "pointer";
  yesbtn.style.marginRight = "10px";
  yesbtn.addEventListener("click",e=>{
    returnboolean(e,handler,evt);
  });
  nobtn.textContent = "취소";
  nobtn.style.border = "none";
  nobtn.value = "false";
  nobtn.style.cursor = "pointer";
  nobtn.addEventListener("click",e=>{
    returnboolean(e,handler,evt);
  });
  foot.append(yesbtn);
  foot.append(nobtn);
  div.append(he);
  div.append(sec);
  div.append(foot);
  modal.append(div);
  body.append(modal);
}