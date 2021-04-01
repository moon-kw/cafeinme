'use strict';

const $cafe_menu = document.querySelector(".cafe_menu");
$cafe_menu.addEventListener("click",ClickHandler,false);
function ClickHandler(evt){
    if(evt.target.classList.contains('name')){
    	// console.log("클릭됨");
        getItemDetail(evt);
    };
};

function getItemDetail(evt){
    const parent = evt.target.parentElement;
    const menuNO = parent.querySelector(".menu_no").value;
    let url = `http://localhost:9080/cafeinme/shop/itemDetail/${menuNO}`;
    location.href(url);
}

// function getItemDetail2(evt){
//     const menuNO = evt.target.parentElement.querySelector(".menu_no").value;
//     console.log(menuNO);
// }

