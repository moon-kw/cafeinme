package com.kh.cafeinme.orders.svc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.cafeinme.common.paging.PageCriteria;
import com.kh.cafeinme.mycafe.dao.MycafeDAO;
import com.kh.cafeinme.mycafe.vo.CafeVO;
import com.kh.cafeinme.orders.dao.OrderDAO;
import com.kh.cafeinme.orders.vo.CartVO;
import com.kh.cafeinme.orders.vo.OrderDetailVO;
import com.kh.cafeinme.orders.vo.OrderVO;
import com.kh.cafeinme.reviews.vo.ReviewVO;
import com.kh.cafeinme.search.dao.SearchDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderSVCImpl implements OrderSVC {

	private final OrderDAO orderDAO;
	private final SearchDAO searchDAO;
	private final MycafeDAO mycafeDAO;
	@Qualifier("pc_10")
	private final PageCriteria pc;
	
	// 장바구니 등록
	@Transactional
	@Override
	public int insertCartItem(CartVO cartVO) {
		int result = 0;
		// 장바구니에 다른 카페 상품이 있는지 확인
		result = isDifferentCafeItem(cartVO.getMEMBER_ID(), cartVO.getCAFE_NO());
		if (result > 0) {
			return -1;
		}
		// 장바구니에 같은 상품이 있는지 확인
		result = isCartItem(cartVO.getMEMBER_ID(), cartVO.getMENU_NO());
		if (result > 0) {
			// 같은 상품이 있을시 수량만큼 추가
			int num = cartVO.getMENU_COUNT();
			num += countOfCartItem(cartVO.getMEMBER_ID(), cartVO.getMENU_NO());
			if(num > 99) {
				num = 99;
			}
			return modifyCartItem(cartVO.getMEMBER_ID(), cartVO.getMENU_NO(), num);
		}
		return orderDAO.insertCartItem(cartVO);
	}

	/**
	 * 장바구니 기존 메뉴 삭제후 메뉴 등록
	 * @param cartVO
	 * @return
	 */
	@Override
	public int removeAndinsertCartItem(CartVO cartVO) {
		orderDAO.deleteCartItemAll(cartVO.getMEMBER_ID());
		return orderDAO.insertCartItem(cartVO);
	}
	
	// 장바구니 상품 다른 카페상품유무 확인
	@Override
	public int isDifferentCafeItem(String MEMBER_ID, int CAFE_NO) {
		return orderDAO.isDifferentCafeItem(MEMBER_ID, CAFE_NO);
	}

	// 장바구니 상품 같은 상품유무 확인
	@Override
	public int isCartItem(String MEMBER_ID, int MENU_NO) {
		return orderDAO.isCartItem(MEMBER_ID, MENU_NO);
	}

	// 장바구니 상품 갯수 가져오기
	@Override
	public int countOfCartItem(String MEMBER_ID, int MENU_NO) {
		return orderDAO.countOfCartItem(MEMBER_ID, MENU_NO);
	}

	// 장바구니 전체조회
	@Override
	public Map<String, Object> getCartItemList(String MEMBER_ID) {
		Map<String, Object> map = new HashMap<>();
		List<CartVO> cartList = orderDAO.cartItemList(MEMBER_ID);
		if(cartList != null && cartList.size() > 0) {		
			//카페이름을 가져오기 위한 카페번호 알아내기
			int CAFE_NO = cartList.get(0).getCAFE_NO();
			//카페정보 가져오기
			CafeVO cafeVO = searchDAO.cafeinfo(CAFE_NO);
			map.put("cafeVO", cafeVO);
			map.put("cartList", cartList);
			return map;
		}
		return null;
	}
	@Override
	public List<CartVO> cartItemList(String MEMBER_ID) {
		return orderDAO.cartItemList(MEMBER_ID);
	}

	// 장바구니 수정
	@Override
	public int modifyCartItem(String MEMBER_ID, int MENU_NO, int MENU_COUNT) {
		return orderDAO.modifyCartItem(MEMBER_ID, MENU_NO, MENU_COUNT);
	}
	@Override
	public int modifyCartItem(String coc, String MEMBER_ID, int MENU_NO) {
		int MENU_COUNT = orderDAO.countOfCartItem(MEMBER_ID, MENU_NO);
		switch (coc) {
		case "01": //감소
			if (1 < MENU_COUNT) {
				MENU_COUNT--;
			}
			break;
		case "02": //증가
			if (MENU_COUNT < 99) {
				MENU_COUNT++;
			}
			break;
		default:
			break;
		}
		return orderDAO.modifyCartItem(MEMBER_ID, MENU_NO, MENU_COUNT);
	}

	// 장바구니 삭제
	@Override
	public int deleteCartItem(String MEMBER_ID, int MENU_NO) {
		return orderDAO.deleteCartItem(MEMBER_ID, MENU_NO);
	}

	/* 주문하기 */
	// 주문등록
	@Transactional
	@Override
	public List<OrderDetailVO> registOrder(OrderVO orderVO) {
		boolean flag = false;
		int result = 0;
		// order_item 값 세팅하기
		List<CartVO> cartList = orderDAO.cartItemList(orderVO.getMEMBER_ID());
		if(cartList.size() == 1) {
			orderVO.setORDER_ITEMS(cartList.get(0).getMENU_NAME() + " " +
					cartList.get(0).getMENU_COUNT() + "개");
		}else if(cartList.size() > 1) {
			orderVO.setORDER_ITEMS(
					cartList.get(0).getMENU_NAME() + " " +
					cartList.get(0).getMENU_COUNT() + "개 외 " +
					(cartList.size()-1) + "개");
		}
		
		//수령방식이 포장일 경우 매장주소 넣기
		if(orderVO.getORDER_RECEIVETYPE().equals("포장")) {
			orderVO.setORDER_ADDRESS1("매장");
		}
		//주문테이블 등록 및 주문번호 받아오기
		int ORDER_NO = orderDAO.registOrder(orderVO);
		// 상세주문테이블에 카트에 담긴 상품들 등록
		for (CartVO record : cartList) {
			result += registOrderDetail(ORDER_NO, record);
		}
		// 상세주문테이블에 정상적으로 등록되었으면 카트에 담긴 아이템 전부 삭제
		if(cartList.size() == result) {
			orderDAO.deleteCartItemAll(orderVO.getMEMBER_ID());
			flag = true;
		}
		if(flag) {
			return getOrderDetail(ORDER_NO);
		}
		return null;
	}

	// 상세주문등록
	@Override
	public int registOrderDetail(int ORDER_NO, CartVO cartVO) {
		return orderDAO.registOrderDetail(ORDER_NO, cartVO);
	}

	// 주문상태변경
	@Override
	public int changeOrderStatus(String MEMBER_ID, int ORDER_NO, java.sql.Date ORDER_DATE, String ORDER_STATUS) {

		return orderDAO.changeOrderStatus(MEMBER_ID, ORDER_NO, ORDER_DATE, ORDER_STATUS);
	}

	// 주문상태변경
	@Override
	public Map<String, Object> changeOrderStatus(int ORDER_NO, String nextStatus) {
		String ORDER_STATUS = identifyOrderStatus(nextStatus);
		String pORDER_STATUS = previousOrderStatus(nextStatus);
		String order_date = todaytime();
//		String order_date = "20210225";
		orderDAO.changeOrderStatus(ORDER_NO, order_date, ORDER_STATUS);
		int cafe_no = orderDAO.findCafeNObyOrderNO(ORDER_NO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("cafe_no", cafe_no);
		map.put("order_date", order_date);
		
		//주문목록 불러오기
		List<OrderVO> orderList = orderDAO.getOrderManagementList(map);
		
		//주문아이템
		List<OrderDetailVO> detailList = orderDAO.getOrderDetailList(map);
		Map<Integer, List<OrderDetailVO>> items = detailList.stream()
														 .collect(Collectors.groupingBy(item->item.getORDER_NO(),Collectors.toList()));
		//맵에 저장된 내용 제거
		map.clear();
		//맵에 리스트 넣기
		map.put("orderList", orderList);
		map.put("detailList",items);
		return map;
	}

	// 전체주문내역
	@Override
	public List<OrderVO> orderList(String MEMBER_ID) {
		return orderDAO.orderList(MEMBER_ID);
	}

	/**
	 * 주문내역 가져오기(고객용)
	 * @param member_id
	 * @param startDt
	 * @param endDt
	 * @param reqPage
	 * @return
	 */
	@Override
	public Map<String, Object> getCustOrderList(String member_id, String startDt, String endDt, int reqPage) {
		
		if(startDt == null) {
			startDt = todaytime();
		}
		if(endDt == null) {
			endDt = todaytime();
		}
		
		//1)요청페이지
		pc.getRc().setReqPage(reqPage);
		//2)총레코드정보
		pc.setTotalRec(orderDAO.orderListTotalRecord(member_id, startDt, endDt));
		//3)페이징계산
		pc.calculatePaging();
		
		//4)목록가져오기
		int startRec  = pc.getRc().getStartRec();
		int endRec 	= pc.getRc().getEndRec();
		
		List<OrderVO> orderList = orderDAO.getCustOrderList(member_id, startDt, endDt, startRec, endRec);
		
		Map<String, Object> map = new HashMap<>();
		map.put("orderList", orderList);
		map.put("pc",pc);
		return map;
	}
	
	// 주문상세보기
	@Override
	public List<OrderDetailVO> getOrderDetail(int ORDER_NO) {
		return orderDAO.getOrderDetail(ORDER_NO);
	}

	/**
	 * 주문관리목록
	 * @param map
	 * @return
	 */
	@Override
	public Map<String, Object> getOrderManagementList(String member_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int cafe_no = orderDAO.getCafeNObyMemberID(member_id);
		String order_date = todaytime();
		
		map.put("cafe_no", cafe_no);
		map.put("order_date", order_date);
		
		//주문목록 불러오기
		List<OrderVO> orderList = orderDAO.getOrderManagementList(map);
		
		//주문아이템
		List<OrderDetailVO> detailList = orderDAO.getOrderDetailList(map);
		Map<Integer, List<OrderDetailVO>> items = detailList.stream()
														 .collect(Collectors.groupingBy(item->item.getORDER_NO(),Collectors.toList()));
		//맵에 저장된 내용 제거
		map.clear();
		//맵에 리스트 넣기
		map.put("orderList", orderList);
		map.put("detailList",items);
		return map;
	}
	
	/**
	 * 판매내역목록
	 * @param cafe_no
	 * @param startDt
	 * @param endDt
	 * @param optionValue
	 * @param reqPage
	 * @return
	 */
	@Override
	public Map<String, Object> getSalesManagementList(String member_id, String startDt, String endDt, String optionValue,
			int reqPage) {
		
		int cafe_no = orderDAO.getCafeNObyMemberID(member_id);
		String order_status = identifyOrderStatus(optionValue);
		if(startDt == null) {
			startDt = todaytime();
		}
		if(endDt == null) {
			endDt = todaytime();
		}
		
		//1)요청페이지
		pc.getRc().setReqPage(reqPage);
		//2)총레코드정보
		pc.setTotalRec(orderDAO.salesListTotalRecord(cafe_no, startDt, endDt, order_status));
		//3)페이징계산
		pc.calculatePaging();
		
		//4)목록가져오기
		int startRec  = pc.getRc().getStartRec();
		int endRec 	= pc.getRc().getEndRec();

		//판매목록 가져오기
		List<OrderVO> orderList = orderDAO.getSalesManagementList(cafe_no, startDt, endDt, order_status, startRec, endRec);
		
		//판매목록에 맞는 주문상세아이템 가져오기
		Map<Integer, Object> orderDetailList = new HashMap<Integer, Object>();
		for(OrderVO rec : orderList) {
			orderDetailList.put(rec.getORDER_NO(), orderDAO.getOrderDetail(rec.getORDER_NO()));
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderList", orderList);
		map.put("orderDetailList", orderDetailList);
		map.put("pc",pc);
		return map;
	}
	
	// 주문상태 식별메소드
	private String identifyOrderStatus(String optionValue) {
		switch (optionValue) {
		case "status01":
			return "접수대기";
		case "status02":
			return "주문접수";
		case "status03":
			return "주문완료";
		case "status04":
			return "주문거부";
		default:
			return null;
		}
	}

	private String previousOrderStatus(String optionValue) {
		switch (optionValue) {
		case "status02":
			return "접수대기";
		case "status03":
			return "주문접수";
		case "status04":
			return "접수대기";
		default:
			return null;
		}
	}

	// 오늘 날짜 구하는 메소드
	private String todaytime() {
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		// String todaytime = formatDate.format(new Date(System.currentTimeMillis()));
		// //sql.Date일때
		Date date = new Date();
		String todaytime = formatDate.format(date.getTime()).substring(0, 8);
		return todaytime;
	}

	// 주문취소
	// @Override
	// public int cancelOrder(String MEMBER_ID, long ORDER_NO, Date ORDER_DATE) {
	// return ordersDAO.cancelOrder(MEMBER_ID, ORDER_NO, ORDER_DATE);
	// }

}
