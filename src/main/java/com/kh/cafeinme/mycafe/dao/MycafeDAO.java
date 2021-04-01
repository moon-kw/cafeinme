package com.kh.cafeinme.mycafe.dao;

import java.util.List;

import com.kh.cafeinme.mycafe.vo.CafeVO;
import com.kh.cafeinme.mycafe.vo.ModifyCafeVO;
import com.kh.cafeinme.mycafe.vo.TagVO;

public interface MycafeDAO {
	
	int joinMycafe(CafeVO cafevo);
	List<TagVO> gettags();
	CafeVO getmycafe(String id);
	//3.12 수정
	int modifymycafe(ModifyCafeVO cafevo,String member_id);
	
	int delmycafe(String member_id);
	
	//3.12 수정
	int delmyfile(int cfile_no,String member_id);
	boolean ishavebn(String bn);
	boolean isbn(String bn,String member_id);
	
	//카페 등록 여부
	boolean ishavecafe(String member_id);
	
	//온라인판매여부(21.3.2에 추가)
	boolean isOnlinesale(int cafe_no);
	boolean isonlinesale(String member_id);
	
	/**
	 * 카페사장아이디 가져오기(카페번호)
	 * @param cafe_no
	 * @return
	 */
	String getCafeOwnerID(int cafe_no);
}
