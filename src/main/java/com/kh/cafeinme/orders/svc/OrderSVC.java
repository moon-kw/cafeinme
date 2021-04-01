package com.kh.cafeinme.orders.svc;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.kh.cafeinme.orders.vo.CartVO;
import com.kh.cafeinme.orders.vo.OrderDetailVO;
import com.kh.cafeinme.orders.vo.OrderVO;

public interface OrderSVC {

	// 장바구니 등록
	int insertCartItem(CartVO cartVO);
	
	/**
	 * 장바구니 기존 메뉴 삭제후 메뉴 등록
	 * @param cartVO
	 * @return
	 */
	int removeAndinsertCartItem(CartVO cartVO);
	
	// 장바구니 다른 카페상품유무 확인
	int isDifferentCafeItem(String MEMBER_ID, int CAFE_NO);

	// 장바구니 상품 같은 상품유무 확인
	int isCartItem(String MEMBER_ID, int MENU_NO);

	// 장바구니 상품 갯수 가져오기
	int countOfCartItem(String MEMBER_ID, int MENU_NO);

	// 장바구니 전체조회
	List<CartVO> cartItemList(String MEMBER_ID);
	Map<String, Object> getCartItemList(String MEMBER_ID);

	// 장바구니 수정
	int modifyCartItem(String MEMBER_ID, int MENU_NO, int MENU_COUNT);
	int modifyCartItem(String coc, String MEMBER_ID, int MENU_NO);

	// 장바구니 삭제
	int deleteCartItem(String MEMBER_ID, int MENU_NO);

	/* 주문하기 */
	/**
	 * 주문 등록
	 * @param orderVO
	 * @return
	 */
	List<OrderDetailVO> registOrder(OrderVO orderVO);

	/**
	 * 상세주문등록
	 * @param ORDER_NO
	 * @param cartVO
	 * @return
	 */
	int registOrderDetail(int ORDER_NO, CartVO cartVO);
	
	//주문상태변경 
	int changeOrderStatus(String MEMBER_ID, int ORDER_NO, Date ORDER_DATE, String ORDER_STATUS);

	//주문상태변경 
	Map<String, Object> changeOrderStatus(int ORDER_NO, String nextStatus);

	// 전체주문내역
	List<OrderVO> orderList(String MEMBER_ID);
	
	/**
	 * 주문내역 가져오기(고객용)
	 * @param member_id
	 * @param startDt
	 * @param endDt
	 * @return
	 */
	Map<String, Object> getCustOrderList(String member_id, String startDt, String endDt, int reqPage);

	// 주문상세보기
	List<OrderDetailVO> getOrderDetail(int ORDER_NO);

	/**
	 * 주문관리목록
	 * @param map
	 * @return
	 */
	Map<String, Object> getOrderManagementList(String member_id);
	
	/**
	 * 판매내역목록
	 * @param cafe_no
	 * @param startDt
	 * @param endDt
	 * @param optionValue
	 * @param reqPage
	 * @return
	 */
	Map<String, Object> getSalesManagementList(String member_id, String startDt, String endDt, String optionValue, int reqPage);
}
