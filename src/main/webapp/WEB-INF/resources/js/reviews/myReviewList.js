/**
 * 
 */
 const $startDt = document.querySelector(".filter_startDt");
 const $endDt = document.querySelector(".filter_endDt");
 const $reviewtab = document.querySelector(".review_tb");
 const $searchbtn = document.querySelector(".filterBtn");
 const $review_tb = document.querySelector(".review_tb");
 const $pageBtn = document.querySelector(".pageBtn");
 let startday;
 let endday;
 let tmp;
 let searchswitch = false;
 $searchbtn.addEventListener("click",e=>{
	 if(validchk()){
		 
	 let url = `http://localhost:9080/cafeinme/review/myreview/${$startDt.value}/${$endDt.value}`;
	 e.preventDefault();
	 get(url)
	 }
	 else{
		 window.alert("날짜를 정확히 선택하여주십시오")
	 }
 
 })
 function get(url){
	 fetch(url, {
		 method: 'GET',
		 headers: { 'Accept': 'application/json' }
	 })
		 .then(response => response.json())
		 .then(json => {handler(json);})
		 .catch(error => console.error(error));
 }
 function handler(jsonObj){
	 startday = jsonObj.startday;
	 endday = jsonObj.endday;
	 tmp = jsonObj.pc;
	 $review_tb.textContent = "";
	 $pageBtn.textContent = "";
	 for(const rec of jsonObj.reviewList){
		 
	 const row = $review_tb.insertRow($review_tb.rows.length);
	 const cell1 = row.insertCell(0);
	 const cell2 = row.insertCell(1);
	 const cell3 = row.insertCell(2);
	 const cell4 = row.insertCell(3);
	 const cell5 = row.insertCell(4);
	 cell1.append(`${rec.cafe_NAME }`);
	 cell2.append(`${rec.review_DATE }`);
	 cell3.append(`${rec.review_ITEMS }`);
	 cell4.append(`${rec.review_CONTENT }`);
	 cell5.append(`${rec.review_CMT }`);
	 }
	 if(jsonObj.pc.prev){
		 let a = document.createElement("a");
		 a.href = "#";
		 a.textContent = "처음";
		 let a2 = document.createElement("a");
		 a2.href = "#"
		 a2.textContent = "이전";
		 $pageBtn.append(a);
		 $pageBtn.append(a2);
	 }
	 for(let i=jsonObj.pc.startPage; i<=jsonObj.pc.endPage; i++){
		 let a = document.createElement("a");
		 a.textContent = i;
		 a.href = "#"
		 $pageBtn.append(a);
	 }
	 if(jsonObj.pc.next){
		 let a = document.createElement("a");
		 a.href = "#";
		 a.textContent = "다음";
		 let a2 = document.createElement("a");
		 a2.href = "#"
		 a2.textContent = "끝";
		 $pageBtn.append(a);
		 $pageBtn.append(a2);
	 }
	 if(!searchswitch){
		 searchswitch = true;
		 $pageBtn.addEventListener("click",paging);
	 }
	 
	 
 }
 function paging(e){
		 e.preventDefault();
		 if(e.target.textContent=="처음"){
			 let aurl = `http://localhost:9080/cafeinme/review/myreview/${startday}/${endday}/1`;
			 get(aurl,handler);
		 }
		 if(e.target.textContent=="이전"){
			 let aurl = `http://localhost:9080/cafeinme/review/myreview/${startday}/${endday}/${tmp.startPage-1}`;
			 get(aurl,handler);
		 }
		 if(e.target.textContent=="다음"){
			 let aurl = `http://localhost:9080/cafeinme/review/myreview/${startday}/${endday}/${tmp.endPage+1}`;
			 get(aurl,handler);
		 }
		 if(e.target.textContent=="끝"){
			 let aurl = `http://localhost:9080/cafeinme/review/myreview/${startday}/${endday}/${tmp.finalEndPage}`;
			 get(aurl,handler);
		 }
		 if(!isNaN(e.target.textContent)){
			 let aurl = `http://localhost:9080/cafeinme/review/myreview/${startday}/${endday}/${e.target.textContent}`;
			 get(aurl,handler);
		 }
	 }
 function validchk(){
	 if($startDt.value.trim().length==0){
		 return false;
	 }
	 if($endDt.value.trim().length==0){
		 return false;
	 }
	 if($startDt.value>$endDt.value){
		 window.alert("시작날짜가 종료날짜보다 더 늦습니다.");
		 return false;
	 }
	 return true;
 }