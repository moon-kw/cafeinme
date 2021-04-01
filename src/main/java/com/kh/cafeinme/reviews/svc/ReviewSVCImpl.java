package com.kh.cafeinme.reviews.svc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.cafeinme.common.paging.PageCriteria;
import com.kh.cafeinme.member.vo.MemberVO;
import com.kh.cafeinme.mycafe.dao.MycafeDAO;
import com.kh.cafeinme.orders.svc.OrderSVC;
import com.kh.cafeinme.reviews.dao.ReviewDAO;
import com.kh.cafeinme.reviews.vo.CommentVO;
import com.kh.cafeinme.reviews.vo.ReviewVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ReviewSVCImpl implements ReviewSVC {
	private final ReviewDAO reviewDAO;
	private final MycafeDAO md;
	private final OrderSVC orderSVC;
	@Qualifier("pc_10")
	private final PageCriteria pc;
	@Qualifier("pc_1")
	private final PageCriteria pc_1;
	
	
	/**
	 * 리뷰 등록
	 * @param reviewVO
	 * @return
	 */
	@Transactional
	@Override
	public Map<String, Object> registReview(MemberVO memberVO, Map<String,String> reqData) {
		ReviewVO reviewVO = new ReviewVO();
		//필요한 데이터 넣기
		reviewVO.setMEMBER_ID(memberVO.getMember_id());
		reviewVO.setREVIEW_NICKNAME(memberVO.getMember_nickname());
		reviewVO.setREVIEW_CONTENT(reqData.get("review_content"));
		reviewVO.setREVIEW_STAR(Integer.parseInt(reqData.get("review_star")));
		reviewVO.setREVIEW_ITEMS(reqData.get("review_items"));
		reviewVO.setCAFE_NO(Integer.parseInt(reqData.get("cafe_no")));
		reviewVO.setORDER_NO(Integer.parseInt(reqData.get("order_no")));

		long review_no = reviewDAO.registReview(reviewVO);
		int result = reviewDAO.isReviewByReviewNO(review_no);
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(result == 1) {			
			int a = reviewDAO.changeOrderReviewYN(Integer.parseInt(reqData.get("order_no")), 1);
			map.put("rtcd", "00");
			map.put("msg", "리뷰가 등록되었습니다");
		}else {
			map.put("rtcd", "01");
			map.put("msg", "리뷰 등록을 실패하였습니다");
		}
		return map;
	}
	
	/**
	 * 리뷰 조회(1개, 주문번호)
	 * @param order_no
	 * @return
	 */
	@Override
	public Map<String, Object> getOneReview(int order_no) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", reviewDAO.getOneReview(order_no));
		return map;
	}
	
	/**
	 * 리뷰 수정
	 * @param reqData
	 * @return
	 */
	@Override
	public Map<String, Object> modifyReview(Map<String, String> reqData) {
		ReviewVO reviewVO = new ReviewVO();
		reviewVO.setREVIEW_CONTENT(reqData.get("review_content"));
		reviewVO.setREVIEW_STAR(Integer.parseInt(reqData.get("review_star")));
		reviewVO.setORDER_NO(Integer.parseInt(reqData.get("order_no")));
		int result = reviewDAO.modifyReview(reviewVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(result == 1) {			
			map.put("rtcd", "00");
			map.put("msg", "리뷰가 수정되었습니다");
		}else {
			map.put("rtcd", "01");
			map.put("msg", "리뷰 수정을 실패하였습니다");
		}
		return map;
	}
	
	/**
	 * 댓글 등록
	 * @param map
	 * @return
	 */
	@Transactional
	@Override
	public Map<String, Object> registComment(Map<String, String> reqData) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		int cafe_no = md.getmycafe(reqData.get("member_id")).getCafe_no();
		//댓글 등록
		long comment_no = reviewDAO.registComment(reqData.get("comment_content"), cafe_no, Long.parseLong(reqData.get("review_no")));
		//등록이 되었는지 확인
		int result = reviewDAO.isCommentByCommentNO(comment_no);
		if(result == 1) {
			//리뷰 댓글 존재여부 1로 바꾸기
			int a = reviewDAO.changeReviewCmt(Long.parseLong(reqData.get("review_no")), 1);
			//댓글 하나 가져오기
			CommentVO commentVO = reviewDAO.getOneComment(comment_no);
			map.put("rtcd", "00");
			map.put("msg", "댓글을 등록하였습니다");			
			map.put("commentVO", commentVO);
		}else {
			map.put("rtcd", "01");
			map.put("msg", "댓글 등록에 실패하였습니다");
		}
		return map;
	}
	
	/**
	 * 댓글 수정
	 * @param map
	 * @return
	 */
	@Override
	public Map<String, Object> modifyComment(Map<String, String> reqData) {
		Map<String, Object> map = new HashMap<String, Object>();
		//댓글 수정
		int result = reviewDAO.modifyComment(reqData.get("comment_content"), Long.parseLong(reqData.get("comment_no")));
		
		if(result == 1) {
			map.put("rtcd", "00");
			map.put("msg", "댓글을 수정하였습니다");			
		}else {
			map.put("rtcd", "01");
			map.put("msg", "댓글 수정에 실패하였습니다");
		}
		return map;
	}
	
	/**
	 * 리뷰 삭제
	 * @param REVIEW_NO
	 * @return
	 */
	@Override
	public int deleteReview(long REVIEW_NO) {
		return reviewDAO.deleteReview(REVIEW_NO);
	}

	/**
	 * 리뷰,댓글 가져오기(회원)
	 * @param MEMBER_ID
	 * @param reqPage
	 * @return
	 */
	@Override
	public Map<String, Object> getReviewAll(String MEMBER_ID, int reqPage) {
		
		Map<String,Object> map = new HashMap<>();
		
		//1)요청페이지
		pc.getRc().setReqPage(reqPage);
		//2)총레코드정보
		pc.setTotalRec(reviewDAO.totalmyreview(MEMBER_ID));
		//3)페이징계산
		pc.calculatePaging();
		
		//4)목록가져오기
		int startRec  = pc.getRc().getStartRec();
		int endRec 		= pc.getRc().getEndRec();
		List<ReviewVO> list = reviewDAO.getReviewAll(MEMBER_ID, startRec, endRec);
		
		//5)map객체에 게시글 목록, pc담기
		map.put("reviewList", list);
		map.put("pc",pc);
		
		return map;
	}

	/**
	 * 리뷰,댓글 가져오기(카페)
	 * @param CAFE_NO
	 * @param reqPage
	 * @return
	 */
	@Override
	public Map<String, Object> getReviewAll(int CAFE_NO, int reqPage) {
		
		Map<String,Object> map = new HashMap<>();
		
		//1)요청페이지
		pc.getRc().setReqPage(reqPage);
		//2)총레코드정보
		pc.setTotalRec(reviewDAO.totalRecordCount(CAFE_NO));
		//3)페이징계산
		pc.calculatePaging();
		
		//4)목록가져오기
		int startRec  = pc.getRc().getStartRec();
		int endRec 		= pc.getRc().getEndRec();
		List<ReviewVO> list = reviewDAO.getReviewAll(CAFE_NO, startRec, endRec);
		
		//5)map객체에 게시글 목록, pc담기
		map.put("reviewList", list);
		map.put("pc",pc);
		
		return map;
	}

	@Override
	public Map<String,Object> getmyreviewwhen(String start, String end, String member_id,int reqpage) {
		Map<String,Object> map = new HashMap<>();
		pc.getRc().setReqPage(reqpage);
		pc.setTotalRec(reviewDAO.totalmyreviewwhen(start, end, member_id));
		pc.calculatePaging();
		int startRec  = pc.getRc().getStartRec();
		int endRec 		= pc.getRc().getEndRec();
		List<ReviewVO> list = reviewDAO.getmyreviewwhen(start, end, member_id, startRec, endRec);
		map.put("reviewList", list);
		map.put("pc",pc);
		return map;
	}

	@Override
	public Map<String, Object> getneedwritereview(String member_id, int recodenum) {
		int cafe_no = md.getmycafe(member_id).getCafe_no();
		Map<String,Object> map = new HashMap<>();
		pc_1.getRc().setReqPage(recodenum);
		pc_1.setTotalRec(reviewDAO.totalRecordCount(cafe_no));
		pc_1.calculatePaging();
		int startrec = pc_1.getRc().getStartRec();
		int endrec = pc_1.getRc().getEndRec();
		List<ReviewVO> rv = reviewDAO.getneedwritereview(cafe_no, startrec,endrec);
		map.put("review", rv);
		map.put("pc", pc_1);
		return map;
	}


}
