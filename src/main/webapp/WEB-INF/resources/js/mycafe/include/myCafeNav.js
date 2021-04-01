'use strict';

const $navToggle = document.getElementById('cafeNav_toggleBtn');
const $navContainer = document.querySelector('.navContainer');

$navToggle.addEventListener('click', function(){
  console.log("클릭");

  $navContainer.classList.toggle('active');
  $navToggle.classList.toggle('active');
});