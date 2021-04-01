package com.kh.cafeinme.orders.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.cafeinme.member.vo.MemberVO;
import com.kh.cafeinme.orders.svc.OrderSVC;
import com.kh.cafeinme.orders.vo.CartVO;
import com.kh.cafeinme.orders.vo.OrderDetailVO;
import com.kh.cafeinme.orders.vo.OrderVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/shop")
@RequiredArgsConstructor
public class RestOrderController {

	private final OrderSVC orderSVC;
	
	//장바구니 수량 변경	
	@PostMapping("/modifyCart")
	public Map<String,Object> modifyCartItem(HttpSession session, @RequestBody Map<String,String> reqData) {
		Map<String,Object> map = new HashMap<>();
		
		MemberVO memberVO = (MemberVO)session.getAttribute("members");
		String MEMBER_ID = memberVO.getMember_id();
		int MENU_NO = Integer.parseInt(reqData.get("MENU_NO"));
		String coc = reqData.get("coc");
		switch (coc) {
		case "01": //장바구니 상품 갯수 변경
		case "02":	
			int result = orderSVC.modifyCartItem(coc, MEMBER_ID, MENU_NO);
			if(result == 1) {
				int itemNum = orderSVC.countOfCartItem(MEMBER_ID, MENU_NO);
				map.put("rtcd", "00");
				map.put("result", itemNum);
			}
			break;
		case "03"://장바구니 상품 삭제
			int result2 = orderSVC.deleteCartItem(MEMBER_ID, MENU_NO);
			if(result2 == 1) {
				List<CartVO> list = orderSVC.cartItemList(MEMBER_ID);
				if(list.size() > 0) {					
					map.put("rtcd", "00");
					map.put("result", list);				
				}else {
					map.put("rtcd", "01");
				}
				
			}else {
				map.put("rtcd", "02");
				map.put("msg_error", "error");
			}
		default:
			break;
		}
		return map;
	}
	
	//고객 주문내역 페이지
	@GetMapping("/getOrderList/{startDt}/{endDt}/{reqPage}")
	public Map<String, Object> getCustOrderListpage(
			@PathVariable("startDt") String startDt,
			@PathVariable("endDt") String endDt,
			@PathVariable("reqPage") int reqPage,
			HttpSession session
			){
		Map<String, Object> map = new HashMap<>();
		MemberVO memberVO = (MemberVO)session.getAttribute("members");
		String member_id = memberVO.getMember_id();
		map = orderSVC.getCustOrderList(member_id, startDt, endDt, reqPage);
		log.info("getOrderList의 맵:"+map);
		return map;
	}
	
	//구매내역페이지 주문상세 데이터
	@GetMapping("/orderDetailList/{order_no}")
	public Map<String, Object> getCustOrderDetailList(@PathVariable("order_no") int order_no){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("items", orderSVC.getOrderDetail(order_no));
		return map;
	}
	
	//주문관리 페이지
	@GetMapping("/orderManagementList")
	public Map<String, Object> getOrderManagementList(HttpSession session){
		MemberVO memberVO = (MemberVO)session.getAttribute("members");
		Map<String, Object> map = orderSVC.getOrderManagementList(memberVO.getMember_id());
//		log.info("맵:"+map);
		return map;
	}
	
	//주문관리 페이지 주문상태변화
	@PostMapping("/orderManagement/changeOrderStatus")
	public Map<String, Object> changeOrderStatus(@RequestBody Map<String,String> reqData) {
		int ORDER_NO = Integer.parseInt(reqData.get("ORDER_NO"));
		String nextStatus = reqData.get("nextStatus");
		Map<String, Object> map = orderSVC.changeOrderStatus(ORDER_NO, nextStatus);

		return map;
	}
	
	//판매목록 페이지
	@GetMapping("/salesManagement/{cafe_no}/{startDt}/{endDt}/{optionValue}/{reqPage}")
	public Map<String, Object> getSalesManagementList(
			HttpSession session,
			@PathVariable("cafe_no") int cafe_no,
			@PathVariable("startDt") String startDt, 
			@PathVariable("endDt") String endDt,
			@PathVariable("reqPage") int reqPage,
			@PathVariable("optionValue") String optionValue) {
		
		MemberVO memberVO = (MemberVO)session.getAttribute("members");
		
		Map<String, Object> map = orderSVC.getSalesManagementList(memberVO.getMember_id(), startDt, endDt, optionValue, reqPage);

		return map;
	}
}








