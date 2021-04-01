
package com.kh.cafeinme.common.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CodeDAOImpl implements CodeDAO {

	private final JdbcTemplate jt;
	
	/**
	 * 다음 시퀀스번호 가져오기
	 * @param sequence
	 * @return
	 */
	@Override
	public int getNextVal(String sequence) {
		String sql = String.format("select %s.nextval from dual", sequence);
		return jt.queryForObject(sql, Integer.class);
	}
	
	/**
	 * 다음 시퀀스번호 가져오기
	 * @param sequence
	 * @return
	 */
	@Override
	public long getNextVal2(String sequence) {
		String sql = String.format("select %s.nextval from dual", sequence);
		return jt.queryForObject(sql, Long.class);
	}

}
