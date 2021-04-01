package com.kh.cafeinme.search.dao;

import java.util.List;

import com.kh.cafeinme.mycafe.vo.CafeVO;


public interface SearchDAO {
	
	/**
	 * 위치로 카페 검색
	 * @param keyword
	 * @return
	 */
	List<CafeVO> searchroad(String keyword);
	
	/**
	 * 이름으로 카페 검색
	 * @param keyword
	 * @return
	 */
	List<CafeVO> searchname(String keyword);
	
	/**
	 * 카페 정보 불러오기
	 * @param cafe_no
	 * @return
	 */
	boolean iscafeinfo(int cafe_no);
	CafeVO cafeinfo(int cafe_no);
	
	/**
	 * 북마크 등록 여부
	 * @param cafevo
	 * @param member_id
	 * @return
	 */
	boolean isbookmark(CafeVO cafevo,String member_id);
}
