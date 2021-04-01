package com.kh.cafeinme.orders.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import com.kh.cafeinme.common.dao.CodeDAO;
import com.kh.cafeinme.orders.vo.CartVO;
import com.kh.cafeinme.orders.vo.OrderDetailVO;
import com.kh.cafeinme.orders.vo.OrderVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Repository
@RequiredArgsConstructor
@Slf4j
public class OrderDAOImpl implements OrderDAO {

	private final JdbcTemplate jt;
	private final SqlSession sqlSession;
	
	CodeDAO codeDAO;
	
	/**
	 * 장바구니 등록
	 * @param cartVO
	 * @return
	 */
	@Override
	public int insertCartItem(CartVO cartVO) {
		return sqlSession.insert("mappers.orderDAO-mapper.insertCartItem", cartVO);
	}

	/**
	 * 장바구니 상품 다른 카페상품유무 확인
	 * @param MEMBER_ID
	 * @param CAFE_NO
	 * @return
	 */
	@Override
	public int isDifferentCafeItem(String MEMBER_ID, int CAFE_NO) {
		Map<String, Object> map = new HashMap<>();
		map.put("member_id",MEMBER_ID);
		map.put("cafe_no", CAFE_NO);
		return sqlSession.selectOne("mappers.orderDAO-mapper.isDifferentCafeItem", map);
	}
	
	/**
	 * 장바구니 상품 같은 상품유무 확인
	 * @param MEMBER_ID
	 * @param MENU_NO
	 * @return
	 */
	@Override
	public int isCartItem(String MEMBER_ID, int MENU_NO) {
		Map<String, Object> map = new HashMap<>();
		map.put("member_id", MEMBER_ID);
		map.put("menu_no", MENU_NO);
		return sqlSession.selectOne("mappers.orderDAO-mapper.isCartItem", map);
	}
	
	/**
	 * 장바구니 상품 갯수 가져오기
	 * @param MEMBER_ID
	 * @param MENU_NO
	 * @return
	 */
	@Override
	public int countOfCartItem(String MEMBER_ID, int MENU_NO) {
		Map<String, Object> map = new HashMap<>();
		map.put("member_id", MEMBER_ID);
		map.put("menu_no", MENU_NO);
		return sqlSession.selectOne("mappers.orderDAO-mapper.countOfCartItem", map);
	}
	
	/**
	 * 장바구니 전체조회
	 * @param MEMBER_ID
	 * @return
	 */
	@Override
	public List<CartVO> cartItemList(String MEMBER_ID) {
		Map<String, Object> map = new HashMap<>();
		map.put("member_id", MEMBER_ID);
		return sqlSession.selectList("mappers.orderDAO-mapper.cartItemList", map);
	}

	/**
	 * 장바구니 수정
	 * @param MEMBER_ID
	 * @param MENU_NO
	 * @param MENU_COUNT
	 * @return
	 */
	@Override
	public int modifyCartItem(String MEMBER_ID, int MENU_NO, int MENU_COUNT) {
		Map<String, Object> map = new HashMap<>();
		map.put("member_id", MEMBER_ID);
		map.put("menu_no", MENU_NO);
		map.put("menu_count", MENU_COUNT);
		return sqlSession.update("mappers.orderDAO-mapper.modifyCartItem", map);
	}

	/**
	 * 장바구니 삭제
	 * @param MEMBER_ID
	 * @param MENU_NO
	 * @return
	 */
	@Override
	public int deleteCartItem(String MEMBER_ID, int MENU_NO) {
		Map<String, Object> map = new HashMap<>();
		map.put("member_id", MEMBER_ID);
		map.put("menu_no", MENU_NO);
		return sqlSession.delete("mappers.orderDAO-mapper.deleteCartItem", map);
	}
	
	/**
	 * 장바구니 전체삭제
	 * @param MEMBER_ID
	 * @return
	 */
	@Override
	public int deleteCartItemAll(String member_id) {
		return sqlSession.delete("mappers.orderDAO-mapper.deleteCartItemAll", member_id);
	}
	
