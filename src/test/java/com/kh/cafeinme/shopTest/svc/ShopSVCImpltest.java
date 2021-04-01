package com.kh.cafeinme.shopTest.svc;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.kh.cafeinme.mycafe.vo.CafeVO;
import com.kh.cafeinme.shop.svc.ShopSVC;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*.xml"})
@Slf4j
@Transactional
public class ShopSVCImpltest {
	
	@Autowired  
	private ShopSVC shopSVC;
	
	@Test
	@DisplayName("온라인 샵 전체 불러오기")
	void registReview() {
		int cafe_onlinesale = 1;

		List<CafeVO> list = shopSVC.getShopList(cafe_onlinesale);
		
		for (CafeVO record : list) {
			log.info(record.toString());
		}
	}


}
