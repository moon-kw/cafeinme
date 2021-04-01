'use strict';


/*드롭다운 메뉴*/ 
const toggleBtn = document.querySelector('.main_toggleBtn');
const navmenu = document.querySelector('.nav-item-menu');
const navmem = document.querySelector('.nav-item-mem')


toggleBtn.addEventListener('click', () => {
    navmenu.classList.toggle('active');
    navmem.classList.toggle('active');
});