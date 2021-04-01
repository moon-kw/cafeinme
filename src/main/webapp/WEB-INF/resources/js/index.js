'use strict';

/*내비게이션*/ 
//navbar color change
const nav = document.getElementById('nav');
const logoLight = document.querySelector('.logo-light');
const logoDark = document.querySelector('.logo-dark');
const cartLight = document.querySelector('.cart-light');
const cartDark = document.querySelector('.cart-dark');
const navHeight = nav.getBoundingClientRect().height;

document.addEventListener('scroll', () => {
    if(window.scrollY > 600) {
        nav.classList.add('dark');
        logoLight.classList.add('dark')
        logoDark.classList.add('dark');
        cartLight.classList.add('dark');
        cartDark.classList.add('dark');
		toggleBtn.classList.add('dark');
    } else {
        nav.classList.remove('dark');
        logoLight.classList.remove('dark')
        logoDark.classList.remove('dark');
        cartLight.classList.remove('dark');
        cartDark.classList.remove('dark');
		toggleBtn.classList.remove('dark');
    }
});


/*드롭다운 메뉴*/ 
const toggleBtn = document.querySelector('.main_toggleBtn');
const navmenu = document.querySelector('.nav-item-menu');
const navmem = document.querySelector('.nav-item-mem')


toggleBtn.addEventListener('click', () => {
    navmenu.classList.toggle('active');
    navmem.classList.toggle('active');
});
/*지도 */
const $mapBox = document.querySelector('.mapBox'),
  options = {
    center: new kakao.maps.LatLng(35.5350070783584, 129.310830713355),
    level: 2,
    scrollwheel: false,
    draggable: false

  };
 
let map = new kakao.maps.Map($mapBox, options);
const $marker = new kakao.maps.Marker({
    map: map,
    position: new kakao.maps.LatLng(35.5350070783584, 129.310830713355)
});
const $input_search = document.querySelector(".input_search");
const $findBtn = document.querySelector(".findBtn");
$findBtn.addEventListener('click',e=>{
  e.preventDefault();
  if($input_search.value.trim().length!=0){
    window.location = `http://localhost:9080/cafeinme/search?keyword="${$input_search.value}"`;
  }
})
$input_search.addEventListener('keyup',()=>{
  if($input_search.value.trim().length!=0){
    if(window.event.keyCode == 13){
      window.location = `http://localhost:9080/cafeinme/search?keyword="${$input_search.value}"`;
    }
  }
})