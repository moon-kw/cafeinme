package com.kh.cafeinme.orders.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.kh.cafeinme.orders.vo.CartVO;
import com.kh.cafeinme.orders.vo.OrderDetailVO;
import com.kh.cafeinme.orders.vo.OrderVO;

public interface OrderDAO {
	
	/**
	 * 장바구니 등록
	 * @param cartVO
	 * @return
	 */
	int insertCartItem(CartVO cartVO);
	
	/**
	 * 장바구니 상품 다른 카페상품유무 확인
	 * @param MEMBER_ID
	 * @param CAFE_NO
	 * @return
	 */
	int isDifferentCafeItem(String MEMBER_ID, int CAFE_NO);
	
	/**
	 * 장바구니 상품 같은 상품유무 확인
	 * @param MEMBER_ID
	 * @param MENU_NO
	 * @return
	 */
	int isCartItem(String MEMBER_ID, int MENU_NO);
	
	/**
	 * 장바구니 상품 갯수 가져오기
	 * @param MEMBER_ID
	 * @param MENU_NO
	 * @return
	 */
	int countOfCartItem(String MEMBER_ID, int MENU_NO);
	
	/**
	 * 장바구니 전체조회
	 * @param MEMBER_ID
	 * @return
	 */
	List<CartVO> cartItemList(String MEMBER_ID);
	
	/**
	 * 장바구니 수정
	 * @param MEMBER_ID
	 * @param MENU_NO
	 * @param MENU_COUNT
	 * @return
	 */
	int modifyCartItem(String MEMBER_ID, int MENU_NO, int MENU_COUNT);
	
	/**
	 * 장바구니 삭제
	 * @param MEMBER_ID
	 * @param MENU_NO
	 * @return
	 */
	int deleteCartItem(String MEMBER_ID, int MENU_NO);
	
	/**
	 * 장바구니 전체삭제
	 * @param MEMBER_ID
	 * @return
	 */
	int deleteCartItemAll(String member_id);
	
	/* 주문하기 */
	/**
	 * 주문등록
	 * @param orderVO
	 * @return
	 */
	int registOrder(OrderVO orderVO);
	
	/**
	 * 상세주문등록
	 * @param ORDER_NO
	 * @param cartVO
	 * @return
	 */
	int registOrderDetail(int ORDER_NO, CartVO cartVO);
	
	/**
	 * 메뉴번호로 카페번호 찾기
	 * @param MENU_NO
	 * @return
	 */
	int findCafeNObyMenuNO(int MENU_NO);
	
	/**
	 * 주문번호로 카페번호 찾기
	 * @param ORDER_NO
	 * @return
	 */
	int findCafeNObyOrderNO(int ORDER_NO);
	
	
	/**
	 * 주문상태변경
	 * @param MEMBER_ID
	 * @param ORDER_NO
	 * @param ORDER_DATE
	 * @param ORDER_STATUS
	 * @return
	 */
	int changeOrderStatus(String MEMBER_ID, int ORDER_NO, Date ORDER_DATE, String ORDER_STATUS);
		
	/**
	 * 주문상태변경
	 * @param ORDER_NO
	 * @param ORDER_DATE
	 * @param ORDER_STATUS
	 * @return
	 */
	int changeOrderStatus(int ORDER_NO, String ORDER_DATE, String ORDER_STATUS);
	
	
	
	/**
	 * 전체주문내역
	 * @param MEMBER_ID
	 * @return
	 */
	List<OrderVO> orderList(String MEMBER_ID);
	
	/**
	 * 주문내역 가져오기(고객용)
	 * @param member_id
	 * @param startDt
	 * @param endDt
	 * @return
	 */
	List<OrderVO> getCustOrderList(String member_id, String startDt, String endDt);
	
	/**
	 * 주문 총 레코드 수
	 * @param member_id
	 * @return
	 */
	int orderListTotalRecord(String member_id, String startDt, String endDt);
	
	/**
	 * 주문내역 가져오기(고객용, 페이지)
	 * @param member_id
	 * @param startDt
	 * @param endDt
	 * @param startRec
	 * @param endRec
	 * @return
	 */
	List<OrderVO> getCustOrderList(String member_id, String startDt, String endDt, int startRec, int endRec);
	
	/**
	 * 주문내용(고객용)
	 * @param member_id
	 * @param startDt
	 * @param endDt
	 * @return
	 */
	List<OrderDetailVO> getCustOrderContent(String member_id, String startDt, String endDt);
	//주문취소
//	int cancelOrder(String MEMBER_ID, long ORDER_NO, Date ORDER_DATE);
	
	//상세주문취소
//	int cancelOrderDetail(int ORDER_NO);
	
	//주문내역 삭제
//	int deleteOrder(String MEMBER_ID, int ORDER_NO);
	
	//주문상세내역 삭제
//	int deleteOrderDetail(int ORDER_NO);
	
	/**
	 * 주문상세보기
	 * @return
	 */
	List<OrderDetailVO> getOrderDetail(int ORDER_NO);

	/**
	 * 주문관리목록
	 * @param map
	 * @return
	 */
	List<OrderVO> getOrderManagementList(Map<String, Object> map);
	
	/**
	 * 주문관리목록(아이템)
	 * @param map
	 * @return
	 */
	List<OrderDetailVO> getOrderDetailList(Map<String, Object> map);
	
	/**
	 * 맴버아이디로 카페번호 찾기
	 * @param member_id
	 * @return
	 */
	int getCafeNObyMemberID(String member_id);
	
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
	List<OrderVO> getSalesManagementList(int cafe_no, String startDt, String endDt, String order_status, int startRec, int endRec);
	
	/**
	 * 판매목록 총 레코드 수
	 * @param cafe_no
	 * @param startDt
	 * @param endDt
	 * @param order_status
	 * @return
	 */
	int salesListTotalRecord(int cafe_no, String startDt, String endDt, String order_status);
}
