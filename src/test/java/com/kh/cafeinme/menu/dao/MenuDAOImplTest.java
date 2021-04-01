package com.kh.cafeinme.menu.dao;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.kh.cafeinme.menu.vo.CategoryVO;
import com.kh.cafeinme.menu.vo.MenuVO;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*.xml"})
@Slf4j
@Transactional
public class MenuDAOImplTest {

	@Autowired
	JdbcTemplate jt;
	
	@Autowired
	MenuDAO menuDAO;
	
	@Test
	@Disabled
	void getCategoryOnlinesale() {
		int cafe_no = 1;
		List<CategoryVO> list = menuDAO.getCategoryOnlinesale(cafe_no);
		for(CategoryVO rec : list) {
			log.info("rec:"+rec.toString());
		}
	}
	
	@Test
//	@Disabled
	void getOnlinesaleMenu() {
		int cafe_no = 1;
		List<MenuVO> list = menuDAO.getOnlinesaleMenu(cafe_no);
		for(MenuVO rec : list) {
			log.info("rec:"+rec.toString());
		}
	}
	
	@Test
	@Disabled
	void getmyonemenu() {
		int menu_no = 21;
		MenuVO menuVO = menuDAO.getmyonemenu(menu_no);
		log.info(menuVO.toString());
	}
}
