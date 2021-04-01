package com.kh.cafeinme.reviewsTest.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.kh.cafeinme.common.dao.CodeDAO;
import com.kh.cafeinme.reviews.dao.ReviewDAO;
import com.kh.cafeinme.reviews.vo.CommentVO;
import com.kh.cafeinme.reviews.vo.ReviewVO;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*.xml"})
@Slf4j
@Transactional
public class reviewDAOImplTest {
	
	@Autowired
	JdbcTemplate jt;
	
	@Autowired
	CodeDAO codeDAO;
	
	@Autowired
	ReviewDAO reviewDAO;
	
	@Test
//	@Disabled
	void registReview() {
		ReviewVO reviewVO = new ReviewVO();
		reviewVO.setREVIEW_NICKNAME("테스터11");
		reviewVO.setREVIEW_ITEMS("아이템11");
		reviewVO.setREVIEW_CONTENT("리뷰11");
		reviewVO.setREVIEW_STAR(5);
		reviewVO.setMEMBER_ID("test@test.com");
		reviewVO.setCAFE_NO(10);
		reviewVO.setORDER_NO(100);
		
		long result = reviewDAO.registReview(reviewVO);
		log.info("리뷰등록:" + result);
	}
	
//	@Test
//	@Disabled
//	void getReview1() {
//		String MEMBER_ID = "test@test.com";
//		int startRec = 1;
//		int endRec = 10;
//		List<ReviewVO> list = reviewDAO.getReview(MEMBER_ID, startRec, endRec);
//		for (ReviewVO record : list) {
//			log.info(record.toString());
//		}
//	}
	
	@Test
	@Disabled
	void getReview2() {
		int CAFE_NO = 1;
		int startRec = 1;
		int endRec = 10;
		List<ReviewVO> list = reviewDAO.getReviewAll(CAFE_NO, startRec, endRec);
		
		for (ReviewVO record : list) {
			log.info(record.toString());
		}
	}
	
	@Test
	@DisplayName("게시글작성샘플300개생성")
	@Disabled
	void write2() {
		
		for(int i=0; i<30; i++) {
			ReviewVO reviewVO = new ReviewVO();
			reviewVO.setREVIEW_NICKNAME("테스터1");
			reviewVO.setREVIEW_ITEMS("아이템11");
			reviewVO.setREVIEW_CONTENT("리뷰11");
			reviewVO.setREVIEW_STAR(5);
			reviewVO.setMEMBER_ID("test@test.com");
			reviewVO.setCAFE_NO(1);
			reviewDAO.registReview(reviewVO);
		}
		//Assertions.assertNotEquals(0, boardDAO.write(boardVO));
	}
	
	@Test
	@Disabled
	void getneedwritereview() {
		List<ReviewVO> list = reviewDAO.getneedwritereview(1, 1, 3);
		for(ReviewVO rec : list) {
			log.info(rec.toString());
		}
	}
	
	@Test
	@Disabled
	void registComment() {
		Map<String , Object> map = new HashMap<String, Object>();
		long comment_no = reviewDAO.registComment("댓글1", 1, 1);
		log.info("반환값 : " + comment_no);
	}

}
