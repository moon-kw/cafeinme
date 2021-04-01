/**
 * 
 */
'use strict'
const $pagination = document.querySelector(".pagination");
const $review_body = document.querySelector(".review_body");

function openTab(evt, tabName) {
var i, tabcontent, tablinks;
tabcontent = document.getElementsByClassName("tabcontent");
for (i = 0; i < tabcontent.length; i++) {
tabcontent[i].style.display = "none";
}
tablinks = document.getElementsByClassName("tablinks");
for (i = 0; i < tablinks.length; i++) {
tablinks[i].className = tablinks[i].className.replace(" active", "");
}
document.getElementById(tabName).style.display = "block";
evt.currentTarget.className += " active";
}

document.getElementById("defaultOpen").click();
const debounce = (callback, delay) => {
	let timerId;
	return event => {
		if (timerId) clearTimeout(timerId);
		timerId = setTimeout(callback, delay, event);
	};
};
function del(url, jsonObj) {

	fetch(url, {
		method: 'DELETE',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(jsonObj)
	}).catch(error => console.error(error));

}
function post(url, jsonObj) {

	fetch(url, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(jsonObj)
	}).catch(error => console.error(error));

}
function get(url,handler) {
	fetch(url, {
		method: 'GET',
		headers: { 'Accept': 'application/json' }
	})
		.then(response => response.json())
		.then(json => {console.log(json);handler(json);})
		.catch(error => console.error(error));
		
}
function get2(url){
	fetch(url,{
		method:'GET',
		headers:{
			'Accept':'application/json'
		}
	}).then(response=>response.json()).then(json=>{
		handler(json);pagingchange(json);
	}).catch(error=>console.error(error));
}
if(document.getElementById("bookmarkbts")!=null){
	const $bkbts = document.getElementById("bookmarkbts");
	$bkbts.addEventListener("click", debounce(() => {
		$bkbts.classList.toggle("active");
		let url = "http://localhost:9080/cafeinme/cafeinfo/bookmark";
		if ($bkbts.classList.contains("active")) {
			post(url, jsonobj);
		}
		else {
			del(url, jsonobj);
		}
	}, 200));
}
function handler(json){
	$review_body.textContent = "";
	for(const rec of json.reviewList){
		const row = $review_body.insertRow($review_body.rows.length);
		row.setAttribute("class","content");
		const cell1 = row.insertCell(0);
		cell1.setAttribute("class","c_name");
		cell1.textContent = rec.review_NICKNAME;
		const cell2 = row.insertCell(1);
		cell2.setAttribute("class","c_ordered");
		cell2.textContent = rec.review_ITEMS;
		const cell3 = row.insertCell(2);
		cell3.setAttribute("class","c_content");
		cell3.textContent = rec.review_CONTENT;
		const cell4 = row.insertCell(3);
		cell4.setAttribute("class","c_rating");
		for(let i=0; i<rec.review_STAR; i++){
			cell4.append("★");
		}
		const cell5 = row.insertCell(4);
		cell5.setAttribute("class","c_date");
		cell5.textContent = rec.review_DATE;

		$review_body.append(row);
		if(rec.review_CMT==1){
			const row2 = $review_body.insertRow($review_body.rows.length);
			row2.setAttribute("class","content reply");
			const ccell1 = row2.insertCell(0);
			ccell1.setAttribute("class","no_content");
			let div = document.createElement("div");
			div.setAttribute("class","indent_border");
			ccell1.append(div);
			const ccell2 = row2.insertCell(1);
			ccell2.setAttribute("class","no_content");
			const ccell3 = row2.insertCell(2);
			ccell3.setAttribute("class","c_content");
			ccell3.textContent = rec.commentVO.comment_CONTENT;
			const ccell4 = row2.insertCell(3);
			ccell4.setAttribute("class","no_content");
			const ccell5 = row2.insertCell(4);
			ccell5.setAttribute("class","c_date");
			ccell5.textContent = rec.commentVO.COMMENT_DATE;
		}
	}
	


}
function pagingchange(json){
	startpage = json.pc.startPage;
	endpage = json.pc.endPage;
	$pagination.textContent = "";
	if(json.pc.prev){
		let a = document.createElement("a");
		a.textContent = "«";
		a.href = "#"
		$pagination.append(a);
	}
	for(let i=json.pc.startPage; i<=json.pc.endPage; i++){
		let a = document.createElement("a");
		if(i==json.pc.startPage){
			a.setAttribute("class","active");
		}
		a.textContent = i;
		a.href = "#"
		$pagination.append(a);
	}
	if(json.pc.next){
		let a = document.createElement("a");
		a.textContent = "»";
		a.href = "#"
		$pagination.append(a);
	}
}
$pagination.addEventListener("click",e=>{
	e.preventDefault();
	if(e.target.textContent=="»"){
		console.log(endpage);
		console.log(jsonobj);
		let url = `http://localhost:9080/cafeinme/review/cafereview/${jsonobj}/${endpage+1}`;
		get2(url);
		return;
	}
	if(e.target.textContent=="«"){
		let url = `http://localhost:9080/cafeinme/review/cafereview/${jsonobj}/${startpage-10}`;
		get2(url);
		return;
	}
	if(!isNaN(e.target.textContent)){
		let deltarget = document.querySelector(".pagination>.active");
		deltarget.classList.remove("active");
		e.target.classList.add("active");
		
		let url = `http://localhost:9080/cafeinme/review/cafereview/${jsonobj}/${e.target.textContent}`;
		get(url,handler);
	}
})