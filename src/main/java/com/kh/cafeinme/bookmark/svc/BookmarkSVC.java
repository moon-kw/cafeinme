package com.kh.cafeinme.bookmark.svc;

import java.util.List;

import com.kh.cafeinme.mycafe.vo.CafeVO;

public interface BookmarkSVC {
int bookmark(int cafe_no,String member_id);
int delbookmark(int cafe_no,String member_id);
List<CafeVO> mybookmark(String member_id);
}
