package com.kh.cafeinme.search.svc;

import java.util.List;

import com.kh.cafeinme.mycafe.vo.CafeVO;

public interface SearchSVC {
	

	List<CafeVO> searchroad(String keyword);
	List<CafeVO> searchname(String keyword);
	boolean iscafeinfo(int cafe_no);
	CafeVO cafeinfo(int cafe_no);
	boolean isbookmark(CafeVO cafevo,String member_id);
}
