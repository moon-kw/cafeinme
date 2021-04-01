package com.kh.cafeinme.myshop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.cafeinme.member.vo.MemberVO;
import com.kh.cafeinme.myshop.svc.MyShopSVC;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class MyShopRestController {
	private final MyShopSVC ms;
	
	@PatchMapping("/updateonline")
	public void onlinesale(HttpSession session,@RequestBody String status) {
		MemberVO member = (MemberVO)session.getAttribute("members");
		ms.updateonlinesale(member.getMember_id(), status);
		
	}
}
