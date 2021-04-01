package com.kh.cafeinme.reviews.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.cafeinme.reviews.vo.CommentVO;
import com.kh.cafeinme.reviews.vo.ReviewVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ReviewDAOImpl implements ReviewDAO {

	private final JdbcTemplate jt;
	private final SqlSession sqlSession;
	
	/**
	 * 리뷰 등록
	 * @param reviewVO
	 * @return
	 */
	@Override
	public long registReview(ReviewVO reviewVO) {
		sqlSession.insert("mappers.ReviewDAO-mapper.registReview", reviewVO);
		return reviewVO.getREVIEW_NO();
	}
	
	/**
	 * 리뷰 등록 확인
	 * @param review_no
	 * @return
	 */
	@Override
	public int isReviewByReviewNO(long review_no) {
		return sqlSession.selectOne("mappers.ReviewDAO-mapper.isReviewByReviewNO", review_no);
	}
	
	/**
	 * 주문테이블 리뷰등록여부 값 변경
	 * @param order_no
	 * @param review_yn
	 * @return
	 */
	@Override
	public int changeOrderReviewYN(int order_no, int review_yn) {
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("order_no", order_no);
		map.put("review_yn", review_yn);
		return sqlSession.update("mappers.ReviewDAO-mapper.changeOrderReviewYN", map);
	}
	
	/**
	 * 리뷰 조회(1개, 주문번호)
	 * @param order_no
	 * @return
	 */
	@Override
	public ReviewVO getOneReview(int order_no) {
		return sqlSession.selectOne("mappers.ReviewDAO-mapper.getOneReview", order_no);
	}
	
	/**
	 * 리뷰 수정
	 * @param reviewVO
	 * @return
	 */
	@Override
	public int modifyReview(ReviewVO reviewVO) {
		return sqlSession.update("mappers.ReviewDAO-mapper.modifyReview", reviewVO);
	}
	
	/**
	 * 리뷰 삭제
	 * @param REVIEW_NO
	 * @return
	 */
	@Override
	public int deleteReview(long REVIEW_NO) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete reviews where review_no = ?");
		int result = jt.update(sql.toString(), REVIEW_NO);
		return result;
	}
	
	/**
	 * 댓글 등록
	 * @param comment_content
	 * @param cafe_no
	 * @param review_no
	 * @return
	 */
	@Override
	public long registComment(String comment_content, int cafe_no, long review_no) {
		CommentVO commentVO = new CommentVO();
		commentVO.setCAFE_NO(cafe_no);
		commentVO.setCOMMENT_CONTENT(comment_content);
		commentVO.setREVIEW_NO(review_no);
		sqlSession.insert("mappers.ReviewDAO-mapper.registComment", commentVO);
		return commentVO.getCOMMENT_NO();
	}
	
	/**
	 * 댓글 존재여부 확인
	 * @param comment_no
	 * @return
	 */
	@Override
	public int isCommentByCommentNO(long comment_no) {
		return sqlSession.selectOne("mappers.ReviewDAO-mapper.isCommentByCommentNO", comment_no);
	}
	
	/**
	 * 리뷰 댓글 존재여부 변경
	 * @param review_no
	 * @param cmt_yn
	 * @return
	 */
	@Override
	public int changeReviewCmt(long review_no, int cmt_yn) {
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("review_no", review_no);
		map.put("cmt_yn", cmt_yn);
		return sqlSession.insert("mappers.ReviewDAO-mapper.changeReviewCmt", map);
	}
	
	/**
	 * 댓글 수정
	 * @param comment_content
	 * @param comment_no
	 * @return
	 */
	@Override
	public int modifyComment(String comment_content, long comment_no) {
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("comment_content", comment_content);
		map.put("comment_no", comment_no);
		return sqlSession.insert("mappers.ReviewDAO-mapper.modifyComment", map);
	}
	
	/**
	 * 댓글 1개 가져오기(댓글번호)
	 * @param comment_no
	 * @return
	 */
	@Override
	public CommentVO getOneComment(long comment_no) {
		return sqlSession.selectOne("mappers.ReviewDAO-mapper.getOneComment", comment_no);
	}
	
	/**
	 * 3.18 수정
	 * 리뷰,댓글 가져오기(회원)
	 * @param MEMBER_ID
	 * @param startRec
	 * @param endRec
	 * @return
	 */
	@Override
	public List<ReviewVO> getReviewAll(String MEMBER_ID, int startRec, int endRec) {
		StringBuilder SQL = new StringBuilder();
		SQL.append("select * ");
		SQL.append("from( ");
		SQL.append("select ROW_NUMber() over(order by t1.review_no desc) as num,t1.review_nickname,t1.review_items,t1.review_content,t1.review_star,t1.review_date,t1.cafe_no,t2.cafe_name ");
		SQL.append("from reviews t1 inner join cafe t2 on t1.cafe_no = t2.cafe_no ");
		SQL.append("where  member_id = ?) ");
		SQL.append("where num between ? and ?");
		List<ReviewVO> list = jt.query(SQL.toString(), new BeanPropertyRowMapper<ReviewVO>(ReviewVO.class),MEMBER_ID,startRec,endRec);
		return list;
		}

	/**
	 * 3.18 수정
	 * 리뷰,댓글 가져오기(카페)
	 * @param CAFE_NO
	 * @param startRec
	 * @param endRec
	 * @return
	 */
	@Override
	public List<ReviewVO> getReviewAll(int CAFE_NO, int startRec, int endRec) {
		StringBuilder SQL = new StringBuilder();
		SQL.append("select * ");
		SQL.append("from (");
		SQL.append("select row_number() over(order by t1.review_no desc) as num,t1.review_no,t1.review_nickname,t1.review_items,t1.review_content,t1.review_star,t1.review_cmt,t1.member_id,");
		SQL.append("t1.review_date,nvl(t2.comment_content,'nocontent') as comment_content,nvl(t2.comment_date,'00/01/01') as comment_date ");
		SQL.append("from reviews t1 left outer join comments t2 on t2.review_no = t1.review_no ");
		SQL.append("where t1.cafe_no = ?) ");
		SQL.append("where num between ? and ?");
		
		List<ReviewVO> list = jt.query(SQL.toString(), 
				new RowMapper<ReviewVO>() {
			@Override
			public ReviewVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ReviewVO reviewVO = new ReviewVO();
				reviewVO.setREVIEW_NO(rs.getLong("review_no"));
				reviewVO.setREVIEW_NICKNAME(rs.getString("review_nickname"));
				reviewVO.setREVIEW_ITEMS(rs.getString("review_items"));
				reviewVO.setREVIEW_CONTENT(rs.getString("review_content"));
				reviewVO.setREVIEW_STAR(rs.getInt("review_star"));
				reviewVO.setREVIEW_DATE(rs.getTimestamp("review_date"));
				reviewVO.setREVIEW_CMT(rs.getInt("review_cmt"));
				reviewVO.setMEMBER_ID(rs.getString("member_id"));
				
				CommentVO commentVO = new CommentVO();
				commentVO.setCOMMENT_CONTENT(rs.getString("comment_content"));
				commentVO.setCOMMENT_DATE(rs.getTimestamp("comment_date"));
				reviewVO.setCommentVO(commentVO);
				return reviewVO;
			}
		}, CAFE_NO, startRec, endRec);
		return list;
	}
	
	/**
	 * 전체 레코드 수
	 * @return
	 */
	@Override
	public long totalRecordCount(int cafe_no) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) from reviews where cafe_no = ?");
		return jt.queryForObject(sql.toString(), Long.class,cafe_no);
	}
	
	/**
	 * 리뷰번호 찾기(by 댓글번호)
	 * @param COMMENT_NO
	 * @return
	 */
	@Override
	public long findReview(long COMMENT_NO) {
		StringBuilder sql = new StringBuilder();
		sql.append("select review_no from comments ");
		sql.append("where comment_no = ?");
		return jt.queryForObject(sql.toString(), Long.class, COMMENT_NO);
	}
	
	//3.18추가 내리뷰 시간대별로보기
	@Override
	public List<ReviewVO> getmyreviewwhen(String start, String end, String member_id, int StartRec, int endRec) {
		StringBuilder SQL = new StringBuilder();
		SQL.append("select * ");
		SQL.append("from( ");
		SQL.append("select ROW_NUMber() over(order by t1.review_no desc) as num,t1.review_nickname,t1.review_items,t1.review_content,t1.review_star,t1.review_date,t1.review_cmt,t2.cafe_name ");
		SQL.append("from reviews t1 inner join cafe t2 on t1.cafe_no = t2.cafe_no ");
		SQL.append("where to_char(t1.review_date,'YYYY-MM-DD') between ? and ? and member_id = ?) ");
		SQL.append("where num between ? and ?");
		List<ReviewVO> list = jt.query(SQL.toString(), new BeanPropertyRowMapper<ReviewVO>(ReviewVO.class),start,end,member_id,StartRec,endRec);
		return list;
	}

	@Override
	public long totalmyreviewwhen(String start, String end, String member_id) {
		StringBuilder SQL = new StringBuilder();
		SQL.append("select count(*) ");
		SQL.append("from( ");
		SQL.append("select ROW_NUMber() over(order by t1.review_no desc) as num,t1.review_nickname,t1.review_items,t1.review_content,t1.review_star,t1.review_date,t1.review_cmt,t2.cafe_name ");
		SQL.append("from reviews t1 inner join cafe t2 on t1.cafe_no = t2.cafe_no ");
		SQL.append("where to_char(t1.review_date,'YYYY-MM-DD') between ? and ? and member_id = ?)");
		return jt.queryForObject(SQL.toString(), Long.class,start,end,member_id);
	}

	@Override
	public long totalmyreview(String member_id) {
		StringBuilder SQL = new StringBuilder();
		SQL.append("select count(*) from reviews where member_id = ?");
		return jt.queryForObject(SQL.toString(), Long.class,member_id);
	}

