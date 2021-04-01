package com.kh.cafeinme.mycafe.controller;


import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.cafeinme.member.svc.MemberSVC;
import com.kh.cafeinme.member.vo.MemberVO;
import com.kh.cafeinme.mycafe.svc.MycafeSVC;
import com.kh.cafeinme.mycafe.vo.CafeVO;
import com.kh.cafeinme.mycafe.vo.ModifyCafeVO;
import com.kh.cafeinme.reviews.svc.ReviewSVC;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;







@Controller
@RequestMapping("/cafe")
@RequiredArgsConstructor
@Slf4j
public class MyCafeController {

	private final MycafeSVC ms;
	private final MemberSVC mems;
	//3.18추가
	private final ReviewSVC rs;
	@ModelAttribute
	public void initData(Model model) {
		model.addAttribute("tags", ms.gettags());
	}
	
	
	@GetMapping("/roadapi")
	public String roadapi() {
		return "/mycafe/roadapi";
	}
	
	/**
	 * 카페 등록 양식
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/cafejoinForm")
	public String cafejoinForm(Model model,HttpSession session) {
			if(session.getAttribute("members")==null) {
				return "/member/loginForm";
				}
			MemberVO member = (MemberVO)session.getAttribute("members");
			if(ms.ishavecafe(member.getMember_id())) {
				model.addAttribute("cafevo",ms.getmycafe(member.getMember_id()));
				return "/mycafe/myCafeMain";
			}
			else {
				return "mycafe/cafejoinForm";
			}
			
	}
	
	/**
	 * 카페 등록 처리
	 * @param cafevo
	 * @param result
	 * @param session
	 * @param model
	 * @return
	 */
		@PostMapping("/cafejoin")
		public String cafejoin(@Valid CafeVO cafevo,
				BindingResult result,HttpSession session,Model model) {
			if(result.hasErrors()) {
				Map<String,String> map = ms.valideHandling(result);
				model.addAttribute("svr_msg", map);
				return "mycafe/cafejoinForm";	
			}
			if(ms.ishavebn(cafevo.getBn())) {
				model.addAttribute("svr_msg", "동일한 사업자 번호가 존재합니다.");
				return "mycafe/cafejoinForm";	
			}
			MemberVO member = (MemberVO)session.getAttribute("members");
			cafevo.setCafe_ownerid(member.getMember_id());
			
			ms.joinMycafe(cafevo);
			return "redirect:/cafe/myCafe";
		}
		
		/**
		 * 내 카페 정보
		 * @param model
		 * @param session
		 * @return
		 */
		@GetMapping("/myCafe")
		public String myCafe(Model model,HttpSession session) {
			if(session.getAttribute("members")==null) {
				return "/member/loginForm";
				}
			MemberVO member = (MemberVO)session.getAttribute("members");
			if(ms.ishavecafe(member.getMember_id())) {
				model.addAttribute("cafevo",ms.getmycafe(member.getMember_id()));
				return "/mycafe/myCafeMain";
			}
			else {
				return "mycafe/cafejoinForm";
			}
			
		}
		
		/**
		 * 내 카페 삭제 양식
		 * @param model
		 * @param session
		 * @return
		 */
		@GetMapping("/del")
		public String del(Model model,HttpSession session) {
			if(session.getAttribute("members")==null) {
				return "/member/loginForm";
			}
			MemberVO member = (MemberVO)session.getAttribute("members");
			if(ms.ishavecafe(member.getMember_id())) {
				return "mycafe/delmycafe";
			}
			else {
				return "mycafe/cafejoinForm";
			}
		}
		
		/**
		 * 내 카페 삭제 처리
		 * @param model
		 * @param session
		 * @param bn
		 * @param pw
		 * @return
		 */
		@PostMapping("/delete")
		public String delete(Model model,HttpSession session
				,@RequestParam("bn") String bn,
				@RequestParam("pw") String pw) {
			MemberVO member = (MemberVO)session.getAttribute("members");
			if(ms.isbn(bn,member.getMember_id())) {
				if(mems.isMember(member.getMember_id(), pw)) {
					ms.delmycafe(member.getMember_id());
					return "redirect:/";
				}
				else {
					model.addAttribute("rctd","1");
					return "redirect:/cafe/del";
				}
			}
			else {
				model.addAttribute("rctd","2");
				return "redirect:/cafe/del";
			}
		}
		
		/**
		 * 카페 정보 수정 양식
		 * @param model
		 * @param session
		 * @return
		 */
		@GetMapping("/modifyForm")
		public String modifyForm(Model model,HttpSession session) {
			if(session.getAttribute("members")==null) {
				return "/member/loginForm";
			}
			MemberVO member = (MemberVO)session.getAttribute("members");
			if(ms.ishavecafe(member.getMember_id())) {
				CafeVO cafevo = ms.getmycafe(member.getMember_id());
				model.addAttribute("cafevo",cafevo);
				Map<String,Object> map = rs.getReviewAll(cafevo.getCafe_no(), 1);
				model.addAttribute("review",map.get("reviewList"));
				model.addAttribute("pc",map.get("pc"));
				return "/mycafe/mycafeinfo";
			}
			else {
				return "redirect:/cafe/mycafe";
			}
			
		}
		
		/**
		 * 카페 정보 수정 양식
		 * @param model
		 * @param session
		 * @return
		 */
		@GetMapping("/infomodifyForm")
		public String infomodifyForm(Model model,HttpSession session) {
			MemberVO member = (MemberVO)session.getAttribute("members");
			CafeVO cafevo = ms.getmycafe(member.getMember_id());
			model.addAttribute("cafevo",cafevo);
			return "/mycafe/Mycafeinfomodify";
		}
		
		/**
		 * 수정 처리
		 * @param modifycafevo
		 * @return
		 */
		// 3.12 수정
		@PostMapping("/modify")
		public String modify(ModifyCafeVO cafevo,HttpSession session) {
			MemberVO member = (MemberVO)session.getAttribute("members");
			ms.modifymycafe(cafevo,member.getMember_id());
			return"redirect:/cafe/modifyForm";
		}
		
		/**
		 * 카페 삭제 처리
		 * @param session
		 * @return
		 */
		@PostMapping("/delmycafe")
		public String delmycafe(HttpSession session) {
			MemberVO member = (MemberVO)session.getAttribute("members");
			ms.delmycafe(member.getMember_id());
			return "redirect:/";
		}
}
