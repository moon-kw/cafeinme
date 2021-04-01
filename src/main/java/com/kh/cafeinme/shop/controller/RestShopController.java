package com.kh.cafeinme.shop.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.cafeinme.member.vo.MemberVO;
import com.kh.cafeinme.orders.controller.RestOrderController;
import com.kh.cafeinme.orders.svc.OrderSVC;
import com.kh.cafeinme.orders.vo.CartVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/shop")
@RequiredArgsConstructor
public class RestShopController {

	private final OrderSVC orderSVC;
	
	//장바구니 메뉴 등록
	@PostMapping("/insertCart")
	public Map<String, String>insertCart(
			@RequestBody Map<String,String> reqData,
			HttpSession session){
		Map<String, String> map = new HashMap<String, String>();
		CartVO cartVO = new CartVO();
		
		MemberVO memberVO = (MemberVO)session.getAttribute("members");
//		MemberVO memberVO = new MemberVO();
//		memberVO.setMember_id("test@test.com");
		cartVO.setMEMBER_ID(memberVO.getMember_id());
		cartVO.setCAFE_NO(Integer.parseInt(reqData.get("cafe_no")));
		cartVO.setMENU_NAME(reqData.get("menu_name"));
		cartVO.setMENU_NO(Integer.parseInt(reqData.get("menu_no")));
		cartVO.setMENU_COUNT(Integer.parseInt(reqData.get("menu_count")));
		cartVO.setMENU_PRICE(Long.parseLong(reqData.get("menu_price")));
		int result = orderSVC.insertCartItem(cartVO);
		log.info("result:"+result);
		switch (result) {
		case 1:
			map.put("rtcd", "00");
			map.put("result", "success");
			break;
		case -1:
			map.put("rtcd", "01");
			map.put("result", "fail:differentCafeMenu");
			break;
		default:
			break;
		}
		return map;
	}
	
	//장바구니 기존 메뉴 삭제후 새 메뉴 등록
	@PostMapping("/insertCart2")
	public Map<String, String>insertCart2(
			@RequestBody Map<String,String> reqData,
			HttpSession session){
		Map<String, String> map = new HashMap<String, String>();
		CartVO cartVO = new CartVO();
		MemberVO memberVO = (MemberVO)session.getAttribute("members");
//		MemberVO memberVO = new MemberVO();
//		memberVO.setMember_id("test@test.com");
		cartVO.setMEMBER_ID(memberVO.getMember_id());
		cartVO.setCAFE_NO(Integer.parseInt(reqData.get("cafe_no")));
		cartVO.setMENU_NAME(reqData.get("menu_name"));
		cartVO.setMENU_NO(Integer.parseInt(reqData.get("menu_no")));
		cartVO.setMENU_COUNT(Integer.parseInt(reqData.get("menu_count")));
		cartVO.setMENU_PRICE(Long.parseLong(reqData.get("menu_price")));
		int result = orderSVC.removeAndinsertCartItem(cartVO);
		switch (result) {
		case 1:
			map.put("rtcd", "00");
			map.put("result", "success");
			break;

		default:
			break;
		}
		return map;
	}
}
