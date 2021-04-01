package com.kh.cafeinme.menu.svc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.cafeinme.menu.dao.MenuDAO;
import com.kh.cafeinme.menu.vo.CategoryVO;
import com.kh.cafeinme.menu.vo.MenuVO;


@Service
public class MenuSVCImpl implements MenuSVC{
@Autowired
MenuDAO md;

	@Override
	public int[] insertorupdatemenu(List<MenuVO> menuvo,String member_id) {
		List<MenuVO> update = new ArrayList<MenuVO>();
		List<MenuVO> insert = new ArrayList<MenuVO>();
		
		for(int i=0; i<menuvo.size(); i++) {
			if(menuvo.get(i).getMenu_no()!=0) {
				update.add(menuvo.get(i));
			}
			else {
				if(menuvo.get(i).getMenu_name()!=null) {
					
					insert.add(menuvo.get(i));
				}
			}
		}
		md.insertmenu(insert, member_id);
		md.updatemenu(update, member_id);
		
		return null;
	}

	@Override
	public int delmenu(int menu_no,String member_id) {

		return md.delmenu(menu_no,member_id);
	}

	@Override
	public List<MenuVO> getmymenu(String member_id) {
		
		
		return md.getmymenu(member_id);
	}

	//메뉴 1개 가져오기(21.03.11 수정)
	@Override
	public MenuVO getmyonemenu(int menu_no) {
		MenuVO menuVO = md.getmyonemenu(menu_no);
		if(menuVO.getMfile_path() == null) {
			menuVO.setMfile_path("/favicon.png");
		}
		return menuVO;
	}

	@Override
	public int updateonline(int menu_no, int onlinesale) {
		
		return md.updateonline(menu_no, onlinesale);
	}
	
	/**
	 * 카테고리 불러오기(21.03.11 추가)
	 * 
	 * @return
	 */
	@Override
	public List<CategoryVO> getCategory() {
		return md.getCategory();
	}
	@Override
	public List<CategoryVO> getCategory(int cafe_no) {
		return md.getCategory(cafe_no);
	}
	@Override
	public List<CategoryVO> getCategoryOnlinesale(int cafe_no) {
		return md.getCategoryOnlinesale(cafe_no);
	}
	/**
	 * 메뉴 불러오기(21.03.11 수정)
	 * 
	 * @param cafe_no
	 * @return
	 */
	@Override
	public List<MenuVO> getOnlinesaleMenu(int cafe_no) {
		List<MenuVO> list = md.getOnlinesaleMenu(cafe_no);
		for(MenuVO rec : list) {
			if(rec.getMfile_path() == null) {
				rec.setMfile_path("/favicon.png");
			}
		}
		return list;
	}

}
