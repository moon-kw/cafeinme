package com.kh.cafeinme.search.svc;

import java.util.List;



import org.springframework.stereotype.Service;

import com.kh.cafeinme.mycafe.vo.CafeVO;
import com.kh.cafeinme.search.dao.SearchDAO;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class SearchSVCImpl implements SearchSVC {

	private final SearchDAO sd;
	private final KeywordsetSVC ks;
	
	@Override
	public List<CafeVO> searchroad(String keyword) {
		String newkeyword = ks.keyset(keyword);
		
		return sd.searchroad(newkeyword);
	}
	@Override
	public List<CafeVO> searchname(String keyword) {
		String newkeyword = ks.keyset(keyword);
		
		return sd.searchname(newkeyword);
	}

	@Override
	public CafeVO cafeinfo(int cafe_no) {
	
		
		return sd.cafeinfo(cafe_no);
	}


	@Override
	public boolean isbookmark(CafeVO cafevo, String member_id) {
		
		return sd.isbookmark(cafevo, member_id);
	}
	@Override
	public boolean iscafeinfo(int cafe_no) {
	
		return sd.iscafeinfo(cafe_no);
	}

}
