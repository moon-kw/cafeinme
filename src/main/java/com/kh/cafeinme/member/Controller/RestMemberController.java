package com.kh.cafeinme.member.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.cafeinme.common.svc.PasswordGeneratorSVC;
import com.kh.cafeinme.member.svc.MemberSVC;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/member")
@RequiredArgsConstructor
public class RestMemberController {
	
	private final MemberSVC memberSVC;
	private final PasswordGeneratorSVC pwGenSVC;
	// 3.17추가 아이디 중복확인
	@GetMapping("/duplicate")
	public boolean isduplicate(@RequestParam("member_id") String member_id)
	{	
		log.info(member_id);
		return memberSVC.isduplicate(member_id);
	}
	@GetMapping("/member_id")
	public Map<String, Object> findId(
			@RequestParam("member_name") String member_name,
			@RequestParam("member_tel") String member_tel
			) {
		log.info("member_name" + member_name);
		log.info("member_tel" + member_tel);
		
		Map<String,Object> map = new HashMap<>();
		
		String member_id = null;
		try {
			member_id = memberSVC.findID(member_name, member_tel);
			map.put("rtcd", "00");
			map.put("result", member_id);
		} catch (Exception e) {
			map.put("rtcd", "01");
			map.put("errmsg", "일치하는 회원정보가 없습니다.");
		}
		return map;
	}
	
	
	@PostMapping("/member_id")
	public String findId2(
			@RequestParam("member_name") String member_name,
			@RequestParam("member_tel") String member_tel
			) {
		log.info("member_name: " + member_name);
		log.info("member_tel: " + member_tel);
		
		return "test@test.com";
	}
	
	
	
	
	//회원비밀번호찾기
	@PostMapping("/member_pw")
	public Map<String,Object> find_pw(
			@RequestBody Map<String,String> reqData, 
			HttpServletRequest request,
			HttpServletResponse response
			) {
		log.info("member_id :" + reqData.get("member_id"));
		log.info("member_tel :" + reqData.get("member_tel"));
		
		Map<String,Object> map = new HashMap<>();
		String member_pw = null; 
		
		try {
			member_pw =	memberSVC.findPW(
						reqData.get("member_id"), 
						reqData.get("member_tel"));
			
			//1)임시비밀번호 비밀번호 생성
			String tmpPw = pwGenSVC.generatorPassword(7, true,true,true,true);
			
			//2)임시비밀번호로 회원의 비밀번호 변경
			memberSVC.changePW(reqData.get("member_id"), member_pw, tmpPw);
			
			
			//로긴주소
			StringBuilder url = new StringBuilder();
			url.append("http://" + request.getServerName());
			url.append(":" + request.getServerPort());
			url.append(request.getContextPath());
			url.append("/loginForm");
			
			
			map.put("rtcd", "00");
			map.put("result", tmpPw);			
		}catch (Exception e) {
			e.printStackTrace();
			map.put("rtcd", "01");
			map.put("errmsg","회원정보가 없습니다." );			
		}
		
		return map;
	}
	
	
	@PostMapping("/idCheck")
	public Map<String, Object> confirmId(
			@RequestBody String member_id
			) throws Exception{
		
		System.out.println("중복 확인 요청 된 아이디: " + member_id);
		
		Map<String, Object> map = new HashMap<>();
		
		//서비스 측에 요청
		boolean result = memberSVC.existMember(member_id);
		
		if (result == true) {
			System.out.println("아이디 사용 가능");
			map.put("rtcd", "00");
			map.put("result", "사용 가능한 아이디입니다.");			
		}else {
			map.put("rtcd", "01");
			map.put("errmsg","아이디 중복. 사용 불가합니다." );			
		}
		
		
		return map;
	}
	
	@PostMapping("/nicknameCheck")
	public Map<String, Object> confirmNickname(
			@RequestBody String member_nickname
			) throws Exception{
		log.info(member_nickname);
		System.out.println("중복 확인 요청 된 닉네임: " + member_nickname);
		
		Map<String, Object> map = new HashMap<>();
		
		//서비스 측에 요청
		boolean result = memberSVC.existNickname(member_nickname);
		
		if (result == false) {
			System.out.println("닉네임 사용 가능");
			map.put("rtcd", "00");
			map.put("result", "");		
		}else {
			map.put("rtcd", "01");
			map.put("errmsg","이미 사용중인 닉네임입니다." );			
		}
		
		
		return map;
	}
	
	
}
