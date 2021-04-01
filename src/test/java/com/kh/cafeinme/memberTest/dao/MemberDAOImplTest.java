package com.kh.cafeinme.memberTest.dao;

import java.time.LocalDate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kh.cafeinme.member.dao.MemberDAO;
import com.kh.cafeinme.member.vo.MemberVO;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*.xml"})
@Slf4j
public class MemberDAOImplTest {
	
	@Autowired
	MemberDAO memberDAO;

	@Autowired
	JdbcTemplate jt;
	
	@Test
	@DisplayName("회원등록")
	@Disabled
	void joinMember() {
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMember_id("test1@test.com");
		memberVO.setMember_pw("1234");
		memberVO.setMember_name("남궁성");
		memberVO.setMember_birth(LocalDate.of(1993, 01, 01));
		memberVO.setMember_nickname("테스터4");
		memberVO.setMember_address1("울산광역시 중구 젊음의 2거리");
		memberVO.setMember_address2("29");
		memberVO.setMember_tel("010-1234-1234");
		
		int result = memberDAO.joinMember(memberVO);
		log.info("cnt:"+result);
	}
	
	
	@Test
	@DisplayName("회원수정")
	//@Disabled
	void modifyMember() {
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMember_name("최은만");
		memberVO.setMember_birth(LocalDate.of(1993, 02, 02));
		memberVO.setMember_nickname("테스터4");
		memberVO.setMember_address1("울산광역시 중구 젊음의 3거리");
		memberVO.setMember_address2("30");
		memberVO.setMember_tel("010-1111-1111");
		memberVO.setMember_id("test@test.com");
		memberVO.setMember_pw("1234");
		
		int result = memberDAO.modifyMember(memberVO);
		log.info("cnt:"+result);
	}
	
	@Test
	@DisplayName("회원탈퇴")
	@Disabled
	void delMember() {
		
		String MEMBER_ID = "test1@test.com";
		String MEMBER_PW = "1234";
		
		int result = memberDAO.delMember(MEMBER_ID, MEMBER_PW);
		log.info("cnt:"+result);
	}
	
	@Test
	@DisplayName("회원 아이디 찾기")
	@Disabled
	void findID() {
		
		String MEMBER_NAME = "홍길동";
		String MEMBER_TEL = "010-1111-1111";
		
		String MEMBER_ID = memberDAO.findID(MEMBER_NAME, MEMBER_TEL);
		log.info(MEMBER_ID);
	}
	
	@Test
	@DisplayName("회원 비밀번호 찾기")
	@Disabled
	void findPW() {
		
		String MEMBER_ID = "test@test.com";
		String MEMBER_TEL = "010-1234-5678";
		
		String MEMBER_PW = memberDAO.findPW(MEMBER_ID, MEMBER_TEL);
		log.info(MEMBER_PW);
	}
	
	@Test
	@DisplayName("회원 존재여부")
	@Disabled
	void existMember() {
		
		String MEMBER_ID = "test@test.com";
		
		log.info("회원 존재유무:" + memberDAO.existMember(MEMBER_ID));
	}
	
	
	@Test
	@DisplayName("회원 인증")
	@Disabled
	void isMember() {
		
		String MEMBER_ID = "test@test.com";
		String MEMBER_PW = "1234";
		
		log.info("회원인증:" + memberDAO.isMember(MEMBER_ID, MEMBER_PW));
	}
	
	
	@Test
	@DisplayName("회원 비밀번호 변경")
	@Disabled
	void changePW() {
		
		String nextPw = "1111";
		String member_id = "test@test.com";
		String currentPw = "1234";
		
		log.info("cnt: " + memberDAO.changePW(member_id, currentPw, nextPw));
	}
	
	@Test
	@DisplayName("닉네임 존재여부")
	//@Disabled
	void existNickname() {
		
		String MEMBER_NICKNAME = "김하윤";
		
		log.info("닉네임 존재유무:" + memberDAO.existNickname(MEMBER_NICKNAME));
	}
	
}
