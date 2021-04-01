package com.kh.cafeinme.bookmark.dao;

import java.util.List;

import com.kh.cafeinme.mycafe.vo.CafeVO;

public interface BookmarkDAO {
	/**
	 * 회원 북마크 조회
	 * @param cafe_no
	 * @param member_id
	 * @return
	 */
	int bookmark(int cafe_no,String member_id);
	
	/**
	 * 북마크 삭제
	 * @param cafe_no
	 * @param member_id
	 * @return
	 */
	int delbookmark(int cafe_no,String member_id);
	
	/**
	 * 회원 북마크 리스트 확인
	 * @param member_id
	 * @return
	 */
	List<CafeVO> mybookmark(String member_id);
}
