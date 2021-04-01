package com.kh.cafeinme.shop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kh.cafeinme.menu.svc.MenuSVC;
import com.kh.cafeinme.menu.vo.CategoryVO;
import com.kh.cafeinme.menu.vo.MenuVO;
import com.kh.cafeinme.mycafe.vo.CafeVO;
import com.kh.cafeinme.orders.svc.OrderSVC;
import com.kh.cafeinme.search.svc.SearchSVC;
import com.kh.cafeinme.shop.svc.ShopSVC;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/shop")
public class ShopController {

	private final MenuSVC menuSVC;
	private final SearchSVC searchSVC;
	private final OrderSVC orderSVC;
	private final ShopSVC shopSVC;
	
	@ModelAttribute("categoryVO")
	public List<CategoryVO> getcategory(){
		return menuSVC.getCategory();
	}

	
	
	@GetMapping({"/itemList/{cafe_no}"})
	public String itemList(
			@PathVariable("cafe_no") int cafe_no, 
			Model model) {
		//카페정보 가져오기
		CafeVO cafeVO = searchSVC.cafeinfo(cafe_no);
		if(cafeVO.getCfilevo() == null) {
			cafeVO.setCfile_path("/favicon.png");
		}else {
			cafeVO.setCfile_path(cafeVO.getCfilevo().get(0).getCfile_path());
		}
		//카테고리 가져오기
		List<CategoryVO> categoryList = menuSVC.getCategoryOnlinesale(cafe_no);
		//메뉴 가져오기
		List<MenuVO> menuList = menuSVC.getOnlinesaleMenu(cafe_no);
		//뷰로 데이터보내기
		model.addAttribute("cafeVO", cafeVO);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("menuList", menuList);
		return "/shop/itemList";
	}
	
	@GetMapping("/itemDetail/{menu_no}")
	public String itemDetail(@PathVariable("menu_no") int menu_no, Model model) {
		int menu_no2 = 1;
		MenuVO menuVO = menuSVC.getmyonemenu(menu_no);
		model.addAttribute("menuVO",menuVO);
		return "/shop/itemDetail";
	}
	
	
	
	//목록가져오기(3.22 추가)
	@RequestMapping("")
	public String shopList(Model model) {
		
		int cafe_onlinesale = 1;
		List<CafeVO> list = shopSVC.getShopList(cafe_onlinesale);
		model.addAttribute("list", list);
		return "/shop/shop";
	}
	
	
}
