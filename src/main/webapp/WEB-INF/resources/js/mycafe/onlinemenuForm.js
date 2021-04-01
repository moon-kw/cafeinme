/**
 * 
 */
 const debounce = (callback, delay) => {
	let timerId;
	return event => {
		if (timerId) clearTimeout(timerId);
		timerId = setTimeout(callback, delay, event);
	};
};
const $onlinebts = document.querySelectorAll(".onlinebts");
for(let i=0; i<$onlinebts.length; i++){
  $onlinebts[i].addEventListener("click",debounce(changestatus,500));
}
function changestatus(event){
  let status;
  let menu_no = event.target.dataset.menu_no;
  let url = "http://localhost:9080/cafeinme/menu/online"
  event.target.classList.toggle("active");
  if(event.target.classList.contains("active")){
    event.target.value = "판매중"
    status = 1;
  }
  else{
    event.target.value = "판매대기"
    status = 0;
  }
  let jsonobj = {"status":status,"menu_no":menu_no};
 
   patch(url,jsonobj);

}


function patch(url, jsonObj) {

  fetch(url, {
    method: 'PATCH',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(jsonObj)      
  }).catch(error => console.error(error));

}