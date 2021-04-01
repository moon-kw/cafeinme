package com.kh.cafeinme.ordersTest.svc;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.kh.cafeinme.orders.svc.OrderSVC;
import com.kh.cafeinme.orders.vo.CartVO;
import com.kh.cafeinme.orders.vo.OrderVO;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*.xml"})
@Slf4j
@Transactional
public class OrdersSVCImplTest {

	@Autowired
	OrderSVC orderSVC;
	
	//장바구니 등록
	@Test
	@Disabled
	void insertCart() {
		CartVO cartVO = new CartVO();
		int result = 0;
		cartVO.setMEMBER_ID("test3@test.com");
		cartVO.setCAFE_NO(2);
		cartVO.setMENU_NO(21);
		cartVO.setMENU_NAME("초코케익");
		cartVO.setMENU_COUNT(1);
		cartVO.setMENU_PRICE(2000);
		result = orderSVC.insertCartItem(cartVO);
		log.info("cartVO:"+cartVO);
		log.info("result:"+result);
		cartItemList();
	}
	
	//장바구니 다른 카페상품유무 확인
	@Test
	@Disabled
	void isDifferentCafeItem() {
		int result = orderSVC.isDifferentCafeItem("test3@test.com", 1);
		log.info("장바구니 상품 다른 카페상품유무:"+result);
	}
	
	//장바구니 상품 같은 상품유무 확인
	@Test
	@Disabled
	void isCartItem() {
		int result = orderSVC.isCartItem("test3@test.com", 3);
		log.info("장바구니 상품 같은 상품유무:"+result);
	}
	
	//장바구니 상품 갯수 가져오기
	@Test
	@Disabled
	void countOfCartItem() {
		int result = orderSVC.countOfCartItem("test3@test.com", 3);
		log.info("장바구니 상품 갯수 가져오기:"+result);
	}
	
	//장바구니 전체조회
	@Test
	@Disabled
	void cartItemList() {
		List<CartVO> list = orderSVC.cartItemList("test3@test.com");
		for(CartVO record : list) {
			log.info("전체조회:"+record.toString());
		}
	}
	
	//장바구니 수정
	@Test
	@Disabled
	void modifyCartItem() {
		int result = orderSVC.modifyCartItem("test3@test.com", 3, 1);
		log.info("장바구니 수정:"+result);
	}
	
	@Test
	void registOrder() {
		OrderVO orderVO = new OrderVO();
		int result = 0;
		orderVO.setMEMBER_ID("test.test.com");
		orderVO.setCAFE_NO(1);
		orderVO.setORDER_PRICE(10000);
//		orderVO.set
	}
	
	@Test
	void getSalesManagementList() {
		String member_id = "test@test.com";
		String startDt = "20210301";
		String endDt = "20210331"; 
		String optionValue = "ada";
		int reqPage = 1;
		
		Map<String, Object> map = orderSVC.getSalesManagementList(member_id, startDt, endDt, optionValue, reqPage);
		log.info("맵"+map);
	}
}
