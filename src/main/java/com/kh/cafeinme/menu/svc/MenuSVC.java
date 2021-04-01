package com.kh.cafeinme.menu.svc;

import java.util.List;

import com.kh.cafeinme.menu.vo.CategoryVO;
import com.kh.cafeinme.menu.vo.MenuVO;


public interface MenuSVC {
	
	/**
	 * 메뉴 등록, 수정하기
	 * @param menuvo
	 * @param member_id
	 * @return
	 */
	int[] insertorupdatemenu(List<MenuVO> menuvo,String member_id);
	

	/**
	 * 메뉴 삭제
	 * @param menu_no
	 * @param member_id
	 * @return
	 */
	int delmenu(int menu_no,String member_id);
	
	/**
	 * 등록된 메뉴 목록 불러오기
	 * @param member_id
	 * @return
	 */
	List<MenuVO> getmymenu(String member_id);
	
	/**
	 * 한 메뉴 불러오기
	 * @param menu_no
	 * @return
	 */
	MenuVO getmyonemenu(int menu_no);
	
	/**
	 * 온라인 판매 메뉴 등록
	 * @param menu_no
	 * @param onlinesale
	 * @return
	 */
	int updateonline(int menu_no,int onlinesale);
	
	/**
	 * 카테고리 불러오기(21.03.08 추가)
	 * @return
	 */
	List<CategoryVO> getCategory();
	List<CategoryVO> getCategory(int cafe_no);
	List<CategoryVO> getCategoryOnlinesale(int cafe_no);
	/**
	 * 메뉴 불러오기(21.03.08 추가)
	 * @param cafe_no
	 * @return
	 */
	List<MenuVO> getOnlinesaleMenu(int cafe_no);
}

