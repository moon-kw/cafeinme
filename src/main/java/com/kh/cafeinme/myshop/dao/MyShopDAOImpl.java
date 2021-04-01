package com.kh.cafeinme.myshop.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyShopDAOImpl implements MyShopDAO {
private final JdbcTemplate jt;
	@Override
	public int updateonlinesale(String member_id, int onlinesale) {
		String SQL = "update cafe set cafe_onlinesale =? where cafe_no =?";
		int cafe_no = getmycafeno(member_id);
		if(onlinesale==0) {
			removeallmenu(cafe_no);
		}
		return jt.update(SQL,onlinesale,cafe_no);
	}
	private int getmycafeno(String member_id) {
		String SQL = "select cafe_no from cafe where cafe_ownerid =?";
		return jt.queryForObject(SQL, Integer.class,member_id);
	}
	private int removeallmenu(int cafe_no) {
		String SQL = "update menu set menu_onlinesale = 0 where cafe_no =?";
		return jt.update(SQL,cafe_no);
	}
}
