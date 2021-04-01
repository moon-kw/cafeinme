package com.kh.cafeinme.ordersTest.dao;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.kh.cafeinme.common.dao.CodeDAO;
import com.kh.cafeinme.orders.dao.OrderDAO;
import com.kh.cafeinme.orders.vo.CartVO;
import com.kh.cafeinme.orders.vo.OrderDetailVO;
import com.kh.cafeinme.orders.vo.OrderVO;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*.xml"})
@Slf4j
@Transactional
public class OrdersDAOImplTest {
	
	private final static Logger logger = LoggerFactory.getLogger(OrdersDAOImplTest.class);
	
	@Autowired
	JdbcTemplate jt;
	
	@Autowired
	OrderDAO orderDAO;
	
	@Autowired
	CodeDAO codeDAO;

	//장바구니 등록
	@Test
	@Disabled
	void insertCart() {
		CartVO cartVO = new CartVO();
		int result = 0;
		cartVO.setMEMBER_ID("test3@test.com");
		cartVO.setCAFE_NO(1);
		cartVO.setMENU_NO(3);
		cartVO.setMENU_NAME("초코케익");
		cartVO.setMENU_COUNT(1);
		cartVO.setMENU_PRICE(2000);
		result = orderDAO.insertCartItem(cartVO);
		log.info("cartVO:"+cartVO);
		log.info("result:"+result);
	}
	
	//장바구니 다른 카페상품유무 확인
	@Test
	@Disabled
	void isDifferentCafeItem() {
		int result = orderDAO.isDifferentCafeItem("test@test.com", 2);
		log.info("장바구니 상품 다른 카페상품유무:"+result);
	}
	
	//장바구니 상품 같은 상품유무 확인
	@Test
	@Disabled
	void isCartItem() {
		int result = orderDAO.isCartItem("test3@test.com", 3);
		log.info("장바구니 상품 같은 상품유무:"+result);
	}
	
	//장바구니 상품 갯수 가져오기
	@Test
	@Disabled
	void countOfCartItem() {
		int result = orderDAO.countOfCartItem("test3@test.com", 3);
		log.info("장바구니 상품 갯수 가져오기:"+result);
	}
	
	//장바구니 전체조회
	@Test
	@Disabled
	void cartItemList() {
		List<CartVO> list = orderDAO.cartItemList("test@test.com");
		for(CartVO record : list) {
			log.info("전체조회:"+record.toString());
		}
	}
	
	//장바구니 수정
	@Test
	@Disabled
	void modifyCartItem() {
		int result = orderDAO.modifyCartItem("test@test.com", 3, 10);
		log.info("장바구니 수정:"+result);
	}
	
	@Test
	@Disabled
	void registOrder() {
		OrderVO orderVO = new OrderVO();
//		orderVO.setORDER_NO(100);
		orderVO.setMEMBER_ID("test@test.com");
		orderVO.setCAFE_NO(1);
		orderVO.setORDER_PRICE(2000);
		orderVO.setORDER_COUNT(10);
		orderVO.setORDER_ADDRESS1("울산");
		orderVO.setORDER_ADDRESS2("달동");
		orderVO.setORDER_PAYMENTTYPE("카드결제");
		orderVO.setORDER_RECEIVETYPE("배달");
		int result = orderDAO.registOrder(orderVO);
		log.info("result : "+ result);
	}
	
	@Test
	@Disabled
	void registOrderDetail() {
		CartVO cartVO = new CartVO();
		int ORDER_NO = 100;
		cartVO.setMENU_NO(1);
		cartVO.setMENU_NAME("아메리카노");
		cartVO.setMENU_COUNT(10);
		cartVO.setMENU_PRICE(1000);
		int result = orderDAO.registOrderDetail(ORDER_NO, cartVO);
		log.info("result : "+ result);
	}
	
	@Test
	@Disabled
	void deleteCartItem() {
		String MEMBER_ID = "test@test.com"; 
		int MENU_NO = 1;
		log.info("result : "+orderDAO.deleteCartItem(MEMBER_ID, MENU_NO));
	}
	
	@Test
	@Disabled
	void orderList() {
		String MEMBER_ID = "test@test.com";
		List<OrderVO> list = orderDAO.orderList(MEMBER_ID);
		for(OrderVO rec : list) {
			log.info(rec.toString());
		}
	}
	
	@Test
	void salesListTotalRecord() {
		int result = orderDAO.salesListTotalRecord(1, "20210301", "20210331", "접수대기");
		log.info("총 레코드 수 : "+result);
	}
	
	@Test
	void getSalesManagementList() {
		List<OrderVO> result = orderDAO.getSalesManagementList(1, "20210301", "20210331", "접수대기", 1, 10);
		for(OrderVO rec : result) {
			log.info("판매목록 : " + rec.toString());
		}
	}

}
