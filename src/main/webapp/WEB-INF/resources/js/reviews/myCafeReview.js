'use strict';

const $reviewBox = document.querySelector('.reviewBox');
const $commentBox = document.querySelector('.commentBox');
const $pagingBox = document.querySelector('.pagingBox');
document.querySelector('.container').addEventListener("click", containerClickHandler);
// const $commentIcon = document.querySelector('.fa-comments');
// $commentIcon.addEventListener('click',()=>{
//   $commentBox.classList.toggle('active');
// })

function containerClickHandler(evt){
  if(evt.target.classList.contains('fa-comments')){
    commentBoxActiveHandler(evt.target.parentElement);
  }
  if(evt.target.classList.contains('commentBtn')){
    commentHandler(evt.target.parentElement.parentElement);
  }
};

function commentBoxActiveHandler(ele){
  const $commentBox = ele.querySelector('.commentBox');
  $commentBox.classList.toggle('active');
};

function commentHandler(ele){
  const commentNo = ele.querySelector('.comment_no').value;
  const reviewNo = ele.querySelector('.review_no').value;
  const commentContent = ele.querySelector('.comment').textContent;

  // console.log(commentNo);
  // console.log(reviewNo);
  // console.log(commentContent);

  //댓글 등록
  if(commentNo === "nocmt"){
    const url = `/cafeinme/review/registComment`;
    const jsonObj = {
      review_no : reviewNo,
      comment_content : commentContent
    }
    ajaxCall.post(url, jsonObj, handler1);
  }

  function handler1(jsonObj){
    if(jsonObj.rtcd === '00'){
      alert(`${jsonObj.msg}`);
      ele.querySelector('.comment_no').value = jsonObj.commentVO.comment_NO;
      ele.querySelector('.commentBtn').textContent = "댓글수정";
    }
    if(jsonObj.rtcd === '01'){
      alert(`${jsonObj.msg}`);
    }
  }

  //댓글 수정
  if(commentNo !== "nocmt"){
    const url = `/cafeinme/review/modifyComment`;
    const jsonObj = {
      comment_no : commentNo,
      comment_content : commentContent
    }
    ajaxCall.post(url, jsonObj, handler2);
  }

  function handler2(jsonObj){
    if(jsonObj.rtcd === '00'){
      alert(`${jsonObj.msg}`);
    }
    if(jsonObj.rtcd === '01'){
      alert(`${jsonObj.msg}`);
    }
  }
};