package com.kh.cafeinme.bookmark.svc;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.cafeinme.bookmark.dao.BookmarkDAO;
import com.kh.cafeinme.mycafe.vo.CafeVO;
@Service
public class BookmarkSVCImpl implements BookmarkSVC{
@Autowired
BookmarkDAO bd;
	@Override
	public int bookmark(int cafe_no, String member_id) {
		
		return bd.bookmark(cafe_no, member_id);
	}

	@Override
	public int delbookmark(int cafe_no, String member_id) {
	
		return bd.delbookmark(cafe_no, member_id);
	}

	@Override
	public List<CafeVO> mybookmark(String member_id) {
	
		
		return bd.mybookmark(member_id);
	}


}
