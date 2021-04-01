package com.kh.cafeinme.reviews.dao;

import java.util.List;
import java.util.Map;

import com.kh.cafeinme.reviews.vo.CommentVO;
import com.kh.cafeinme.reviews.vo.ReviewVO;

public interface ReviewDAO {
	//3.24추가 내카페 리뷰보기
	List<ReviewVO> getneedwritereview(int cafe_no,int startrec,int endrec);
	long totalneedwritereview(int cafe_no);
	//3.18추가 내리뷰 시간대별로 검색
	List<ReviewVO> getmyreviewwhen(String start, String end, String member_id, int StartRec, int endRec);
	long totalmyreviewwhen(String start,String end,String member_id);
	long totalmyreview(String member_id);

	/**
	 * 리뷰 등록
	 * @param reviewVO
	 * @return
	 */
	long registReview(ReviewVO reviewVO);
	
	/**
	 * 리뷰 등록 확인
	 * @param review_no
	 * @return
	 */
	int isReviewByReviewNO(long review_no);
	
	/**
	 * 주문테이블 리뷰등록여부 값 변경
	 * @param order_no
	 * @param review_yn
	 * @return
	 */
	int changeOrderReviewYN(int order_no, int review_yn);
	
	/**
	 * 리뷰 조회(1개)
	 * @param order_no
	 * @return
	 */
	ReviewVO getOneReview(int order_no);
	
	/**
	 * 리뷰 수정
	 * @param reviewVO
	 * @return
	 */
	int modifyReview(ReviewVO reviewVO);
	
	/**
	 * 리뷰 삭제
	 * @param REVIEW_NO
	 * @return
	 */
	int deleteReview(long REVIEW_NO);
	
	/**
	 * 댓글 등록
	 * @param comment_content
	 * @param cafe_no
	 * @param review_no
	 * @return
	 */
	long registComment(String comment_content, int cafe_no, long review_no);
	
	/**
	 * 댓글 존재여부 확인
	 * @param comment_no
	 * @return
	 */
	int isCommentByCommentNO(long comment_no);
	
	/**
	 * 리뷰 댓글 존재여부 변경
	 * @param review_no
	 * @param cmt_yn
	 * @return
	 */
	int changeReviewCmt(long review_no, int cmt_yn);
	
	/**
	 * 댓글 수정
	 * @param comment_content
	 * @param comment_no
	 * @return
	 */
	int modifyComment(String comment_content, long comment_no);
	
	/**
	 * 댓글 1개 가져오기(댓글번호)
	 * @param comment_no
	 * @return
	 */
	CommentVO getOneComment(long comment_no);
	
	/**
	 * 리뷰,댓글 가져오기(회원)
	 * @param MEMBER_ID
	 * @param startRec
	 * @param endRec
	 * @return
	 */
	List<ReviewVO> getReviewAll(String MEMBER_ID, int startRec, int endRec);
		
	/**
	 * 리뷰,댓글 가져오기(카페)
	 * @param CAFE_NO
	 * @param startRec
	 * @param endRec
	 * @return
	 */
	List<ReviewVO> getReviewAll(int CAFE_NO, int startRec, int endRec);
	
	/**
	 * 전체 레코드 수
	 * @return
	 */
	long totalRecordCount(int cafe_no);
	
	/**
	 * 리뷰번호 찾기(by 댓글번호)
	 * @param COMMENT_NO
	 * @return
	 */
	long findReview(long COMMENT_NO);
	
}
