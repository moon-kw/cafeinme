'use strict';

const $form = document.querySelector('.delForm');
const $delBtn = document.querySelector('.delBtn');

$form.addEventListener('keyup', ()=>{
  const $inputs = document.querySelectorAll('input');
  const $filterdInputs = Array.from($inputs).filter(ele=>ele.value.trim().length != 0);
  
  
  const beforeCount = $inputs.length;
  const afterCount = $filterdInputs.length;
  console.log(beforeCount, afterCount);

  if(beforeCount == afterCount) {
    $delBtn.removeAttribute('disabled');
  } else {
    $delBtn.setAttribute('disabled','');
  }
  
})