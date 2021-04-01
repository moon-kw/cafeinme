package com.kh.cafeinme.menu.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.cafeinme.member.vo.MemberVO;
import com.kh.cafeinme.menu.svc.MenuSVC;
import com.kh.cafeinme.menu.vo.MenuVOList;
import com.kh.cafeinme.mycafe.svc.MycafeSVC;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/cafe")
@RequiredArgsConstructor
@Slf4j
public class MenuController {
	
	private final MenuSVC ms;
	private final MycafeSVC mc;
	
	@ModelAttribute
	public void menucategoryinit(Model model) {
		model.addAttribute("category",ms.getCategory());
	}
		/**
		 * 메뉴 등록 양식
		 * @param model
		 * @param session
		 * @return
		 */
	@GetMapping("/menuForm")
	public String menuform(Model model,HttpSession session) {
		if(session.getAttribute("members")==null) {
			return "/member/loginForm";
		}
		MemberVO member = (MemberVO)session.getAttribute("members");
		if(mc.ishavecafe(member.getMember_id())) {
			model.addAttribute("menuvo",ms.getmymenu(member.getMember_id()));
			return "/mycafe/menuForm";
		}
		else {
			return "redirect:/cafe/cafejoinForm";
		}
	
		}
	
	/**
	 * 메뉴 등록 처리
	 * @param list
	 * @param model
	 * @param session
	 * @param result
	 * @return
	 */
	@PostMapping("/menujoinForm")
	public String menujoinForm(@ModelAttribute(value="MenuVOList") @Valid MenuVOList list,Model model,HttpSession session,BindingResult result) {
		MemberVO member = (MemberVO)session.getAttribute("members");
		ms.insertorupdatemenu(list.getMenulist(), member.getMember_id());	
		model.addAttribute("menuvo",ms.getmymenu(member.getMember_id()));
		return "redirect:/cafe/menuForm";
	}
	
	/**
	 * 온라인 판매 메뉴 등록
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("onlinemenuForm")
	public String onlinemenuForm(Model model,HttpSession session) {
		if(session.getAttribute("members")==null) {
			return "/member/loginForm";
		}
		MemberVO member = (MemberVO)session.getAttribute("members");
		if(mc.ishavecafe(member.getMember_id())) {
			if(mc.isonlinesale(member.getMember_id())) {
				model.addAttribute("menuvo",ms.getmymenu(member.getMember_id()));
				return "/mycafe/onlinemenuForm";
			}
			else {
				return "redirect:/shop/myShopJoinForm";
			}
		}
		else {
			return "/mycafe/cafejoinForm";
		}
		
		}

}
