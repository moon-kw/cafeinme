'use strict';
const isEmpty = document.getElementById('isEmpty').value;
if(isEmpty == 0){
    window.addEventListener("load", function(){
        itemprice();
    });
    
    const $MEMBER_ID = document.getElementById("MEMBER_ID");
    const $itemBox = document.getElementById("itemBox");
    const $itemList = document.getElementById("itemList");
    const url = "/cafeinme/shop/modifyCart";
    const $orderBtn = document.getElementById('orderBtn');
    
    $itemBox.addEventListener("click", listClickHandler);
    
    //주문버튼 이벤트
    $orderBtn.addEventListener("click", function(evt){
        evt.preventDefault();
        location.href = "http://localhost:9080/cafeinme/shop/order";
    });
    
    //상품 갯수 가격 계산 함수
    function itemprice() {
        const $item = document.querySelectorAll(".item");
        Array.from($item).forEach(function (ele) {
            parent = ele.parentElement;
            let $MENU_COUNT = ele.querySelector(".MENU_COUNT").value;
            let $MENU_PRICE = ele.querySelector(".MENU_PRICE").value;
            let $input_price = ele.querySelector(".input_price");
            
            $input_price.value = Number($MENU_COUNT) * Number($MENU_PRICE);
        });
        totalprice();
    };
    
    //상품 갯수 가격 총 합계 계산 함수
    function totalprice() {
        const $ORDER_PRICE = document.getElementById("ORDER_PRICE");
        const $ORDER_COUNT = document.getElementById("ORDER_COUNT");
        const $input_price = document.querySelectorAll(".input_price");
        const $MENU_COUNT = document.querySelectorAll(".MENU_COUNT");
        let ORDER_PRICE = 0;
        let ORDER_COUNT = 0;
        Array.from($input_price).forEach(function (ele) {
            ORDER_PRICE += Number(ele.value);
        });
        Array.from($MENU_COUNT).forEach(function(ele){
            ORDER_COUNT += Number(ele.value);
        });
        $ORDER_PRICE.value = ORDER_PRICE;
        $ORDER_COUNT.value = ORDER_COUNT;
    };
    //버튼클릭 이벤트 함수
    function listClickHandler(evt) {
        if (evt.target.classList.contains('fa-minus')) {
            countMinus(evt);
        };
        if (evt.target.classList.contains('fa-plus')) {
            countPlus(evt);
        };
        if (evt.target.classList.contains('delBtn')) {
            deleteItem(evt);
        };
    };
    //상품 갯수 감소 버튼 이벤트함수
    function countMinus(evt) {
        const parent = evt.target.parentElement.parentElement;
        const $MENU_NO = parent.parentElement.querySelector(".MENU_NO");
        const $MENU_COUNT = parent.querySelector(".MENU_COUNT");
        if ($MENU_COUNT.value == 1) {
            return;
        }
        const jsonObj = {
            // MEMBER_ID: $MEMBER_ID.value,
            MENU_NO: $MENU_NO.value,
            coc: "01",
        }
        ajaxCall.post(url, jsonObj, handler($MENU_COUNT));
    
    };
    //상품 갯수 증가 버튼 이벤트함수
    function countPlus(evt) {
        // debugger;
        const parent = evt.target.parentElement.parentElement;
        const $MENU_NO = parent.parentElement.querySelector(".MENU_NO");
        const $MENU_COUNT = parent.querySelector(".MENU_COUNT");
        if ($MENU_COUNT.value == 99) {
            return;
        }
        const jsonObj = {
            // MEMBER_ID: $MEMBER_ID.value,
            MENU_NO: $MENU_NO.value,
            coc: "02",
        }
        ajaxCall.post(url, jsonObj, handler($MENU_COUNT));
    };
    function handler(MENU_COUNT){
        const $MENU_COUNT = MENU_COUNT;
        return function handler2(jsonObj) {
            if (jsonObj.rtcd === '00') {
                $MENU_COUNT.value = Number(jsonObj.result);
                itemprice();
            }
            if(jsonObj.rtcd === '01'){
    
            }
        }
    };
    function handler3(jsonObj) {
        if (jsonObj.rtcd === '00') {
            const list = jsonObj.result;
            let items = "";
            for(let rec of list){
                items += `<div class="item"> `;
                items += `  <input type="hidden" class="MENU_NO" value="${rec.menu_NO}"> `;
                items += `  <input type="text" name="menu_name" class="input_name" value="${rec.menu_NAME}"> `;
                items += `  <input type="text" name="menu_price" class="input_price" value=""> `;
                items += `  <input type="hidden" class="MENU_PRICE" value="${rec.menu_PRICE}"> `;
                items += `  <div class="item_count input_count"> `;
                items += `    <button type="button" class="addBtn"><i class="fas fa-plus"></i></button> `;
                items += `    <input type="number" class="MENU_COUNT" min="1" value="${rec.menu_COUNT}" readonly> `;
                items += `    <button type="button" class="subBtn"><i class="fas fa-minus"></i></button> `;
                items += `  </div> `;
                items += `  <button type="button" class="delBtn">X</button> `;
                items += `</div> `;
            }
            $itemList.innerHTML = items;
            itemprice();
        }
        if(jsonObj.rtcd === '01'){
            let items = "";
            items += `<input type="hidden" name="isEmpty" id="isEmpty" value="1"> `;
            items += `<div class="cartEmpty">장바구니가 비었습니다</div> `;
            document.querySelector('.container').innerHTML = items;
        }
    }
    
    //장바구니 아이템 삭제 함수
    function deleteItem(evt) {
        const parent = evt.target.parentElement;
        const $MENU_NO = parent.querySelector(".MENU_NO");
        const jsonObj = {
            MENU_NO: $MENU_NO.value,
            coc:"03"
        }
        ajaxCall.post(url, jsonObj, handler3);
    };
};

