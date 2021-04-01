package com.kh.cafeinme.orders.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.cafeinme.member.vo.MemberVO;
import com.kh.cafeinme.mycafe.svc.MycafeSVC;
import com.kh.cafeinme.mycafe.vo.CafeVO;
import com.kh.cafeinme.orders.svc.OrderSVC;
import com.kh.cafeinme.orders.vo.OrderVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
@Slf4j
public class OrderOwnerController {

	private final MycafeSVC mycafeSVC;
	private final OrderSVC orderSVC;
	
	@GetMapping("/orderManagement")
	public String orderManagementForm(HttpSession session, Model model) {
		MemberVO memberVO = (MemberVO)session.getAttribute("members");
//		String startDt = null;
//		String endDt = null;
//		int reqPage = 1;
		if(mycafeSVC.ishavecafe(memberVO.getMember_id())) {
			CafeVO cafeVO = mycafeSVC.getmycafe(memberVO.getMember_id());
//			Map<String, Object> map = orderSVC.getCustOrderList(memberVO.getMember_id(), startDt, endDt, reqPage);		
			model.addAttribute("cafeVO", cafeVO);
//			model.addAttribute("list", orderSVC.getOrderManagementList(memberVO.getMember_id()));	
			return "/orders/orderManagementForm";
		}
		return "/mycafe/cafejoinForm";
	}
	
	@GetMapping("/salesManagement")
	public String salesManagementForm(HttpSession session, Model model) {
		MemberVO memberVO = (MemberVO)session.getAttribute("members");
		String startDt = null;
		String endDt = null;
		String optionValue = "status00";
		int reqPage = 1;
		if(mycafeSVC.ishavecafe(memberVO.getMember_id())) {
			CafeVO cafeVO = mycafeSVC.getmycafe(memberVO.getMember_id());
			Map<String, Object> map = orderSVC.getSalesManagementList(memberVO.getMember_id(), startDt, endDt, optionValue, reqPage);		
			model.addAttribute("cafeVO", cafeVO);
			model.addAttribute("map", map);
			return "/orders/myShopAccount";
		}
		return "/mycafe/cafejoinForm";
	}
	
}
