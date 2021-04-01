'use strict';
const $orderManagementList = document.getElementById('orderManagementList');

//페이지로딩시 주문관리목록 불러오기 이벤트
window.addEventListener("load",function(){
    getOrderList();
});

//주문관리목록 불러오기 함수
function getOrderList(){
    const url = `http://localhost:9080/cafeinme/shop/orderManagementList`;
    ajaxCall.get(url, OrderListHandler);
};

function OrderListHandler(jsonObj){
    const headers = jsonObj.orderList;
    const items = jsonObj.detailList;

    let waitTabItem = "";
    let acceptTabItem = "";
    let completeTabItem = "";

    for(let rec of headers){
        let arr = Array.from(items[rec.order_NO]);
        switch(rec.order_STATUS){
            case "접수대기":
                waitTabItem += `            <div class="waitTab tabContent"> `;
                waitTabItem += ` <input type="hidden" class="order_NO" name="order_NO" value="${rec.order_NO}"> `;
                waitTabItem += `                <div class="waitTab_orderInfo"> `;
                waitTabItem += `                    <div class="orderTime"> `;
                waitTabItem += `                        ${rec.order_DATE} `;
                waitTabItem += `                    </div> `;
                waitTabItem += `                    <div class="recieveType"> `;
                waitTabItem += `                        ${rec.order_RECEIVETYPE} `;
                waitTabItem += `                    </div> `;
                waitTabItem += `                </div> `;
                waitTabItem += `                <div class="waitTab_orderMenu"> `;
                waitTabItem += `                    <div class="menu_count"> `;
                waitTabItem += `                        ${rec.order_COUNT}개 `;
                waitTabItem += `                    </div> `;
                waitTabItem += `                    <div class="menu_name"> `;
                arr.forEach((item,idx)=>{
                    waitTabItem += `${item.menu_NAME} ${item.menu_COUNT}개`;
                    if(!(arr.length-1 == idx)){
                        waitTabItem += " / ";
                    }
                });
                waitTabItem += `                    </div> `;
                waitTabItem += `                    <div class="orderAddress"> `;
                if(rec.order_ADDRESS2 != null){
                    waitTabItem += `            ${rec.order_ADDRESS1} ${rec.order_ADDRESS2} `;
                }else{
                    waitTabItem += `            ${rec.order_ADDRESS1} `;
                }
                waitTabItem += `                    </div> `;
                waitTabItem += `                    <div class="paymentWay"> `;
                waitTabItem += `                        ${rec.order_PAYMENTTYPE} `;
                waitTabItem += `                    </div> `;
                waitTabItem += `                </div> `;
                waitTabItem += `                <div class="waitTab_buttons"> `;
                waitTabItem += `                    <button class="okBtn">접수</button> `;
                waitTabItem += `                    <button class="noBtn">거부</button> `;
                waitTabItem += `                </div> `;
                waitTabItem += `            </div> `;
                break;
            case "주문접수":
                acceptTabItem += `            <div class="acceptTab tabContent"> `;
                acceptTabItem += ` <input type="hidden" class="order_NO" name="order_NO" value="${rec.order_NO}"> `;
                acceptTabItem += `                <div class="acceptTab_orderInfo"> `;
                acceptTabItem += `                    <div class="orderTime"> `;
                acceptTabItem += `                        ${rec.order_DATE} `;
                acceptTabItem += `                    </div> `;
                acceptTabItem += `                    <div class="recieveType"> `;
                acceptTabItem += `                        ${rec.order_RECEIVETYPE} `;
                acceptTabItem += `                    </div> `;
                acceptTabItem += `                </div> `;
                acceptTabItem += `                <div class="acceptTab_orderMenu"> `;
                acceptTabItem += `                    <div class="menu_count"> `;
                acceptTabItem += `                        ${rec.order_COUNT}개 `;
                acceptTabItem += `                    </div> `;
                acceptTabItem += `                    <div class="menu_name"> `;
                arr.forEach((item,idx)=>{
                    acceptTabItem += `${item.menu_NAME} ${item.menu_COUNT}개`;
                    if(!(arr.length-1 == idx)){
                        acceptTabItem += " / ";
                    }
                });
                acceptTabItem += `                    </div> `;
                acceptTabItem += `                    <div class="orderAddress"> `;
                if(rec.order_ADDRESS2 != null){
                    acceptTabItem += `            ${rec.order_ADDRESS1} ${rec.order_ADDRESS2} `;
                }else{
                    acceptTabItem += `            ${rec.order_ADDRESS1} `;
                }
                acceptTabItem += `                    </div> `;
                acceptTabItem += `                    <div class="paymentWay"> `;
                acceptTabItem += `                        ${rec.order_PAYMENTTYPE} `;
                acceptTabItem += `                    </div> `;
                acceptTabItem += `                </div> `;
                acceptTabItem += `                <div class="acceptTab_buttons"> `;
                // acceptTabItem += `                    <button id="billsBtn">주문표<br>보기</button> `;
                acceptTabItem += `                    <button class="completeBtn">완료<br>처리</button> `;
                acceptTabItem += `                </div> `;
                acceptTabItem += `            </div> `;
                break;
            case "주문완료":
            case "주문거부":
                completeTabItem += `            <div class="completeTab tabContent"> `;
                completeTabItem += ` <input type="hidden" class="order_NO" name="order_NO" value="${rec.order_NO}"> `;
                completeTabItem += `                <div class="completeTab_orderInfo"> `;
                completeTabItem += `                    <div class="orderResult"> `;
                if(rec.order_STATUS == "주문완료"){
                    completeTabItem += `완료`;
                }else{
                    completeTabItem += `거부`;
                }
                completeTabItem += `                    </div> `;
                completeTabItem += `                    <div class="orderTime"> `;
                completeTabItem += `                        ${rec.order_DATE} `;
                completeTabItem += `                    </div> `;
                completeTabItem += `                </div> `;
                completeTabItem += `                <div class="completeTab_orderMenu"> `;
                completeTabItem += `                    <div class="menu_count"> `;
                completeTabItem += `                        ${rec.order_COUNT}개 `;
                completeTabItem += `                    </div> `;
                completeTabItem += `                    <div class="menu_name"> `;
                arr.forEach((item,idx)=>{
                    completeTabItem += `${item.menu_NAME} ${item.menu_COUNT}개`;
                    if(!(arr.length-1 == idx)){
                        completeTabItem += " / ";
                    }
                });
                completeTabItem += `                    </div> `;
                completeTabItem += `                    <div class="orderAddress"> `;
                if(rec.order_RECEIVETYPE === '배달'){
                    completeTabItem += `            ${rec.order_ADDRESS1} ${rec.order_ADDRESS2} `;
                }else{
                    completeTabItem += `            ${rec.order_ADDRESS1} `;
                }
                completeTabItem += `                    </div> `;
                completeTabItem += `                    <div class="paymentWay"> `;
                completeTabItem += `                        ${rec.order_PAYMENTTYPE} `;
                completeTabItem += `                    </div> `;
                completeTabItem += `                </div> `;
                // completeTabItem += `                <div class="completeTab_buttons"> `;
                // completeTabItem += `                    <button id="billsBtn">주문표<br>보기</button> `;
                // completeTabItem += `                </div> `;
                completeTabItem += `            </div> `;
                break;
        }
    }

    document.getElementById('pills-wait').innerHTML = waitTabItem;
    document.getElementById('pills-accept').innerHTML = acceptTabItem;
    document.getElementById('pills-complete').innerHTML = completeTabItem;
};

