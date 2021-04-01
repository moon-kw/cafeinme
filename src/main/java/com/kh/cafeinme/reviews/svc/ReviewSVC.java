package com.kh.cafeinme.reviews.svc;


import java.util.Map;

import com.kh.cafeinme.member.vo.MemberVO;
import com.kh.cafeinme.reviews.vo.CommentVO;
import com.kh.cafeinme.reviews.vo.ReviewVO;

public interface ReviewSVC {
	//3.24 추가
	Map<String,Object> getneedwritereview(String member_id, int recodenum);
	//3.18 추가 리뷰시간별조회
	Map<String,Object> getmyreviewwhen(String start, String end, String member_id,int reqpage);
	
	/**
	 * 리뷰 등록
	 * @param memberVO
	 * @param reqData
	 * @return
	 */
	Map<String, Object> registReview(MemberVO memberVO, Map<String,String> reqData);
	
	/**
	 * 리뷰 조회(1개, 주문번호)
	 * @param order_no
	 * @return
	 */
	Map<String, Object> getOneReview(int order_no);
	
	/**
	 * 리뷰 수정
	 * @param reqData
	 * @return
	 */
	Map<String, Object> modifyReview(Map<String,String> reqData);
	
	/**
	 * 리뷰 삭제
	 * @param REVIEW_NO
	 * @return
	 */
	int deleteReview(long REVIEW_NO);
	
	/**
	 * 댓글 등록
	 * @param map
	 * @return
	 */
	Map<String, Object> registComment(Map<String, String> reqData);
	
	/**
	 * 댓글 수정
	 * @param map
	 * @return
	 */
	Map<String, Object> modifyComment(Map<String, String> reqData);
	
	/**
	 * 리뷰,댓글 가져오기(회원)
	 * @param MEMBER_ID
	 * @param reqPage
	 * @return
	 */
	Map<String, Object> getReviewAll(String MEMBER_ID, int reqPage);
		
	/**
	 * 리뷰,댓글 가져오기(카페)
	 * @param CAFE_NO
	 * @param reqPage
	 * @return
	 */
	Map<String, Object> getReviewAll(int CAFE_NO, int reqPage);
	
}
