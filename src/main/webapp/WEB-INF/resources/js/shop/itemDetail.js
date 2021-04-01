'use strict';

const $item_count_input = document.querySelector('.item_count_input');
$item_count_input.addEventListener("click", itemPriceCalculation);
function itemPriceCalculation(){
    const menuPrice = document.getElementById('menu_price').value;
    const itemNum = document.querySelector('.item_count_input').value;
    const itemPrice = document.querySelector('.item_price');
    itemPrice.textContent = menuPrice * itemNum + " 원";
};

const $submitBtn = document.querySelector('.submitBtn');
$submitBtn.addEventListener("click", function(){
    const url = "/cafeinme/shop/insertCart";
    const menuName = document.getElementById('item_name').textContent.trim();
    const jsonObj = {
        cafe_no: document.getElementById('cafe_no').value,
        menu_no: document.getElementById('menu_no').value,
        menu_name: menuName,
        menu_count: document.querySelector('.item_count_input').value,
        menu_price: document.getElementById('menu_price').value
    }
    ajaxCall.post(url, jsonObj, handler);
});

function insertCartItem2(){
    const url = "/cafeinme/shop/insertCart2";
    const menuName = document.getElementById('item_name').textContent.trim();
    const jsonObj = {
        cafe_no: document.getElementById('cafe_no').value,
        menu_no: document.getElementById('menu_no').value,
        menu_name: menuName,
        menu_count: document.querySelector('.item_count_input').value,
        menu_price: document.getElementById('menu_price').value
    }
    ajaxCall.post(url, jsonObj, handler);
};

function handler(jsonObj){
    if(jsonObj.rtcd === '00'){
        alert("장바구니에 메뉴가 추가되었습니다.")
    }
    if(jsonObj.rtcd === '01'){
        let con = confirm("장바구니에는 같은 가게의 메뉴만 담을 수 있습니다. \n선택하신 메뉴를 장바구니에 담을 경우 이전에 담은 메뉴가 삭제됩니다.")
        if(con == true){
            insertCartItem2();
        }
    }
};