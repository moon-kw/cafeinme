package com.kh.cafeinme.common.svc;

public interface CodeSVC {

	/**
	 * 다음 시퀀스번호 가져오기
	 * @param sequence
	 * @return
	 */
	int getNextVal(String sequence);
	
	/**
	 * 다음 시퀀스번호 가져오기
	 * @param sequence
	 * @return
	 */
	long getNextVal2(String sequence);
}