//	@Override
//	public List<ReviewVO> getneedwritereview(int cafe_no, int startrec,int endrec) {
//		StringBuilder SQL = new StringBuilder();
//		SQL.append("select * ");
//		SQL.append("from( ");
//		SQL.append("select  row_number() over (order by t1.review_no desc) as num ,t1.review_no,t1.review_nickname,t1.review_content,t1.review_star,t1.review_date,t1.review_cmt,");
//		SQL.append("NVL(t2.comment_content,'nocomment') as comment_content, t2.comment_no ");
//		SQL.append("from reviews t1 LEFT OUTER JOIN comments t2 on t2.review_no = t1.review_no ");
//		SQL.append("where t1.cafe_no = ?) ");
//		SQL.append("where num between ? and ?");
//		List<ReviewVO> list = jt.query(SQL.toString(),new RowMapper<ReviewVO>() {
//			@Override
//			public ReviewVO mapRow(ResultSet rs, int rowNum) throws SQLException {
//				ReviewVO reviewVO = new ReviewVO();
//				reviewVO.setREVIEW_NO(rs.getLong("review_no"));
//				reviewVO.setREVIEW_NICKNAME(rs.getString("review_nickname"));
//				reviewVO.setREVIEW_CONTENT(rs.getString("review_content"));
//				reviewVO.setREVIEW_STAR(rs.getInt("review_star"));
//				reviewVO.setREVIEW_DATE(rs.getTimestamp("review_date"));
//				reviewVO.setREVIEW_CMT(rs.getInt("review_cmt"));
//				CommentVO co = new CommentVO();
//				co.setCOMMENT_NO(Long.parseLong(rs.getString("comment_no")));
//				co.setCOMMENT_CONTENT(rs.getString("comment_content"));
//				reviewVO.setCommentVO(co);
//				return reviewVO;
//			}
//			
//		},cafe_no,startrec,endrec);
//		return list;
//	}
	
	@Override
	public List<ReviewVO> getneedwritereview(int cafe_no, int startrec, int endrec) {
		Map<String, Object> map = new HashMap<>();
		map.put("cafe_no", cafe_no);
		map.put("startrec", startrec);
		map.put("endrec", endrec);
		return sqlSession.selectList("mappers.ReviewDAO-mapper.getneedwritereview", map);
	}

	@Override
	public long totalneedwritereview(int cafe_no) {
		String SQL = "select count(*) from reviews where cafe_no =? and review_cmt= 0";
		return jt.queryForObject(SQL, Long.class,cafe_no);
	}
}
