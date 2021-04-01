package com.kh.cafeinme.shopTest.dao;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kh.cafeinme.mycafe.vo.CafeVO;
import com.kh.cafeinme.shop.dao.ShopDAO;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*.xml"})
@Slf4j
public class ShopDAOImplTest {
	
	
	@Autowired
	JdbcTemplate jt;
	
	@Autowired
	ShopDAO shopDAO;
	
	@Test
	@DisplayName("온라인 샵 전체 불러오기")
	void registReview() {
		int cafe_onlinesale = 1;

		List<CafeVO> list = shopDAO.getShopList(cafe_onlinesale);
		
		for (CafeVO record : list) {
			log.info(record.toString());
		}
	}
}
