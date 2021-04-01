package com.kh.cafeinme.common.svc;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.cafeinme.common.dao.CodeDAO;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CodeSVCImpl implements CodeSVC {

	private final CodeDAO codeDAO;
	
	/**
	 * 다음 시퀀스번호 가져오기
	 * @param sequence
	 * @return
	 */
	@Override
	public int getNextVal(String sequence) {
		return codeDAO.getNextVal(sequence);
	}
	
	/**
	 * 다음 시퀀스번호 가져오기
	 * @param sequence
	 * @return
	 */
	@Override
	public long getNextVal2(String sequence) {
		return codeDAO.getNextVal2(sequence);
	}

}
