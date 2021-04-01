package com.kh.cafeinme.member.Controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.cafeinme.member.svc.MemberSVC;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {
	
	
	private final MemberSVC memberSVC;
	
	@Autowired
	public LoginController(MemberSVC memberSVC) {
		this.memberSVC = memberSVC;
	}
	
	//로그인양식
	@GetMapping("/loginForm")
	public String loginForm() {
		
		return "/member/loginForm";
	}

	//로그인처리
	@PostMapping("/login")
	public String login(
			@RequestParam("member_id") String member_id,
			@RequestParam("member_pw") String member_pw,
			Model model,
			HttpSession session
			) {
		String viewName = null;
		
		log.info("id:"+member_id);
		log.info("pw:"+member_pw);
		
		//1. 회원인 경우
		if(memberSVC.existMember(member_id)) {
			
			//1-1. 회원정보 일치
			if(memberSVC.isMember(member_id, member_pw)) {
				session.setAttribute("members", memberSVC.listOneMember(member_id));
				viewName = "index";
				//1-2. 회원정보 불일치
			} else {
				model.addAttribute("svr_msg", "가입하지 않은 아이디이거나, 입력하신 정보가 정확하지 않습니다.");
				viewName = "/member/loginForm";
			}
		//2. 비회원인 경우
		} else {
			model.addAttribute("svr_msg", "가입하지 않은 아이디이거나, 입력하신 정보가 정확하지 않습니다.");
			viewName = "/member/loginForm";
		}
		return viewName;
	}
	
	
	//로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		return "redirect:/";
	}
}
