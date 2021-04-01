/**
 * 
 */
 const $body = document.querySelector("body");
var socket = null;
      connect();
      function connect() {
          const url = 'ws://localhost:9080/cafeinme/web-socket';
    
          const ws = new WebSocket(url);
          socket = ws;
          ws.addEventListener("open",() => {
              console.log('연결 성공!!');
          });
          ws.addEventListener("message",data => {
              let mbody = document.createElement("div");
              mbody.setAttribute("class","mbody");
              mbody.style.position = 'absolute';
              mbody.style.bottom = '10px';
              mbody.style.right = '10px';
              mbody.style.width = '250px';
              mbody.style.height = '150px';
              mbody.style.borderRadius = 'var(--big-tab) 0 var(--big-tab) var(--big-tab)';
              mbody.style.padding = 'var(--padding)';
              mbody.style.display = 'flex';
              mbody.style.alignItems = 'center';
              mbody.style.justifyContent = 'center';
              mbody.style.textAlign = 'center';
              mbody.style.boxShadow =  '7px 7px 4px #e0e0e0,-7px -7px 4px #ffffff';
              mbody.style.color = 'white';
              mbody.style.background = 'linear-gradient(45deg, var(--primary-blue-main), var(--primary-blue))';
              let span = document.createElement("span");
              span.setAttribute("class","message");
              span.textContent = '새로운 주문이 들어 왔습니다.';
              let delBtn = document.createElement("button");
              delBtn.style.position = 'absolute';
              delBtn.style.width = '15px';
              delBtn.style.height = '15px';
              delBtn.style.borderRadius = '20px';
              delBtn.style.top = '10px';
              delBtn.style.right = '15px';
              delBtn.style.lineHeight = 0;
              delBtn.style.fontWeight = 'bold';
              delBtn.style.color = 'var(--primary-blue-main)';
              delBtn.style.backgroundColor = 'white';
              delBtn.style.cursor = 'pointer';
              delBtn.textContent = 'X';
              mbody.append(delBtn);
              mbody.append(span);
               setTimeout(animate(mbody),100);
               setTimeout(()=>{
                if(document.querySelector(".mbody")!='undefined'){
                  
                  $body.removeChild(document.querySelector(".mbody"));
                }
              },3000);
             mbody.addEventListener('click',(e)=>{
                $body.removeChild(mbody);
               })
               $body.append(mbody);
            });
      }
function animate(evt){
	evt.style.animationName = 'aram';
			evt.style.animationDuration = '300ms';
}