package com.kh.cafeinme.myshop.svc;

import org.springframework.stereotype.Service;

import com.kh.cafeinme.myshop.dao.MyShopDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyShopSVCImpl implements MyShopSVC{
private final MyShopDAO md;
	@Override
	public int updateonlinesale(String member_id, String status) {
		int onlinesale = 0;
		if(status.equals("1")) {
			onlinesale = 1;
		}
		return md.updateonlinesale(member_id, onlinesale);
	}

}