	/* 주문하기 */
	/**
	 * 주문등록
	 * @param orderVO
	 * @return
	 */
	@Override
	public int registOrder(OrderVO orderVO) {
		sqlSession.insert("mappers.orderDAO-mapper.registOrder", orderVO);
		return orderVO.getORDER_NO();
	}

	/**
	 * 상세주문등록
	 * @param ORDER_NO
	 * @param cartVO
	 * @return
	 */
	@Override
	public int registOrderDetail(int ORDER_NO, CartVO cartVO) {
		Map<String, Object> map = new HashMap<>();
		map.put("order_no", ORDER_NO);
		map.put("cartVO", cartVO);
		return sqlSession.insert("mappers.orderDAO-mapper.registOrderDetail", map);
	}
	
	/**
	 * 메뉴번호로 메뉴정보 가져오기
	 * @param MENU_NO
	 * @return
	 */
	@Override
	public int findCafeNObyMenuNO(int MENU_NO) {
		return sqlSession.selectOne("mappers.orderDAO-mapper.findCafeNObyMenuNO", MENU_NO);
	}
	
	/**
	 * 주문번호로 카페번호 찾기
	 * @param ORDER_NO
	 * @return
	 */
	@Override
	public int findCafeNObyOrderNO(int ORDER_NO) {
		Map<String, Object> map = new HashMap<>();
		map.put("order_no", ORDER_NO);
		return sqlSession.selectOne("mappers.orderDAO-mapper.findCafeNObyOrderNO", map);
	}
	
	/**
	 * 주문상태변경
	 * @param MEMBER_ID
	 * @param ORDER_NO
	 * @param ORDER_DATE
	 * @param ORDER_STATUS
	 * @return
	 */
	@Override
	public int changeOrderStatus(String MEMBER_ID, int ORDER_NO, Date ORDER_DATE, String ORDER_STATUS) {
		Map<String, Object> map = new HashMap<>();
		map.put("member_id", MEMBER_ID);
		map.put("order_no", ORDER_NO);
		map.put("order_date", ORDER_DATE);
		map.put("order_status", ORDER_STATUS);
		return sqlSession.update("mappers.orderDAO-mapper.changeOrderStatus", map);
	}
	
	/**
	 * 주문상태변경
	 * @param ORDER_NO
	 * @param ORDER_DATE
	 * @param ORDER_STATUS
	 * @return
	 */
	@Override
	public int changeOrderStatus(int ORDER_NO, String ORDER_DATE, String ORDER_STATUS) {
		Map<String, Object> map = new HashMap<>();
		map.put("order_no", ORDER_NO);
		map.put("order_date", ORDER_DATE);
		map.put("order_status", ORDER_STATUS);
		return sqlSession.update("mappers.orderDAO-mapper.changeOrderStatus2", map);
	}
	
	/**
	 * 전체주문내역
	 * @param MEMBER_ID
	 * @return
	 */
	@Override
	public List<OrderVO> orderList(String MEMBER_ID) {
		return sqlSession.selectList("mappers.orderDAO-mapper.orderList", MEMBER_ID);
	}
	
	/**
	 * 주문내역 가져오기(고객용)
	 * @param member_id
	 * @param startDt
	 * @param endDt
	 * @return
	 */
	@Override
	public List<OrderVO> getCustOrderList(String member_id, String startDt, String endDt) {
		Map<String, Object> map = new HashMap<>();
		map.put("member_id", member_id);
		map.put("startDt", startDt);
		map.put("endDt", endDt);
		return sqlSession.selectList("mappers.orderDAO-mapper.getCustOrderList", map);
	}
	
