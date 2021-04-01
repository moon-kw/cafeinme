package com.kh.cafeinme.shop.svc;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.cafeinme.mycafe.vo.CafeVO;
import com.kh.cafeinme.shop.dao.ShopDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) //읽기전용 트랙잭션
@Slf4j
public class ShopSVCImpl implements ShopSVC{
	
	private final ShopDAO shopDAO;

	@Override
	public List<CafeVO> getShopList(int cafe_onlinesale) {
		return shopDAO.getShopList(cafe_onlinesale);
	}

}
