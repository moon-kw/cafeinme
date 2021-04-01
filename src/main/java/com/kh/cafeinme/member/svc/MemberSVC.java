package com.kh.cafeinme.member.svc;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.kh.cafeinme.member.vo.MemberVO;

@Service
public interface MemberSVC {

	//회원등록
	int joinMember(MemberVO memberVO);
		
	//회원수정
	int modifyMember(MemberVO memberVO);
		
	//회원탈퇴
	int delMember(String member_id, String member_pw);
		
	//회원 아이디 찾기(by name, tel)
	String findID(String member_name, String member_tel);
	
	//회원 비밀번호 찾기(by id, tel)
	String findPW(String member_id, String member_tel);
	
	//회원 존재 유무
	boolean isMember(String member_id, String member_pw);
	
	//회원 인증
	boolean existMember(String member_id);
	
	//회원 개별 조회
	MemberVO listOneMember(String member_id);
	
	//회원 비밀번호 변경
	int changePW(String member_id, String currentPw, String nextPw);
		
	/**
	 * 유효성 체크
	 * @param errors
	 * @return
	 */
	Map<String, String> valideHandling(Errors errors);
	
	//아이디 중복확인 3.17추가
	boolean isduplicate(String member_id);
	
	//닉네임 중복 유무 3.29추가
	boolean existNickname(String member_nickname);
		
}