	/**
	 * 주문 총 레코드 수
	 * @param member_id
	 * @return
	 */
	@Override
	public int orderListTotalRecord(String member_id, String startDt, String endDt) {
		Map<String, Object> map = new HashMap<>();
		map.put("member_id", member_id);
		map.put("startDt", startDt);
		map.put("endDt", endDt);
		return sqlSession.selectOne("mappers.orderDAO-mapper.orderListTotalRecord", map);
	}
	
	/**
	 * 주문내역 가져오기(고객용, 페이지)
	 * @param member_id
	 * @param startDt
	 * @param endDt
	 * @return
	 */
	@Override
	public List<OrderVO> getCustOrderList(String member_id, String startDt, String endDt, int startRec, int endRec) {
		Map<String, Object> map = new HashMap<>();
		map.put("member_id", member_id);
		map.put("startDt", startDt);
		map.put("endDt", endDt);
		map.put("startRec", startRec);
		map.put("endRec", endRec);
		return sqlSession.selectList("mappers.orderDAO-mapper.getCustOrderListpage", map);
	}
	
	/**
	 * 주문내용(고객용)
	 * @param member_id
	 * @param startDt
	 * @param endDt
	 * @return
	 */
	@Override
	public List<OrderDetailVO> getCustOrderContent(String member_id, String startDt, String endDt) {
		Map<String, Object> map = new HashMap<>();
		map.put("member_id", member_id);
		map.put("startDt", startDt);
		map.put("endDt", endDt);
		return sqlSession.selectList("mappers.orderDAO-mapper.getCustOrderContent", map);
	}

	/**
	 * 주문상세보기
	 * @return
	 */
	@Override
	public List<OrderDetailVO> getOrderDetail(int ORDER_NO) {
		return sqlSession.selectList("mappers.orderDAO-mapper.getOrderDetail", ORDER_NO);
	}

	/**
	 * 주문관리목록
	 * @param map
	 * @return
	 */
	@Override
	public List<OrderVO> getOrderManagementList(Map<String, Object> map) {
		return sqlSession.selectList("mappers.orderDAO-mapper.getOrderManagementList", map);
	}
	
	/**
	 * 주문관리목록(아이템)
	 * @param map
	 * @return
	 */
	@Override
	public List<OrderDetailVO> getOrderDetailList(Map<String, Object> map) {
		return sqlSession.selectList("mappers.orderDAO-mapper.getOrderDetailList", map);
	}
	
	/**
	 * 맴버아이디로 카페번호 찾기
	 * @param member_id
	 * @return
	 */
	@Override
	public int getCafeNObyMemberID(String member_id) {
		return sqlSession.selectOne("mappers.orderDAO-mapper.getCafeNObyMemberID", member_id);
	}
	
	/**
	 * 판매목록 가져오기
	 * @param cafe_no
	 * @param startDt
	 * @param endDt
	 * @param order_status
	 * @param startRec
	 * @param endRec
	 * @return
	 */
	@Override
	public List<OrderVO> getSalesManagementList(int cafe_no, String startDt, String endDt, String order_status,
			int startRec, int endRec) {
		Map<String, Object> map = new HashMap<>();
		map.put("cafe_no", cafe_no);
		map.put("startDt", startDt);
		map.put("endDt", endDt);
		map.put("order_status", order_status);
		map.put("startRec", startRec);
		map.put("endRec", endRec);
		return sqlSession.selectList("mappers.orderDAO-mapper.getSalesManagementList", map);
	}
	
	/**
	 * 판매목록 총 레코드 수
	 * @param cafe_no
	 * @param startDt
	 * @param endDt
	 * @param order_status
	 * @return
	 */
	@Override
	public int salesListTotalRecord(int cafe_no, String startDt, String endDt, String order_status) {
		Map<String, Object> map = new HashMap<>();
		map.put("cafe_no", cafe_no);
		map.put("startDt", startDt);
		map.put("endDt", endDt);
		map.put("order_status", order_status);
		return sqlSession.selectOne("mappers.orderDAO-mapper.salesListTotalRecord", map);
	}
}
