package com.kh.cafeinme.mycafe.svc;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.kh.cafeinme.mycafe.dao.MycafeDAO;
import com.kh.cafeinme.mycafe.vo.CafeVO;
import com.kh.cafeinme.mycafe.vo.ModifyCafeVO;
import com.kh.cafeinme.mycafe.vo.TagVO;
@Service
public class MycafeSVCImpl implements MycafeSVC {
@Autowired
MycafeDAO md;
	@Override
	public int joinMycafe(CafeVO cafevo) {
		 
		return md.joinMycafe(cafevo);
	}
	@Override
	public List<TagVO> gettags() {
		
		return md.gettags();
	}
	@Override
	public CafeVO getmycafe(String id) {
		 
	
		return md.getmycafe(id);
	}
	
	@Override
	public int delmycafe(String member_id) {
		
		return md.delmycafe(member_id);
	}

	@Override
	public boolean ishavecafe(String member_id) {
		// TODO Auto-generated method stub
		return md.ishavecafe(member_id);
	}
	@Override
	public Map<String, String> valideHandling(Errors errors) {
		Map<String,String> map = new HashMap<>();
		
		for(FieldError error : errors.getFieldErrors()) {
			String key = String.format("valid_%s", error.getField());
			String value = error.getDefaultMessage();
			map.put(key, value);
		}
		
		return map;
	}
	
	//사업자번호 조회
	@Override
	public boolean ishavebn(String bn) {
		
		return md.ishavebn(bn);
	}
	
	
	// 온라인판매여부(21.3.5에 추가)
	@Override
	public boolean isOnlinesale(int cafe_no) {
		return md.isOnlinesale(cafe_no);
	}
	
	@Override
	public boolean isonlinesale(String member_id) {
		
		return md.isonlinesale(member_id);
	}
	
	//카페삭제시 BN맞는지 확인하는거
	@Override
	public boolean isbn(String bn, String member_id) {
		
		return md.isbn(bn, member_id);
	}
	@Override
	public int modifymycafe(ModifyCafeVO cafevo, String member_id) {
		// TODO Auto-generated method stub
		return md.modifymycafe(cafevo, member_id);
	}
	@Override
	public int delmyfile(int cfile_no, String member_id) {
		// TODO Auto-generated method stub
		return md.delmyfile(cfile_no, member_id);
	}



}
