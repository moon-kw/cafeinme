package com.kh.cafeinme.mycafe.vo;
// (2021 03 12 수정)
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
@Data
public class CfileVO {
	private int cfile_no;
	private String cfile_origin_name;
	private String cfile_change_name;
	private long cfile_size;
	private String cfile_type;
	private String cfile_path;
	private int cafe_no;
	private MultipartFile file;
	private boolean isdel;
	
	
	
}
