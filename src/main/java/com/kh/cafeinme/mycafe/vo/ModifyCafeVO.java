package com.kh.cafeinme.mycafe.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ModifyCafeVO {
private String cafe_name;
private String cafe_tel;
private String cafe_address1;
private String cafe_address2;
private String open_time;
private String close_time;
private String cafe_content;
private List<Integer> tag_no;
private List<CfileVO> delfile;
private List<MultipartFile> files;
}
