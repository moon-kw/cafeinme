/**
 * 
 *//**
* 
*//**
* 
*/
const $address_search = document.getElementById("address_search");
const $address_searchBtn = document.getElementById("address_searchBtn");
const $tagbox = document.querySelector(".tagbox");
const $keyword_search = document.getElementById("keyword_search");
const $keyword_searchBtn = document.getElementById("keyword_searchBtn");
const $searchresult = document.getElementById("searchul");
const $curlocation = document.getElementById("curlocation");
const $modal = document.querySelector(".divmodal");
const $cafeinfo = document.getElementById("cafeinfo");

let container = document.getElementById('map'),
  options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 2
  };
let map = new kakao.maps.Map(container, options);
let resultlist = [];
let tagselcetlist = [];
let geocoder = new kakao.maps.services.Geocoder();
class resultitems {
  lx;
  ly;
  cafe_no;
  cafe_name;
  taglist;
  cafe_address1;
  cafe_address2;
  cafe_tel;
  open_time;
  close_time;
  infowindow;
  cfile_path;
  marker;
}
$address_searchBtn.addEventListener("click", () => {
  if ($address_search.value != "") {

    search($address_search.value, 'road');
  }
  else {
    window.alert("검색어를입력해주십시오");
  }

})
$address_search.addEventListener("keyup", () => {
  if (window.event.keyCode == 13) {
    if ($address_search.value != "") {

      search($address_search.value, 'road');
    }
    else {
      window.alert("검색어를입력해주십시오");
    }
  }
})
$tagbox.addEventListener("click", (e) => {
  if (e.target.parentElement.classList.contains('checks')) {
    tagselector(e);
  }
})
$keyword_search.addEventListener("keyup", () => {
  if (window.event.keyCode == 13) {
    if ($keyword_search.value != "") {

      search($keyword_search.value, 'cafename');
    }
    else {
      window.alert("검색어를입력해주십시오");
    }
  }
})
$keyword_searchBtn.addEventListener("click", () => {
  if ($keyword_search.value != "") {

    search($keyword_search.value, 'cafename');
  }
  else {
    window.alert("검색어를입력해주십시오");
  }
})
$searchresult.addEventListener("click", (e) => {
  if (e.target.classList.contains('result1')) {
    resultselector(e.target);
  }
  else {
    if (e.target.parentElement.classList.contains('result1')) {
      resultselector(e.target.parentElement);
    }
    else {
      if (e.target.parentElement.parentElement.classList.contains('result1')) {

        resultselector(e.target.parentElement.parentElement);
      }
    }
  }
})
$curlocation.addEventListener('click', () => {
  resetsearch();
  getLocation();
})
function search(keyword, searchoption) {
  resetsearch();
  let url = `http://localhost:9080/cafeinme/search/${searchoption}/${keyword}`;
  fetch(url, {
    method: 'GET',
    headers: { 'Accept': 'application/json' }
  }).then(response => response.json()).then(json => { setresult(json); })
    .catch(error => console.error(error));
}
function setresult(json) {
  console.log(json);
  if (json.length != 0) {
    for (const rec of json) {
      let items = new resultitems();
      items.cafe_no = rec.cafe_no;
      items.cafe_name = rec.cafe_name;
      
      items.taglist = rec.tagnames.split(',');
      items.cafe_address1 = rec.cafe_address1;
      items.cafe_address2 = rec.cafe_address2;
      items.open_time = rec.open_time;
      items.cafe_tel = rec.cafe_tel;
      items.close_time = rec.close_time;
      if (rec.cfile_path != null) {

        items.cfile_path = rec.cfile_path;
      }
      else {
        items.cfile_path = "favicon.png"
      }
      let textinfo = `<div>${rec.cafe_name}</div>`;
      geocoder.addressSearch(rec.cafe_address1, function (result, status) {
        if (status === kakao.maps.services.Status.OK) {

          items.ly = result[0].y
          items.lx = result[0].x

          let marker = new kakao.maps.Marker({
            position: new kakao.maps.LatLng(result[0].y, result[0].x)
          });

          marker.setTitle(rec.cafe_name);

          items.marker = marker;

          let infowindow = new kakao.maps.InfoWindow({
            content: textinfo,
            removable: true
          });

          kakao.maps.event.addListener(items.marker, 'click', function () {
            infowindow.open(map, items.marker);
          });
          items.infowindow = infowindow;
          items.marker.setMap(map);

          resultlist.push(items);
          showresult();
        }
        else {
          window.alert("잠시후 실행해주십시오");
        }
      });
    }
  }
  else {
    window.alert("검색결과가없습니다.");
  }
}
function showresult() {
  $searchresult.textContent = "";
  for (let i = 0; i < resultlist.length; i++) {
    let li = document.createElement("li");
    let div1 = document.createElement("div");
    let div2 = document.createElement("div");
    let div3 = document.createElement("div");
    let p1 = document.createElement("p");
    let p2 = document.createElement("p");
    let p3 = document.createElement("p");
    let p4 = document.createElement("p");
    li.classList.add("result1");
    div1.classList.add("resultimg");
    div1.innerHTML = `<img src="/cafeinme/img/${resultlist[i].cfile_path}" alt="" onerror="this.src='/cafeinme/img/favicon.png'"></div>`;
    div2.classList.add("vr");
    div3.classList.add("result_content");
    p1.append(resultlist[i].cafe_name);
    p2.append(resultlist[i].cafe_address1);
    p3.append(resultlist[i].cafe_no);
    p4.append(resultlist[i].taglist);
    p3.classList.add("hiddeninfo");
    p4.classList.add("hiddeninfo");
    div3.append(p1);
    div3.append(p2);
    div3.append(p3);
    div3.append(p4);
    li.append(div1);
    li.append(div2);
    li.append(div3);

    $searchresult.append(li);
  }
  map.setCenter(new kakao.maps.LatLng(resultlist[0].ly, resultlist[0].lx));

}
function resetsearch() {
  tagselcetlist = [];
  let cnt = document.querySelectorAll(".checks>[type='checkbox']:checked");
  for (let i = 0; i < cnt.length; i++) {
    cnt[i].checked = false;
  }
  for (let i = 0; i < resultlist.length; i++) {
    if (resultlist[i].cafe_name != '현재위치') {

      resultlist[i].infowindow.close();
    }
    resultlist[i].marker.setMap(null);
  }
  $searchresult.textContent = "";
  resultlist = [];
}
function tagselector() {
  tagselcetlist = [];
  let cnt = document.querySelectorAll(".checks>[type='checkbox']:checked");
  let items = document.querySelectorAll("#searchul>.result1");
  for (let i = 0; i < cnt.length; i++) {
    tagselcetlist.push(cnt[i].parentElement.textContent.trim());
  }
  for (let i = 0; i < items.length; i++) {
    let tmp = items[i].childNodes[2].childNodes[3].textContent.split(",");
    let flag = true;
    if (tmp.length < tagselcetlist.length) {
      items[i].classList.add("hiding");
      for (let k = 0; k < resultlist.length; k++) {
        if (items[i].childNodes[2].childNodes[2].textContent == resultlist[k].cafe_no) {
          resultlist[k].marker.setMap(null);
          if (resultlist[k].cafe_name != '현재위치') {

            resultlist[k].infowindow.close();
          }
        }
      }
    }
    else {
      for (let j = 0; j < tagselcetlist.length; j++) {
        if (items[i].childNodes[2].childNodes[3].textContent.indexOf(tagselcetlist[j]) == -1) {
          flag = false;
          break;
        }
      }
      if (flag) {
        items[i].classList.remove("hiding");
        for (let k = 0; k < resultlist.length; k++) {
          if (items[i].childNodes[2].childNodes[2].textContent == resultlist[k].cafe_no) {
            resultlist[k].marker.setMap(map);
          }
        }
      }
      else {
        items[i].classList.add("hiding");
        for (let k = 0; k < resultlist.length; k++) {
          if (items[i].childNodes[2].childNodes[2].textContent == resultlist[k].cafe_no) {
            resultlist[k].marker.setMap(null);
            if (resultlist[k].cafe_name != '현재위치') {

              resultlist[k].infowindow.close();
            }
          }
        }
      }
    }
  }
}
function resultselector(event) {
  let tmp = event.childNodes[2].childNodes[2].textContent;
  let tmp2;
  for (let i = 0; i < resultlist.length; i++) {
    if (tmp == resultlist[i].cafe_no) {
      map.setCenter(new kakao.maps.LatLng(resultlist[i].ly, resultlist[i].lx))
      if (resultlist[i].cafe_name != '현재위치') {
        resultlist[i].infowindow.open(map, resultlist[i].marker);
        tmp2 = resultlist[i];
      }


    }
    else {
      if (resultlist[i].cafe_name != '현재위치') {
        resultlist[i].infowindow.close();

      }
    }
  }
  if (tmp2 != null) {
    onmodal(tmp2);
  }
}
const getLocation = () => {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(
      (position) => {
        let coord = new kakao.maps.LatLng(position.coords.latitude, position.coords.longitude);
        let callback = function (result, status) {
          if (status === kakao.maps.services.Status.OK) {
            if(result[0].road_address!=null){

              let x = new resultitems();
              x.lx = position.coords.longitude;
              x.ly = position.coords.latitude;
              x.cafe_no = 0;
              x.cafe_name = '현재위치';
              x.taglist = ['프렌차이즈','개인 카페','스터디 카페','룸 카페','애견 카페','2층 이상','주차장 여부','24시간 영업','감성 인테리어','브런치','알콜 판매','디저트 판매','예약 가능','커피 수업','주차장 여부','콘센트 수','반려 동물','원두 판매','장애인 좌석','높은 평점','키오스크','노 키즈 존'];
              x.cfile_path = "favicon.png";
              console.log(result[0]);
              x.cafe_address1 = result[0].road_address.address_name;
              let marker = new kakao.maps.Marker({
                position: new kakao.maps.LatLng(x.ly, x.lx)
              });
              x.marker = marker;
              x.marker.setMap(map);
              resultlist.push(x);
              let tmp = result[0].road_address.address_name.split('로')[0];
              let url = `http://localhost:9080/cafeinme/search/road/${tmp}`;
              fetch(url, {
                method: 'GET',
                headers: { 'Accept': 'application/json' }
              }).then(response => response.json()).then(json => { setresult(json); })
              .catch(error => console.error(error));
            }
            else{
              window.alert("위치정보를 찾을수 없습니다.");
            }
          }
        };
        geocoder.coord2Address(coord.getLng(), coord.getLat(), callback);
      },
      (error) => {
        console.error(error);
      },
      {
        enableHighAccuracy: false,
        maximumAge: 0,
        timeout: Infinity,
      }
    );
  } else {
    alert("GPS를 지원하지 않습니다");
  }
};
function onmodal(location) {
  let html = `<div class="mobody">
  <div class="motitle">
      <div class="cafe_name">${location.cafe_name}</div>
      <div onclick="offmodal()" class="modalbts">X</div>
  </div>
  <div class="mohr"></div>
  <div class="mocafe_img">
      <div class="mocafe_img">
          <img src="/cafeinme/img/${location.cfile_path}" alt="" onerror="this.src='/cafeinme/img/favicon.png'">
      </div>
  </div>
  <div class="mocafe_address">
      <p>${location.cafe_address1}</p>
  </div>
  <div class="mocafe_tel">
      <p class="moicon"> &#9742; </p>
      <p class="monum">${location.cafe_tel}</p>
  </div>
  <div class="moopen_close">
      Open
      <p id="moopen_time">${location.open_time}</p>
      <p id="mospace">|</p>
      Close 
      <p id="moclose_time">${location.close_time}</p>
  </div>
  <div class="mohr"></div>
  <div class="motagbox">`;
  for(const tags of location.taglist){
    html += `<div class="motag"> 
    <div class="mocheck"></div> 
    ${tags}
</div>`
  }
  html +=`</div>
  <button class="moviewmore"><a href="http://localhost:9080/cafeinme/search/cafeinfo/${location.cafe_no}"> 상세페이지로 이동 > </a></button>
</div>`
  $modal.innerHTML = html;
  $modal.classList.add("on");
}
function offmodal() {
  $modal.classList.remove("on");
} 
if(typeof mainkeyword !='undefined'){
  search(mainkeyword, 'road');
}
        
        