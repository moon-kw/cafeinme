package com.kh.cafeinme.mycafe.svc;

import java.util.List;
import java.util.Map;

import org.springframework.validation.Errors;

import com.kh.cafeinme.mycafe.vo.CafeVO;
import com.kh.cafeinme.mycafe.vo.ModifyCafeVO;
import com.kh.cafeinme.mycafe.vo.TagVO;

public interface MycafeSVC {
	
	//카페 등록 여부
	boolean ishavecafe(String member_id);
	
	// 온라인판매여부(21.3.2에 추가)
	boolean isOnlinesale(int cafe_no);
	boolean isonlinesale(String member_id);
	
	int joinMycafe(CafeVO cafevo);
	// 3.12 수정
	int modifymycafe(ModifyCafeVO cafevo,String member_id);
	// 3.12 수정
	int delmyfile(int cfile_no,String member_id);
	List<TagVO> gettags();
	CafeVO getmycafe(String id);
	int delmycafe(String member_id);
	Map<String, String> valideHandling(Errors errors);
	boolean ishavebn(String bn);
	boolean isbn(String bn,String member_id);
}
