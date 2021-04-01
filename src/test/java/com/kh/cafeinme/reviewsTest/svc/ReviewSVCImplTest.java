package com.kh.cafeinme.reviewsTest.svc;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.kh.cafeinme.reviews.svc.ReviewSVC;
import com.kh.cafeinme.reviews.vo.CommentVO;
import com.kh.cafeinme.reviews.vo.ReviewVO;
import com.kh.cafeinme.reviewsTest.dao.reviewDAOImplTest;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*.xml"})
@Slf4j
@Transactional
public class ReviewSVCImplTest {

	@Autowired
	JdbcTemplate jt;
	
	@Autowired
	ReviewSVC reviewSVC;
	
	@Test
	@Disabled
	void registReview() {
		ReviewVO reviewVO = new ReviewVO();
		reviewVO.setREVIEW_NICKNAME("테스터11");
		reviewVO.setREVIEW_ITEMS("아이템11");
		reviewVO.setREVIEW_CONTENT("리뷰11");
		reviewVO.setREVIEW_STAR(5);
		reviewVO.setMEMBER_ID("test11@test.com");
		reviewVO.setCAFE_NO(10);
		
		//int result = reviewSVC.registReview(reviewVO);
		//log.info("리뷰등록:" + result);
	}
	
	@Test
	@Disabled
	void modifyReview() {
		ReviewVO reviewVO = new ReviewVO();
		reviewVO.setREVIEW_CONTENT("리뷰입니다");
		reviewVO.setREVIEW_STAR(5);
		reviewVO.setREVIEW_NO(11);
		//int result = reviewSVC.modifyReview(reviewVO);
		//log.info("리뷰수정:" + result);
	}
}
