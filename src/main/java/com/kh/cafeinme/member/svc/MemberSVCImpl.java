package com.kh.cafeinme.member.svc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.kh.cafeinme.member.dao.MemberDAO;
import com.kh.cafeinme.member.vo.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly = true)
@Slf4j
public class MemberSVCImpl implements MemberSVC{

	private final MemberDAO memberDAO;
	
	@Autowired
	public MemberSVCImpl(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	@Transactional(readOnly = false)
	//회원등록
	@Override
	public int joinMember(MemberVO memberVO) {
		int result = 0;
		result = memberDAO.joinMember(memberVO);
		return result;
	}
	
	@Transactional(readOnly = false)
	//회원수정
	@Override
	public int modifyMember(MemberVO memberVO) {
		int result = 0;
		result = memberDAO.modifyMember(memberVO);
		return result;
	}
	
	@Transactional(readOnly = false)
	//회원탈퇴
	@Override
	public int delMember(String member_id, String member_pw) {
		int result = 0;
		result = memberDAO.delMember(member_id, member_pw);
		return result;
	}
	
	//아이디찾기
	@Override
	public String findID(String member_name, String member_tel) {
		String result = null;
		result = memberDAO.findID(member_name, member_tel);
		return result;
	}
	
	//비밀번호찾기
	@Override
	public String findPW(String member_id, String member_tel) {
		String result = null;
		result = memberDAO.findPW(member_id, member_tel);
		return result;
	}

	//회원조회
	@Override
	public boolean isMember(String member_id, String member_pw) {
		boolean result = false;
		result = memberDAO.isMember(member_id, member_pw);
		return result;
	}

	//회원존재여부
	@Override
	public boolean existMember(String member_id) {
		boolean result = false;
		result = memberDAO.existMember(member_id);
		return result;
	}

	@Override
	public MemberVO listOneMember(String member_id) {
		MemberVO memberVO = memberDAO.listOneMember(member_id);
		
		return memberVO;
	}

	@Transactional(readOnly = false)
	//비밀번호 변경
	@Override
	public int changePW(String member_id, String currentPw, String nextPw) {
		int result = 0;
		result = memberDAO.changePW(member_id, currentPw, nextPw);
		return result;
	}
	
	/**
	 * 멤버 정보 유효성 체크(3.12 추가)
	 * @param errors
	 * @return
	 */
	public Map<String,String> valideHandling(Errors errors){
		
		Map<String,String> map = new HashMap<>();
		
		for(FieldError error : errors.getFieldErrors()) {
			String key = String.format("valid_%s", error.getField());
			String value = error.getDefaultMessage();
			map.put(key, value);
		}
		
		return map;
	}
	
	//회원 중복체크 3.17 추가    		@Override
		public boolean isduplicate(String member_id) {
			
			return memberDAO.isduplicate(member_id);
		}
		
		//회원 닉네임 중복체크 3.29 추가
		@Override
		public boolean existNickname(String member_nickname) {
			boolean result = false;
			result = memberDAO.existNickname(member_nickname);
			return result;
		}

}
