package com.kh.cafeinme.mycafe.svc;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

@Component
public class FilecodeSVC {
	
	public String code(String originalfiletype) {
		StringBuilder code = new StringBuilder();
		String[] array = originalfiletype.split("/");
		int r = (int)(Math.random()*100);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat date = new SimpleDateFormat("MMddhhmmss");
		code.append(r);
		code.append(date.format(timestamp));
		code.append(".");
		code.append(array[1]);
		return code.toString();
	}
	public String recode(String file_path) {
		String[] split = file_path.split("cafemenu/");
		return split[1];
	}
}
