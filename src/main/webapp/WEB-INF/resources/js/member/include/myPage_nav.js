'use strict';

const myPageToggle = document.getElementById("myPage_toggleBtn");
const navBody = document.querySelector('.nav_body');

myPageToggle.addEventListener("click", ()=>{
  navBody.classList.toggle('active');
});