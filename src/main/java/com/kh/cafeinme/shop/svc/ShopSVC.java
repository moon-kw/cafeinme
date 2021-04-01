package com.kh.cafeinme.shop.svc;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.cafeinme.mycafe.vo.CafeVO;

@Service
public interface ShopSVC {
	
	List<CafeVO> getShopList(int cafe_onlinesale);

}
