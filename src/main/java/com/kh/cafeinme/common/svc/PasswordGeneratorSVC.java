package com.kh.cafeinme.common.svc;

import org.springframework.stereotype.Service;

import com.kh.cafeinme.common.PasswordGenerator;

@Service
/**
 * 임시 비밀번호 발급
 * @author mypc
 *
 */
public class PasswordGeneratorSVC {
	
	public String generatorPassword(int passwordLength, boolean ...flag) throws Exception {
		if(flag.length > 4) {
			throw new Exception("flag 값은 4개를 초과할 수 없습니다.");
		}
		
		PasswordGenerator passwordGenerator
			= new PasswordGenerator.PasswordGeneratorBuilder()
									.useDigits(flag[0])
									.useLower(flag[1])
									.useUpper(flag[2])
									.usePunctuation(flag[3])
									.build();
		return passwordGenerator.generate(passwordLength);
	}
}
