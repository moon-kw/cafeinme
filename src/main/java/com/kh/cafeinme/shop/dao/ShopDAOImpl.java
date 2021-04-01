package com.kh.cafeinme.shop.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.cafeinme.mycafe.vo.CafeVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ShopDAOImpl implements ShopDAO{
	
	private final JdbcTemplate jt;

	@Override
	public List<CafeVO> getShopList(int cafe_onlinesale) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.cafe_no,t1.cafe_name,t1.cafe_address1,t2.cfile_path ");
		sql.append("from cafe t1 left outer join cafefile t2 on t2.cafe_no = t1.cafe_no ");
		sql.append("where (cfile_path in ");
		sql.append("(select cfile_path from (select ROW_number() OVER(partition By cafe_no Order by cafe_no,cfile_path) Ran ,cafe_no,cfile_path from cafefile) ");
		sql.append("where RAn =1) or cfile_path is NULL) and cafe_onlinesale = ?");
		
		List<CafeVO> list = new ArrayList<CafeVO>();
		list = jt.query(sql.toString(), new BeanPropertyRowMapper<CafeVO>(CafeVO.class),cafe_onlinesale);
		
		return list;
	}

	


}
