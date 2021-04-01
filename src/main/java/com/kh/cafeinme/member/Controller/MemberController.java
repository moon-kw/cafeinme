package com.kh.cafeinme.member.Controller;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.cafeinme.member.svc.MemberSVC;
import com.kh.cafeinme.member.vo.MemberVO;
import com.kh.cafeinme.mycafe.svc.MycafeSVC;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController {
	
	
	private final MemberSVC memberSVC;
	private final MycafeSVC mycafeSVC;
	
	//회원가입양식
	@GetMapping("/memberJoinForm")
	public String memberJoinForm(Model model) {
		
		model.addAttribute("memberVO", new MemberVO());
		return "/member/memberJoinForm";
	}
	
	//회원가입처리
	@PostMapping("/memberJoin")
	public String memberJoin(
			@Valid MemberVO memberVO,
			BindingResult errResult,
			Model model
			) {
		
		String viewName = null;
		int result = 0;
		
		log.info("memberVO:" + memberVO);
		
		//유효성 체크 오류시
		if(errResult.hasErrors()) {
			
			Map<String,String> map = memberSVC.valideHandling(errResult);
			model.addAttribute("svr_msg", map);
			log.info("map:==>"+map);
			log.info("map:==>"+model);
			return "/member/memberJoinForm";	
		}
		
		result = memberSVC.joinMember(memberVO);
		
		if(result == 1) {
			log.info("회원가입성공:" + memberVO);
			viewName = "redirect:/";
		}else {
			log.info("회원가입실패:" + memberVO);
			viewName = "/member/memberJoinForm";
		}
		
		return viewName;
	}
	
	//회원 아이디 찾기
	@GetMapping("/findIDForm")
	public String findID() {
		return "/member/findIDForm";
	}
	
	//회원 비밀번호 찾기
	@GetMapping("/findPWForm")
	public String findPW() {
		return "/member/findPWForm";
	}
	
	//마이페이지
	@GetMapping("/myPage")
	public String myPage() {
		
		return "/member/myPage";
	}
	

	
	//회원수정양식
	@GetMapping("/memberModifyForm")
	public String memberModfiyForm(
			Model model,
			HttpSession session) {
		
		MemberVO memberVO = (MemberVO)session.getAttribute("members");
		
		if(memberVO == null) {
			return "redirect:/loginForm";
		}
		
		MemberVO member = memberSVC.listOneMember(memberVO.getMember_id());
		member.setMember_pw("");
		
		model.addAttribute("memberVO", member);
		return "/member/memberModifyForm";
	}
	
	//비밀번호 수정 양식
	@GetMapping("/changePWForm")
	public String changePWForm() {
		
		return "/member/changePWForm";
	}
	
	//비밀번호 변경 처리
	@PostMapping("/changePW")
	public String change(
			@RequestParam("member_id") String member_id,
			@RequestParam("currentPw") String currentPw, 
			@RequestParam("nextPw") String nextPw,
			Model model
			) {
		
		log.info("비밀번호변경:"+ member_id); 
		log.info("비밀번호변경:"+ currentPw); 
		log.info("비밀번호변경:"+ nextPw);
		
		String viewName = null;
		int result = memberSVC.changePW(member_id, currentPw, nextPw);
		//성공
		if(result == 1) {
			model.addAttribute("svr_msg", "비밀번호가 변경되었습니다.");
			viewName = "redirect:/member/myPage";
		}else {
		//실패
			model.addAttribute("svr_msg", "입력하신 비밀번호를 확인해주세요.");
			viewName = "/member/changePWForm";
		}
		return viewName;
	}
	
	
	//회원수정처리
	@PostMapping("/memberModify")
	public String memberModify(
			@Valid MemberVO memberVO,
			BindingResult errResult,
			Model model,
			RedirectAttributes redirectAttrs,
			HttpSession session
			) {
		String viewName = null;
		
		log.info("회원수정 :" + memberVO.toString());
		
		//유효성체크 오류발생시
		if(errResult.hasErrors()) {
			return "/member/memberModifyForm";
		}
		
		int result = memberSVC.modifyMember(memberVO);
		
		//수정성공
		if(result == 1) {
			//세션의 회원정보 갱신
			//현재 세선정보 제거
			session.removeAttribute("members");
			
			//새로운 정보로 세션생성
			session.setAttribute("members", memberSVC.listOneMember(memberVO.getMember_id()));
			
			redirectAttrs.addFlashAttribute("svr_msg", "회원정보가 수정되었습니다.");
			viewName = "redirect:/member/memberModifyForm";
		} else {
		//수정실패	
			model.addAttribute("svr_msg", "회원정보 수정 실패");
			viewName = "/member/memberModifyForm";
			}
		return viewName;
		}
	
	
	//회원탈퇴양식	
	@GetMapping("/delMemberForm")
	public String delMemberForm() {
		
		
		return "/member/delMemberForm";
	}

	//회원탈퇴 처리
	@PostMapping ("/delMember") 
	public String delMember(
			@RequestParam("member_id") String member_id,
			@RequestParam("member_pw") String member_pw,
			Model model) {
		
		String viewName = null;
		
		log.info(member_id, member_pw);
		
		int result = memberSVC.delMember(member_id, member_pw);
		
		//1)탈퇴성공
		if(result == 1) {
			//로그아웃 후 초기화면
			viewName = "redirect:../logout";
		} else {
		//2)탈퇴실패	
			model.addAttribute("svr_msg", "비밀번호가 일치하지 않습니다.");
			viewName = "/member/delMemberForm";
		}
		return viewName;
	}
	

	
	
	

}
