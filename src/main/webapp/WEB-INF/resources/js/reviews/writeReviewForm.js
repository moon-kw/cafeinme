'use strict'

const $btn_confirm = document.getElementById('btn_confirm');

$btn_confirm.addEventListener("click", postReview);

function postReview(evt){

    evt.preventDefault();

    let starRate;
    if(document.getElementById('rate-1').checked == true){
        starRate = 1;
    }
    if(document.getElementById('rate-2').checked == true){
        starRate = 2;
    }
    if(document.getElementById('rate-3').checked == true){
        starRate = 3;
    }
    if(document.getElementById('rate-4').checked == true){
        starRate = 4;
    }
    if(document.getElementById('rate-5').checked == true){
        starRate = 5;
    }

    console.log(starRate);
}