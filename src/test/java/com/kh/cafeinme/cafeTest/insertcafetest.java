package com.kh.cafeinme.cafeTest;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kh.cafeinme.mycafe.dao.MycafeDAO;
import com.kh.cafeinme.mycafe.vo.CafeVO;
import com.kh.cafeinme.myshop.svc.MyShopSVC;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class) //스프링 컨텍스트와 junit통합
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/*.xml"})
@Slf4j
public class insertcafetest {
@Autowired
MycafeDAO md;
@Autowired
MyShopSVC ms;

	@Test
	@Disabled
	void test2() {
		CafeVO cafevo = new CafeVO();
		cafevo.setCafe_ownerid("정동훈");
		cafevo.setCafe_name("Hello world");
		cafevo.setCafe_address1("울산광역시 울주군 장검길 60");
		cafevo.setCafe_address2("101동 1503호");
		cafevo.setCafe_tel("010-8224-1814");
		cafevo.setBn("123-123-123");
		cafevo.setClose_time("12:00");
		cafevo.setOpen_time("13:00");
		cafevo.setCafe_content("테스트용");
		  
		md.joinMycafe(cafevo);
	}
	@Test
	void test3() {
	md.delmycafe("hello@naver.com");
	}
}
