package com.kh.cafeinme.member.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.cafeinme.member.vo.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class MemberDAOImpl implements MemberDAO{
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public MemberDAOImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	//회원등록
	@Override
	public int joinMember(MemberVO memberVO) {
		log.info("int joinMember(MemberVO memberVO)호출됨");
		int result = 0;
		
		StringBuilder sql = new StringBuilder();
		sql.append("insert into MEMBERS (MEMBER_ID, MEMBER_PW, MEMBER_NAME, MEMBER_BIRTH, MEMBER_NICKNAME, MEMBER_ADDRESS1, MEMBER_ADDRESS2, MEMBER_TEL) ");
		sql.append(" values (?, ?, ?, ?, ?, ?, ?, ?)");
		
		result = jdbcTemplate.update(sql.toString(),
									memberVO.getMember_id(), memberVO.getMember_pw(), memberVO.getMember_name(), 
									memberVO.getMember_birth(), memberVO.getMember_nickname(), memberVO.getMember_address1(),
									memberVO.getMember_address2(), memberVO.getMember_tel());
		
		return result;
	}

	//회원수정
	@Override
	public int modifyMember(MemberVO memberVO) {
		int result = 0;
		
		StringBuilder sql = new StringBuilder();
		sql.append("update MEMBERS "); 
		sql.append("set MEMBER_NAME =  ?, "); 
		sql.append("MEMBER_BIRTH = ?, "); 
		sql.append("MEMBER_NICKNAME = ?, "); 
		sql.append("MEMBER_ADDRESS1 = ?, "); 
		sql.append("MEMBER_ADDRESS2 = ?, ");  
		sql.append("MEMBER_TEL = ? "); 
		sql.append("where MEMBER_ID = ? "); 
		
		log.info("int modifyMember(MemberVO memberVO) " + memberVO);
		
		result = jdbcTemplate.update(
									sql.toString(),
									memberVO.getMember_name(),
									memberVO.getMember_birth(),
									memberVO.getMember_nickname(),
									memberVO.getMember_address1(),
									memberVO.getMember_address2(),
									memberVO.getMember_tel(),
									memberVO.getMember_id());
		
		return result;
	}

	//회원삭제
	@Override
	public int delMember(String member_id, String member_pw) {
		int result = 0;
		
		StringBuilder sql = new StringBuilder();
		sql.append("delete from MEMBERS ");
		sql.append("where MEMBER_ID = ? ");
		sql.append("and MEMBER_PW = ? ");
		
		result = jdbcTemplate.update(sql.toString(), member_id, member_pw);
		return result;
	}

	//회원 아이디 찾기
	@Override
	public String findID(String member_name, String member_tel) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("select MEMBER_ID from MEMBERS ");
		sql.append("where MEMBER_NAME = ? ");
		sql.append("and MEMBER_TEL = ? ");
		
		String result = jdbcTemplate.queryForObject(sql.toString(), String.class, member_name, member_tel);
		return result;
	}

	//회원 비밀번호 찾기
	@Override
	public String findPW(String member_id, String member_tel) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("select MEMBER_PW from MEMBERS ");
		sql.append("where MEMBER_ID = ? ");
		sql.append("and MEMBER_TEL = ?");
		
		String result = jdbcTemplate.queryForObject(sql.toString(), String.class, member_id, member_tel);
		return result;
	}

	//회원 존재여부
	@Override
	public boolean existMember(String member_id) {
		boolean result = false;
		String sql = "select count(*) cnt from members where MEMBER_ID = ? ";
		Integer cnt = jdbcTemplate.queryForObject(sql, Integer.class, member_id);
		result = (cnt == 1) ? true : false;
		return result;
	}
	
	//회원 인증
	@Override
	public boolean isMember(String member_id, String member_pw) {
		boolean result = false;
		String sql = "select count(*) cnt from members where MEMBER_ID = ? and MEMBER_PW = ?";
		Integer cnt = jdbcTemplate.queryForObject(sql, Integer.class, member_id, member_pw);
		result = (cnt == 1) ? true : false;
		return result;
	}
	
	
	//회원 정보 가져오기
	@Override
	public MemberVO listOneMember(String member_id) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from members ");
		sql.append("where MEMBER_ID = ? ");
		
		MemberVO memberVO = jdbcTemplate.queryForObject(
							sql.toString(), 
							new BeanPropertyRowMapper<MemberVO>(MemberVO.class),
							member_id);
		return memberVO;
	}

	
	//회원 비밀번호 변경
	@Override
	public int changePW(String member_id, String currentPw, String nextPw) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("update members ");
		sql.append(" set member_pw = ? ");
		sql.append(" where member_id = ? ");
		sql.append("  and member_pw = ? " );
		
		int result = jdbcTemplate.update(sql.toString(), nextPw, member_id, currentPw);
	
		return result;
	}
	
	//회원 아이디 중복체크 3.17 추가
	@Override
	public boolean isduplicate(String member_id) {
		boolean result = false;
		String SQL = "select count(*) from members where member_id = ?";
		int r = jdbcTemplate.queryForObject(SQL, Integer.class,member_id);
		if(r!=0) {
			result = true;
		}
		return result;
	}

	//회원 닉네임 중복체크 3.29 추가
	@Override
	public boolean existNickname(String member_nickname) {
		boolean result = false;
		String sql = "select count(*) cnt from members where MEMBER_Nickname = ? ";
		int cnt = jdbcTemplate.queryForObject(sql, Integer.class, member_nickname);
		if(cnt!=0) {
			result = true;
		}
		return result;
	}
	
}
