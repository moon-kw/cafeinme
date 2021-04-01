/**
 * 
 */
 var websocket = null;
const $body = document.querySelector("body");
const btn = document.getElementById("btn");
 function connectWs(){
  let sock = new SockJS( "http://localhost:9080/cafeinme/ws" );
  //sock = new SockJS('/replyEcho');
  websocket = sock;

  sock.onopen = function() {
        console.log('info: connection opened.');}
	sock.onmessage = onMsg;
}
btn.addEventListener("click",sendMsg);
	function onMsg(evt){
		let mbody = document.createElement("div");
		mbody.setAttribute("class","mbody");
		mbody.style.position = 'absoulte';
		mbody.style.bottom = 0;
		mbody.style.left = 0;
		mbody.style.width = '100px';
		mbody.style.height = '100px';
		mbody.style.backgroundColor = 'blue';
		mbody.style.color = 'white';
		setTimeout(animate(mbody),100);
		mbody.textContent = evt.data;
		mbody.addEventListener('click',(e)=>{
			$body.removeChild(mbody);
		})
		console.log(evt.data);
		$body.append(mbody);
	}
	function sendMsg(evt){
		let data = "1";
		websocket.send(data);
	}
	function animate(evt){
		evt.style.animationName = 'aram';
			evt.style.animationDuration = '300ms';
	}
	connectWs();