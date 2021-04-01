package com.kh.cafeinme.shop.dao;

import java.util.List;

import com.kh.cafeinme.mycafe.vo.CafeVO;


public interface ShopDAO {
	List<CafeVO> getShopList(int cafe_onlinesale);
}