$orderManagementList.addEventListener("click", listClickHandler);
//상태변화 이벤트 함수
function listClickHandler(evt) {
    let nextStatusValue = "";
    if (evt.target.classList.contains('okBtn')) {
        console.log(evt.target);
        nextStatusValue = "status02";
        orderStatusChange(evt, nextStatusValue);
    };
    if (evt.target.classList.contains('noBtn')) {
        console.log(evt.target);
        nextStatusValue = "status04";
        orderStatusChange(evt, nextStatusValue);
    };
    if (evt.target.classList.contains('completeBtn')) {
        console.log(evt.target);
        nextStatusValue = "status03";
        orderStatusChange(evt, nextStatusValue);
    };
};

function orderStatusChange(evt, nextStatusValue){
    const url = "/cafeinme/shop/orderManagement/changeOrderStatus";
    const parent = evt.target.parentElement.parentElement;
    const orderNo = parent.querySelector('.order_NO').value;
    // const $order_STATUS = parent.querySelector('order_STATUS').value;

    const jsonObj = {
        ORDER_NO: orderNo,
        nextStatus: nextStatusValue
    }

    ajaxCall.post(url, jsonObj, OrderListHandler);
};

//온라인 주문시 웹소켓 메세지 감지 이벤트
socket.addEventListener("message", function(){
    getOrderList();
});