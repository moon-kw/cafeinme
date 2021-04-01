package com.kh.cafeinme.bookmark.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.cafeinme.bookmark.svc.BookmarkSVC;
import com.kh.cafeinme.member.vo.MemberVO;

@Controller
@RequestMapping("/member")
public class BookmarkController {
	@Autowired
	BookmarkSVC bs;
	@GetMapping("/mybookmark")
	public String mybookmark(Model model,HttpSession session) {
		if(session.getAttribute("members")==null) {
			return "/member/loginForm";
			}
		MemberVO member = (MemberVO)session.getAttribute("members");
	
		model.addAttribute("bookmarks",bs.mybookmark(member.getMember_id()));
		return "bookmark/mybookmark";
	}
}
