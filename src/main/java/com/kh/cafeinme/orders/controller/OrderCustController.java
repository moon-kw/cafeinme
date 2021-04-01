package com.kh.cafeinme.orders.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.socket.TextMessage;

import com.kh.cafeinme.common.paging.PageCriteria;
import com.kh.cafeinme.common.websocket.MyHandler;
import com.kh.cafeinme.member.vo.MemberVO;
import com.kh.cafeinme.mycafe.dao.MycafeDAO;
import com.kh.cafeinme.mycafe.vo.CafeVO;
import com.kh.cafeinme.orders.svc.OrderSVC;
import com.kh.cafeinme.orders.vo.CartVO;
import com.kh.cafeinme.orders.vo.OrderDetailVO;
import com.kh.cafeinme.orders.vo.OrderVO;
import com.kh.cafeinme.reviews.svc.ReviewSVC;
import com.kh.cafeinme.search.svc.SearchSVC;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/shop")
public class OrderCustController {

	private final OrderSVC orderSVC;
	private final SearchSVC searchSVC;
	private final MycafeDAO mycafeDAO;
	private final MyHandler myHandler;

	//장바구니 페이지
	@GetMapping("/cart")
	public String cart(HttpSession session, Model model) {
		MemberVO memberVO = (MemberVO)session.getAttribute("members");
		//카트리스트 불러오기
		Map<String, Object> map = orderSVC.getCartItemList(memberVO.getMember_id());
		model.addAttribute("map", map);
		return "/shop/cart";
	}
	
	//주문 페이지
	@GetMapping("/order")
	public String orderForm(HttpSession session, Model model) {
		MemberVO memberVO = (MemberVO)session.getAttribute("members");
		//카트리스트 불러오기
		List<CartVO> cartList = orderSVC.cartItemList(memberVO.getMember_id());
		//카페이름을 가져오기 위한 카페번호 알아내기
		int CAFE_NO = cartList.get(0).getCAFE_NO();
		//카페정보 가져오기
		CafeVO cafeVO = searchSVC.cafeinfo(CAFE_NO);
		if(cafeVO.getCfilevo() == null) {
			cafeVO.setCfile_path("/favicon.png");
		}else {
			cafeVO.setCfile_path(cafeVO.getCfilevo().get(0).getCfile_path());
		}
		//정보 전송
		model.addAttribute("cafeVO", cafeVO);
		model.addAttribute("cartList", cartList);
		return "/orders/orderForm";
	}
	
	//주문 페이지 처리
	@PostMapping("/orderPayment")
	public String order(@Valid OrderVO orderVO, RedirectAttributes rda) {
		String viewName = null;
		log.info(orderVO.toString());
		List<OrderDetailVO> orderDetailList = orderSVC.registOrder(orderVO);
			
		if(orderDetailList != null) {
			CafeVO cafeVO = searchSVC.cafeinfo(orderVO.getCAFE_NO());

			String ownerid = mycafeDAO.getCafeOwnerID(orderVO.getCAFE_NO());
			myHandler.getWebSocketSession()
			 .stream()
			 .filter(wss->((MemberVO)wss.getAttributes().get("members")).getMember_id().equals(ownerid))
			 .forEach(wss -> {
		      try {
		        wss.sendMessage(new TextMessage("서버에서보냄"));
			    } catch (IOException e) {
			        log.error("Error occurred.", e);
			    }
				});;
				
			if(cafeVO.getCfilevo() == null) {
				cafeVO.setCfile_path("/favicon.png");
			}else {
				cafeVO.setCfile_path(cafeVO.getCfilevo().get(0).getCfile_path());
			}
			Map<String, Object> map = new HashMap<>();
			map.put("cafe_no", orderVO.getCAFE_NO());
			map.put("cafe_name", orderVO.getCAFE_NAME());
			map.put("cafe_img", cafeVO.getCfile_path());
			map.put("order_count", orderVO.getORDER_COUNT());
			map.put("order_price", orderVO.getORDER_PRICE());
			map.put("order_receivetype", orderVO.getORDER_RECEIVETYPE());
			map.put("order_paymenttype", orderVO.getORDER_PAYMENTTYPE());
			rda.addFlashAttribute("orderVO", map);
			rda.addFlashAttribute("orderDetailList", orderDetailList);			
			viewName = "redirect:/shop/orderComplete";
		}else {
			viewName = "redirect:/shop/order";
		}
		return viewName;
	}
	
	//주문완료 페이지
	@GetMapping("/orderComplete")
	public String orderComplete() {
		
		
		
		return "/orders/orderComplete";
	}
	
	//주문목록 페이지
	@GetMapping({"/orderList","/orderList/{reqPage}", "/orderList/{reqPage}/{startDt}/{endDt}"})
	public String orderListForm(HttpSession session, 
			@PathVariable(value="reqPage", required = false) Optional<Integer> reqPage,
			@PathVariable(value="startDt", required = false) String startDt,
			@PathVariable(value="endDt", required = false) String endDt,
			Model model) {
		MemberVO memberVO = (MemberVO)session.getAttribute("members");
		Map<String, Object> map = orderSVC.getCustOrderList(memberVO.getMember_id(), startDt, endDt, reqPage.orElse(1));
		model.addAttribute("map", map);
		return "/orders/custOrderList";
	}
	
	//주문페이지 주소API
	@GetMapping("/juso")
	public String juso() {
		return "/orders/juso";
	}
}
