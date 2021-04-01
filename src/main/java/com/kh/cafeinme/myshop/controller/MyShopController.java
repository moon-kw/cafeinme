package com.kh.cafeinme.myshop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.cafeinme.member.svc.MemberSVC;
import com.kh.cafeinme.member.vo.MemberVO;
import com.kh.cafeinme.mycafe.svc.MycafeSVC;
import com.kh.cafeinme.myshop.svc.MyShopSVC;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/shop")
@RequiredArgsConstructor
public class MyShopController {
	private final MycafeSVC ms;
	private final MyShopSVC mss;
	private final MemberSVC mems;
	// 2021 03.12 수정
	@GetMapping("/myShopJoinForm")
	public String myshopjoinform(HttpSession session,Model model) {
		if(session.getAttribute("members")==null) {
			return "/member/loginForm";
		}
		MemberVO member = (MemberVO)session.getAttribute("members");
		if(ms.isonlinesale(member.getMember_id())) {
			return "redirect:/shop/del";
		}
		else{return "/mycafe/myshop/myShopJoinForm";}
	}
	@PostMapping("/myShopjoin")
	public String myshojoin(HttpSession session) {
		MemberVO member = (MemberVO)session.getAttribute("members");
		mss.updateonlinesale(member.getMember_id(),"1");
		return "redirect:/shop/myShopJoinForm";
	}
	//3.12 추가
	@GetMapping("/del")
	public String delmyshop() {
		
		return "/mycafe/myshop/delMyShopForm";
	}
	// 3.12 추가
	@PostMapping("/delete")
	public String deletemyshop(HttpSession session,
			@RequestParam("pw") String pw,
			@RequestParam("bn") String bn,
			Model model) {
		MemberVO member = (MemberVO)session.getAttribute("members");
		if(ms.isbn(bn, member.getMember_id())) {
			if(mems.isMember(member.getMember_id(), pw)) {
				mss.updateonlinesale(member.getMember_id(), "0");
				return "redirect:/shop/myShopJoinForm";
			}
			else {
				model.addAttribute("rctd","2");
				return "/mycafe/myshop/delMyShopForm";
			}
		}
		else {
			model.addAttribute("rctd","1");
			return "/mycafe/myshop/delMyShopForm";
		}
	}

}
